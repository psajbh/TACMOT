package mil.dtic.cbes.utils.aspect;

import java.util.HashMap;
import java.util.Map;

import mil.dtic.cbes.model.dto.UserCredential;


public class FeatureQualifications {

    private static Map<String, Integer> featureQual  = new HashMap<>();
    
    private static final Integer LEVEL_ONE = 1; //Anaylst
    private static final Integer LEVEL_TWO = 2; //User
    private static final Integer LEVEL_THREE = 3; //LocalSitMgr
    private static final Integer LEVEL_FOUR = 4; //SiteMgr
    private static final Integer LEVEL_FIVE = 5; // AppMgr
    
    private static FeatureQualifications featureQualifications;
    
    static {
        featureQualifications = new FeatureQualifications();
        init();
    }
    
    public static FeatureQualifications getInstance() {
        return featureQualifications;
    }
    
    private static void init() {
        featureQual.put("execution(ManageUsersController.deleteManagedUser(..))",FeatureQualifications.LEVEL_FIVE);
        featureQual.put("execution(ManageUsersController.addManagedUser(..))",FeatureQualifications.LEVEL_FIVE);
        featureQual.put("execution(ManageUsersController.updateManagedUser(..))",FeatureQualifications.LEVEL_FIVE);
        featureQual.put("execution(ManageUsersController.getManagedUsers())",FeatureQualifications.LEVEL_THREE);
        featureQual.put("execution(UserProfileController.getProfile(..))",FeatureQualifications.LEVEL_ONE);
        //feature for only Analysts will be HARD Level 5 which means will be equal not equal and above.
    }
    
    public static boolean authorizeCredentialWithFeature(UserCredential userCredential, String feature) {
        Integer qualValue = featureQual.get(feature);
        Integer credentialQual = getCredentialQual(userCredential);
        
        if (credentialQual >= qualValue) {
            return true;
        }
        
        //log.debug(String.format("credential for: %s not authorized for feature: %s",userCredential.getLdapId(), feature));
        return false;
        
    }
    
    private static Integer getCredentialQual(UserCredential userCredential) {
        
        if (userCredential.getUserRole().equals(UserCredential.GROUP_R2_APP_ADMIN)) {
            return FeatureQualifications.LEVEL_FIVE;
        }
        
        if (userCredential.getUserRole().equals(UserCredential.GROUP_R2_SITEADMIN)) {
            return FeatureQualifications.LEVEL_FOUR;
        }
        
        if (userCredential.getUserRole().equals(UserCredential.GROUP_R2_LOCALSITEADMIN)) {
            return FeatureQualifications.LEVEL_THREE;
        }

        if (userCredential.getUserRole().equals(UserCredential.GROUP_R2_USER)) {
            return FeatureQualifications.LEVEL_TWO;
        }

        if (userCredential.getUserRole().equals(UserCredential.GROUP_R2_ANALYST)) {
            return FeatureQualifications.LEVEL_ONE;
        }
        
        else {
            return -1;
        }
        
    }
    
    
}
