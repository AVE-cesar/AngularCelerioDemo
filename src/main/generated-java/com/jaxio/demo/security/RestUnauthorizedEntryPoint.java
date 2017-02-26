package com.jaxio.demo.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Returns a 401 error code (Unauthorized) to the client.
 * 
 * We need a custom authenticationEntryPoint because default Spring-Security config will redirect to login page. 
 * In our case we need just a https status 401 and a json response.
 * 
 * @see SecurityConfig#configure(...)
 */
@Component
public class RestUnauthorizedEntryPoint implements AuthenticationEntryPoint {

	private final Logger log = LoggerFactory.getLogger(RestUnauthorizedEntryPoint.class);
	
    /**
     * Always returns a 401 error code to the client.
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException exception) throws IOException, ServletException {
    	
    	log.info("dans RestUnauthorizedEntryPoint.commence");
    	
    	// send a 401 back to the browser 
        SecurityUtils.sendError(response, exception, HttpServletResponse.SC_UNAUTHORIZED,
                "Authentication failed");
    }
}
