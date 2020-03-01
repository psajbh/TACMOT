package mil.dtic.cbes.service.impl.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mil.dtic.cbes.model.entities.security.FeatureEntity;
import mil.dtic.cbes.repositories.user.FeatureAccessRepository;
import mil.dtic.cbes.service.security.FeatureAccessService;

@Service
public class FeatureAccessServiceImpl implements FeatureAccessService {
    
    @Autowired
    FeatureAccessRepository featureAccessRepository;

    @Override
    public List<FeatureEntity> getAllFeatures(){
        return featureAccessRepository.findAll();
    }

}
