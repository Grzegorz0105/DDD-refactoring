package com.grzegorzkartasiewicz;/*
 *
 *
 * @author Grzegorz Kartasiewicz
 *
 */



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@Component
class LoggerFilter implements Filter {
    Logger logger = LoggerFactory.getLogger(LoggerFilter.class);
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(servletRequest instanceof HttpServletRequest httpRequest){
            logger.info("[doFilter]{} {}",httpRequest.getMethod(), httpRequest.getRequestURI());
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

}
