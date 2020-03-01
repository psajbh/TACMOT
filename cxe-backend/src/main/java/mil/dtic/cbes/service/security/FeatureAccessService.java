package mil.dtic.cbes.service.security;

import java.util.List;

import mil.dtic.cbes.model.entities.security.FeatureEntity;

public interface FeatureAccessService {
    
    List<FeatureEntity> getAllFeatures();

}
