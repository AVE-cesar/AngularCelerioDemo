package com.jaxio.demo.security;

import com.jaxio.demo.repository.AppTokenRepository;
import com.jaxio.demo.repository.AppUserRepository;
import com.jaxio.demo.domain.AppToken;
import com.jaxio.demo.domain.AppUser;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.security.web.authentication.rememberme.CookieTheftException;
import org.springframework.security.web.authentication.rememberme.InvalidCookieException;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Date;

import static com.jaxio.demo.config.SecurityConfig.REMEMBER_ME_KEY;

/**
 * Custom implementation of Spring Security's RememberMeServices.
 * <p/>
 * Persistent tokens are used by Spring Security to automatically log in users.
 * <p/>
 * This is a specific implementation of Spring Security's remember-me authentication, but it is much
 * more powerful than the standard implementations:
 * <ul>
 * <li>It allows a user to see the list of his currently opened sessions, and invalidate them</li>
 * <li>It stores more information, such as the IP address and the user agent, for audit purposes<li>
 * <li>When a user logs out, only his current session is invalidated, and not all of his sessions</li>
 * </ul>
 * <p/>
 */
@Service
public class RememberMeServices extends
        AbstractRememberMeServices {

    private final Logger log = LoggerFactory.getLogger(RememberMeServices.class);

    @Inject
    private AppUserRepository appUserRepository;
    
    @Inject
    private AppTokenRepository appTokenRepository;
    
    // Token is valid for one month
    private static final int TOKEN_VALIDITY_DAYS = 31;

    private static final int TOKEN_VALIDITY_SECONDS = 60 * 60 * 24 * TOKEN_VALIDITY_DAYS;

    private static final int DEFAULT_SERIES_LENGTH = 16;

    private static final int DEFAULT_TOKEN_LENGTH = 16;

    private SecureRandom random;

    @Autowired
    public RememberMeServices(Environment env, org.springframework.security.core.userdetails.UserDetailsService userDetailsService) {
        super(REMEMBER_ME_KEY, userDetailsService);
        super.setParameter("rememberme");
        random = new SecureRandom();
    }

    @Override
    protected UserDetails processAutoLoginCookie(String[] cookieTokens, HttpServletRequest request, HttpServletResponse response) {

        AppToken appToken = getPersistentToken(cookieTokens);
        String login = appToken.getUserLogin();

        // Token also matches, so login is valid. Update the token value, keeping the *same* series number.
        log.debug("Refreshing persistent login token for user '{}', series '{}'", login, appToken.getId());
        appToken.setTokenCreationDate(new Date());
        appToken.setTokenValue(generateTokenData());
        appToken.setIpAddress(request.getRemoteAddr());
        appToken.setUserAgent(request.getHeader("User-Agent"));
        try {
        	appTokenRepository.save(appToken);
            addCookie(appToken, request, response);
        } catch (Exception e) {
            log.error("Failed to update token: ", e);
            throw new RememberMeAuthenticationException("Autologin failed due to data access problem", e);
        }
        return getUserDetailsService().loadUserByUsername(login);
    }

    @Override
    protected void onLoginSuccess(HttpServletRequest request, HttpServletResponse response, Authentication successfulAuthentication) {
        String login = successfulAuthentication.getName();

        log.debug("Creating new persistent login for user {}", login);
        AppUser appUser = appUserRepository.findByLogin(login);
        AppToken appToken = new AppToken();
        appToken.setId(generateSeriesData());
        appToken.setUserLogin(appUser.getLogin());
        appToken.setTokenValue(generateTokenData());
        appToken.setTokenCreationDate(new Date());
        appToken.setIpAddress(request.getRemoteAddr());
        appToken.setUserAgent(request.getHeader("User-Agent"));
        try {
            appTokenRepository.save(appToken);
            addCookie(appToken, request, response);
        } catch (Exception e) {
            log.error("Failed to save persistent token ", e);
        }
    }

    /**
     * When logout occurs, only invalidate the current token, and not all user sessions.
     * <p/>
     * The standard Spring Security implementations are too basic: they invalidate all tokens for the
     * current user, so when he logs out from one browser, all his other sessions are destroyed.
     */
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String rememberMeCookie = extractRememberMeCookie(request);
        if (rememberMeCookie != null && rememberMeCookie.length() != 0) {
            try {
                String[] cookieTokens = decodeCookie(rememberMeCookie);
                AppToken appToken = getPersistentToken(cookieTokens);
                appTokenRepository.delete(appToken.getId());
            } catch (InvalidCookieException ice) {
                log.info("Invalid cookie, no persistent token could be deleted");
            } catch (RememberMeAuthenticationException rmae) {
                log.debug("No persistent token found, so no token could be deleted");
            }
        }
        super.logout(request, response, authentication);
    }

    /**
     * Validate the token and return it.
     */
    private AppToken getPersistentToken(String[] cookieTokens) {
        if (cookieTokens.length != 2) {
            throw new InvalidCookieException("Cookie token did not contain " + 2 +
                    " tokens, but contained '" + Arrays.asList(cookieTokens) + "'");
        }

        final String presentedSeries = cookieTokens[0];
        final String presentedToken = cookieTokens[1];

        AppToken appToken = null;
        try {
        	appToken = appTokenRepository.findOne(presentedSeries);
        } catch (Exception e) {
            log.error("Error to access database", e );
        }

        if (appToken == null) {
            // No series match, so we can't authenticate using this cookie
            throw new RememberMeAuthenticationException("No persistent token found for series id: " + presentedSeries);
        }

        // We have a match for this user/series combination
        log.info("presentedToken={} / tokenValue={}", presentedToken, appToken.getTokenValue());
        if (!presentedToken.equals(appToken.getTokenValue())) {
            // Token doesn't match series value. Delete this session and throw an exception.
        	appTokenRepository.delete(appToken.getId());
            throw new CookieTheftException("Invalid remember-me token (Series/token) mismatch. Implies previous cookie theft attack.");
        }

        if (DateUtils.addDays(appToken.getTokenCreationDate(), TOKEN_VALIDITY_DAYS).before(new Date())) {
        	appTokenRepository.delete(appToken.getId());
            throw new RememberMeAuthenticationException("Remember-me login has expired");
        }
        return appToken;
    }

    private String generateSeriesData() {
        byte[] newSeries = new byte[DEFAULT_SERIES_LENGTH];
        random.nextBytes(newSeries);
        return new String(Base64.encode(newSeries));
    }

    private String generateTokenData() {
        byte[] newToken = new byte[DEFAULT_TOKEN_LENGTH];
        random.nextBytes(newToken);
        return new String(Base64.encode(newToken));
    }

    private void addCookie(AppToken appToken, HttpServletRequest request, HttpServletResponse response) {
        setCookie(
                new String[]{appToken.getId(), appToken.getTokenValue()},
                TOKEN_VALIDITY_SECONDS, request, response);
    }
}