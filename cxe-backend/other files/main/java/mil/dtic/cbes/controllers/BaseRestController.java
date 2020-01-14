package mil.dtic.cbes.controllers;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import mil.dtic.cbes.model.dto.UserCredential;
import mil.dtic.cbes.utils.aspect.CredentialsAspect;


/**
 * Maps all REST controllers with a base context
 *
 */
@Component
@RequestMapping("/api")
public class BaseRestController{

    protected UserCredential getCredential() {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
        return (UserCredential) request.getAttribute(CredentialsAspect.CREDENTIAL_KEY);
    }
    
}
