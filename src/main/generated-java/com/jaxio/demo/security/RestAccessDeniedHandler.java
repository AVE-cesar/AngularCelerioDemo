package com.jaxio.demo.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The access denied handler returns http status 403.
 * @author egd
 *
 */
@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException exception) throws IOException, ServletException {
    	
    	log.info("dans RestAccessDeniedHandler.handle");
    	
    	// send a 403 back to the browser 
        SecurityUtils.sendError(response, exception, HttpServletResponse.SC_FORBIDDEN, "Not authorized resources");
    }
}
