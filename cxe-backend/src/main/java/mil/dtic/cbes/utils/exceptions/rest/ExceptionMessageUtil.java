package mil.dtic.cbes.utils.exceptions.rest;

public interface ExceptionMessageUtil {
    
    static final String NO_AUTHENTICATE_MSG = "[error : NOT AUTHENTICATED USER]";
    static final String NO_AUTHORIZED_MSG = "[error : NOT AUTHORIZED USER]";
    static final String FAILURE_TO_PROCESS_MSG = "[error : FAILURE TO PROCESS]";
    static final String TRANSFORM_ENTITY_FAILURE_MSG = "[error : FAILURE TRANSLATING ENTITY]";
    static final String TRANSFORM_DTO_FAILURE_MSG = "[error : FAILURE TRANSLATING DTO]";
    static final String INVALID_USER_CREDENTIAL_MSG = "[error : INVALID_USER_CREDENTIAL_MSG]";
}
