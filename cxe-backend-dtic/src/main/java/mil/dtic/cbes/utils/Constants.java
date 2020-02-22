package mil.dtic.cbes.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mil.dtic.cbes.repositories.ConfigRepository;

@Service
public final class Constants {
    @Autowired
    private ConfigRepository configRepo;

    public String getConfigValueByName(String name) {
        return configRepo.findByName(name).getValue();
    }
    
}
