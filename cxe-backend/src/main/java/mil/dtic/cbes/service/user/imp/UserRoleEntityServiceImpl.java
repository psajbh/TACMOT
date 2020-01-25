package mil.dtic.cbes.service.user.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mil.dtic.cbes.model.entities.RoleEntity;
import mil.dtic.cbes.repositories.RoleEntityRepository;
import mil.dtic.cbes.service.user.api.UserRoleEntityService;

@Service
public class UserRoleEntityServiceImpl implements UserRoleEntityService{
    
    @Autowired
    private RoleEntityRepository roleEntityRepository;
    
    public UserRoleEntityServiceImpl(RoleEntityRepository roleEntityRepository) {
        this.roleEntityRepository = roleEntityRepository;
    }
    
    public List<RoleEntity> findAll() {
        List<RoleEntity> roles = roleEntityRepository.findAll();
        return roles;
    }

}
