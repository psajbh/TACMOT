package mil.dtic.cbes.utils.exceptions.security;

public interface SecurityExceptionMessageHolder {
    
    static final String NO_AUTHENTICATE_MSG = "[error : NOT AUTHENTICATED USER]";
    static final String NO_AUTHORIZED_MSG = "[error : NOT AUTHORIZED USER]";
    static final String FAILURE_TO_PROCESS_MSG = "[error : FAILURE TO PROCESS]";
    static final String TRANSFORM_ENTITY_FAILURE_MSG = "[error : FAILURE TRANSLATING ENTITY]";
    static final String TRANSFORM_DTO_FAILURE_MSG = "[error : FAILURE TRANSLATING DTO]";
    static final String INVALID_USER_CREDENTIAL_MSG = "[error : INVALID_USER_CREDENTIAL_MSG]";
    
    
    
    //supports TransformerException
    static final String TRANSFORM_DTO_TO_ENTITY_EXCEPTION = "Failed to transform object";
    static final String TRANSFORM_ENTITY_TO_DTO_EXCEPTION = "Failed to transform entity object";
    
    //supports UsernameNotFoundException
    static final String USER_NOT_FOUND_FAILURE = "Failed to authenticate user";
    
    //supports DataAccessException
    static final String USER_DTO_CAPTURE_FAILURE = "Failed to capture user object";
    static final String USER_ENTITY_CAPTURE_FAILURE = "Failed to capture user from database";
    
    //supports AccountNotFoundException
    static final String INVALID_PRINCIPAL = "Principal is NULL";
    static final String INVALID_AUTHORIZATION = "Authorization is NULL";
    static final String INVALID_USER_SECURITY = "User Security is NULL";
    
    //supports CxeNotAuthorizedException
    static final String CXE_NOT_AUTHORIZED = "LDAP id not in CXE database";
    
    //supports InvalidHeadersException
    static final String INVALID_HEADER_EXCEPTION_MSG = "Header anomaly issues";
    
    //supports LdapRetrievalFailureException
    static final String LDAP_EXCEPTION_FAILURE = "failed to connect to LDAP server";
    static final String LDAP_EXCEPTION_MSG = "Authentication Failure";
}
