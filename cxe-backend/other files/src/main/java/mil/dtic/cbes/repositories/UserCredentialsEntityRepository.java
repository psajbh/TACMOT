package mil.dtic.cbes.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mil.dtic.cbes.model.entities.UserCredentialsEntity;

public interface UserCredentialsEntityRepository extends CrudRepository<UserCredentialsEntity, Integer>{
    List<UserCredentialsEntity> findByLdapId(String ldapId);
}
