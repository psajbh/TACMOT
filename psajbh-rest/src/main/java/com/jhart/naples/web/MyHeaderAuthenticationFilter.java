package com.jhart.naples.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyHeaderAuthenticationFilter extends RequestHeaderAuthenticationFilter {

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
    	log.debug("getPreAuthenticatedPrincipal-");
    	return null;
    }

}
