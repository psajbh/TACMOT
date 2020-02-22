package mil.dtic.cbes.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mil.dtic.cbes.model.entities.UserEntity;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Integer>{
    UserEntity findByUserLdapId(String userLdapId);
}





