package com.jaxio.demo.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.jaxio.demo.domain.AppUser;
import com.jaxio.demo.repository.AppUserRepository;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Spring Security success handler, specialized for Ajax requests.
 */
@Component
public class RestAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
    @Inject
    private AppUserRepository appUserRepository;
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication)
            throws ServletException, IOException {
        
    	log.info("dans RestAuthenticationSuccessHandler.onAuthenticationSuccess");
    	
    	// try to find a corresponding user with a login
    	AppUser appUser = appUserRepository.findByLogin(authentication.getName());
        
    	// send this user back to the browser 
        SecurityUtils.sendResponse(response, HttpServletResponse.SC_OK, appUser);
    }
}
