package com.jaxio.demo.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.jaxio.demo.domain.AppAuthority;
import com.jaxio.demo.domain.AppUser;
import com.jaxio.demo.repository.AppUserRepository;
import com.jaxio.demo.security.exception.UserNotEnabledException;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    @Inject
    private AppUserRepository appUserRepository;
    
    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {
        log.debug("Authenticating {}", login);

        AppUser appUser = appUserRepository.findByLogin(login);
        
        if (appUser == null) {
            throw new UsernameNotFoundException("User " + login + " was not found in the database");
        } else if (appUser.getEnabled() != 1) {
            throw new UserNotEnabledException("User " + login + " was not enabled");
        }

        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        for (AppAuthority appAuthority : appUser.getAppAuthorities()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(appAuthority.getName());
            grantedAuthorities.add(grantedAuthority);
            
            log.debug("add: " + appAuthority.getName() + ", to user with login: " + login);
        }
        
        return new org.springframework.security.core.userdetails.User(login, appUser.getPassword(),
                grantedAuthorities);
    }
}
