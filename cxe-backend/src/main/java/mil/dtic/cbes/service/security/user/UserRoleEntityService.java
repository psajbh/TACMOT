package mil.dtic.cbes.service.security.user;

import java.util.List;

import mil.dtic.cbes.model.entities.security.RoleEntity;

public interface UserRoleEntityService {
    List<RoleEntity> findAll();

}
