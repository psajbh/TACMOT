package mil.dtic.cbes.repositories.security.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mil.dtic.cbes.model.entities.views.security.UserCredentialsEntity;

@Repository
public interface UserCredentialsEntityRepository extends JpaRepository<UserCredentialsEntity, Integer>{
    List<UserCredentialsEntity> findByLdapId(String ldapId);
}
