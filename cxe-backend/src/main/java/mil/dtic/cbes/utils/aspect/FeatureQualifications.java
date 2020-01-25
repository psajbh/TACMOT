package mil.dtic.cbes.utils.aspect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mil.dtic.cbes.model.dto.UserCredentialDto;
import mil.dtic.cbes.utils.exceptions.rest.ExceptionMessageUtil;
import mil.dtic.cbes.utils.exceptions.rest.FeatureNotFoundException;
import mil.dtic.cbes.utils.exceptions.rest.InvalidCredentialException;

@SuppressWarnings("unused")
public class FeatureQualifications {
    private static final Logger log = LoggerFactory.getLogger(FeatureQualifications.class);
    private static Map<String, Integer> featureQual  = new HashMap<>();
    private static List<String> featureTypeEquals = new ArrayList<>();
    
    private static final Integer ANY = 0;
    private static final Integer ANALYST = 1;
    private static final Integer USER = 2;
    private static final Integer LOCAL_SITE_MGR = 3;
    private static final Integer SITE_MGR = 4; 
    private static final Integer APP_MGR = 5; 
    
    private static FeatureQualifications featureQualifications;
    
    static {
        featureQualifications = new FeatureQualifications();
        init();
    }
    
    public static FeatureQualifications getInstance() {
        return featureQualifications;
    }
    
    private static void init() {
        featureQual.put("execution(CxeSecurityController.getUser(..))",FeatureQualifications.ANY);
        featureQual.put("execution(ManageUsersController.deleteManagedUser(..))",FeatureQualifications.APP_MGR);
        featureQual.put("execution(ManageUsersController.addManagedUser(..))",FeatureQualifications.APP_MGR);
        featureQual.put("execution(ManageUsersController.updateManagedUser(..))",FeatureQualifications.APP_MGR);
        featureQual.put("execution(ManageUsersController.getManagedUsers())",FeatureQualifications.LOCAL_SITE_MGR);
        featureQual.put("execution(UserProfileController.getProfile())",FeatureQualifications.ANALYST);
        featureQual.put("execution(AnnouncementController.getAnnouncement())",FeatureQualifications.ANALYST);
        featureQual.put("execution(DownloadsController.getDownloadsList(..))",FeatureQualifications.USER);
        featureQual.put("execution(DownloadsController.downloadFile(..))",FeatureQualifications.USER);
        featureQual.put("execution(DownloadsController.deleteFile(..))",FeatureQualifications.USER);
        featureQual.put("execution(DownloadsController.uploadFile(..))",FeatureQualifications.USER);
        featureQual.put("execution(UserGuideController.getUserGuideHTML())",FeatureQualifications.ANALYST);
    }
    
    public static boolean authorizeCredentialWithFeature(UserCredentialDto userCredential, String feature) throws FeatureNotFoundException{
        if (null == userCredential || null == userCredential.getLdapId()) {
            String msg = (null == userCredential)?"user credential null":"user credential ldapId null";
            log.error("authorizeCredentialWithFeature - " + msg);
            throw new InvalidCredentialException(ExceptionMessageUtil.INVALID_USER_CREDENTIAL_MSG);
        }
        
        Integer qualValue = null;
        
        try {
            qualValue = featureQual.get(feature); 
            if (null == qualValue) {
                throw new FeatureNotFoundException("feature not found.  feature: "+feature);
            }
        }
        finally {
            log.trace("authorizeCredentialWithFeature- feature successfully accessed: "+feature);
        }
        
        Integer credentialQual = getCredentialQual(userCredential);
        
        if (credentialQual >= qualValue) {
            return true;
        }
        
        log.warn("credential for: "+userCredential.getLdapId()+"not authorized for feature: "+feature);
        return false;
    }
    
    private static Integer getCredentialQual(UserCredentialDto userCredential) {
        
        if (userCredential.getUserRole().equals(UserCredentialDto.GROUP_R2_APP_ADMIN)) {
            return FeatureQualifications.APP_MGR;
        }
        
        if (userCredential.getUserRole().equals(UserCredentialDto.GROUP_R2_SITEADMIN)) {
            return FeatureQualifications.SITE_MGR;
        }
        
        if (userCredential.getUserRole().equals(UserCredentialDto.GROUP_R2_LOCALSITEADMIN)) {
            return FeatureQualifications.LOCAL_SITE_MGR;
        }

        if (userCredential.getUserRole().equals(UserCredentialDto.GROUP_R2_USER)) {
            return FeatureQualifications.USER;
        }

        if (userCredential.getUserRole().equals(UserCredentialDto.GROUP_R2_ANALYST)) {
            return FeatureQualifications.ANALYST;
        }
        
        else {
            return -1;
        }
    }
    
}
