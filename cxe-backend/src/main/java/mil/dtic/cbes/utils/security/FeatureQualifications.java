package mil.dtic.cbes.utils.security;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import mil.dtic.cbes.model.dto.security.UserCredentialDto;
import mil.dtic.cbes.model.entities.security.FeatureEntity;
import mil.dtic.cbes.service.security.FeatureAccessService;
import mil.dtic.cbes.utils.exceptions.security.FeatureNotFoundException;
import mil.dtic.cbes.utils.exceptions.security.InvalidCredentialException;

@Component
public class FeatureQualifications {
    private static final Logger log = LoggerFactory.getLogger(FeatureQualifications.class);
    private static final String USER_CREDENTIAL_NULL = "user credential null";
    private static final String  USER_CREDENTIAL_LDAP_ID_NULL = "user credential ldapId null";
    
    private Map<String, FeatureEntity> featureQuals;
    
    @Autowired
    private FeatureAccessService featureAccessService;
    
    public FeatureQualifications(FeatureAccessService featureAccessService) {
        this.featureAccessService = featureAccessService;
    }
    
    @PostConstruct
    private void postConstruct() {
        List<FeatureEntity> features = featureAccessService.getAllFeatures();
        init(features);
    }
    
    private void init(List<FeatureEntity> features) {
        featureQuals = features.stream().collect(Collectors.toMap(FeatureEntity::getPointCut,Function.identity()));
    }
    
    public boolean authorizeCredentialWithFeature(UserCredentialDto userCredential, String feature) throws FeatureNotFoundException{
        
        if (null == userCredential || null == userCredential.getLdapId()) {
            String msg = (null == userCredential) ? FeatureQualifications.USER_CREDENTIAL_NULL : FeatureQualifications.USER_CREDENTIAL_LDAP_ID_NULL;
            log.error("authorizeCredentialWithFeature - "+msg);
            throw new InvalidCredentialException(InvalidCredentialException.INVALID_USER_CREDENTIAL_MSG);
        }
        
        FeatureEntity featureEntity = featureQuals.get(feature);
        
        if (null == featureEntity) {
            log.error("authorizeCredentialWithFeature- feature not found - feature: "+feature);
            throw new FeatureNotFoundException(FeatureNotFoundException.FEATURE_NOT_SUPPORTED);
        }
        
        boolean rValue = processCredentialsWithFeature(getCredentialQual(userCredential),featureEntity);
        
        if (rValue) {
        	log.trace("authorizeCredentialWithFeature- authorization success for: "+userCredential.getLdapId()+" feature: "+featureEntity.getPointCut());
        }
        else {
        	log.warn("authorizeCredentialWithFeature- authorization failure for: "+userCredential.getLdapId()+" feature: "+featureEntity.getPointCut());
        }
        
        
        return rValue;
    }
    
    private boolean processCredentialsWithFeature(Integer credentialQual, FeatureEntity featureEntity) {
        log.trace("authorizeCredentialWithFeature- credentialQual: " + credentialQual);
        
        boolean rValue = false;
        
        if (featureEntity.getEqualLogic().isEqualTo()) {
            log.trace("authorizeCredentialWithFeature- equalLogic: true");
            if (credentialQual.equals(featureEntity.getFeatureQual())){
                rValue = true;
            }
        }
        else {
            log.trace("authorizeCredentialWithFeature- equalLogic: false");
            if (credentialQual >= featureEntity.getFeatureQual()) {
                rValue = true;
            }
        }
        
        return rValue;
    }
    
    private Integer getCredentialQual(UserCredentialDto userCredential) {
        
        if (userCredential.getUserRole().equals(UserCredentialDto.GROUP_R2_APP_ADMIN)) {
            return QualificationHierarchy.APP_MGR;
        }
        
        if (userCredential.getUserRole().equals(UserCredentialDto.GROUP_R2_SITEADMIN)) {
            return QualificationHierarchy.SITE_MGR;
        }
        
        if (userCredential.getUserRole().equals(UserCredentialDto.GROUP_R2_LOCALSITEADMIN)) {
            return QualificationHierarchy.LOCAL_SITE_MGR;
        }

        if (userCredential.getUserRole().equals(UserCredentialDto.GROUP_R2_USER)) {
            return QualificationHierarchy.USER;
        }

        if (userCredential.getUserRole().equals(UserCredentialDto.GROUP_R2_ANALYST)) {
            return QualificationHierarchy.ANALYST;
        }
        
        else {
            return -1;
        }
    }
    
}
