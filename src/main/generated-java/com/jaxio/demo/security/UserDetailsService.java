package com.jaxio.demo.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.jaxio.demo.model.Authority;
import com.jaxio.demo.model.User;
import com.jaxio.demo.security.exception.UserNotEnabledException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    private static final List<User> allUsers = new ArrayList<User>();

    public UserDetailsService() {
    	super();
    	
    	if (allUsers.isEmpty()) {
	    	User user = new User();
	    	user.setId(1);
	    	user.setLogin("admin");
	    	user.setPassword("admin");
	    	user.setEnabled(true);
	    	Authority authority = new Authority();
	    	authority.setName("admin");
	    	Set<Authority> authorities = new HashSet<Authority>();
	    	authorities.add(authority);
	    	user.setAuthorities(authorities);
	    	
	    	allUsers.add(user);
    	}
    }
    
    public static User findByLogin(String login) {
    	User user = null;
        for (int i = 0; i < allUsers.size(); i++) {
			if (login.equals(allUsers.get(i).getLogin())) {
				user = allUsers.get(i);
				break;
			}
		}
        
        return user;
    }
    
    @Override
    public UserDetails loadUserByUsername(final String login) {
        log.debug("Authenticating {}", login);

        User user = findByLogin(login);
        
        if (user == null) {
            throw new UsernameNotFoundException("User " + login + " was not found in the database");
        } else if (!user.getEnabled()) {
            throw new UserNotEnabledException("User " + login + " was not enabled");
        }

        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        for (Authority authority : user.getAuthorities()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getName());
            grantedAuthorities.add(grantedAuthority);
        }

        return new org.springframework.security.core.userdetails.User(login, user.getPassword(),
                grantedAuthorities);
    }
}
