package mil.dtic.cbes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import mil.dtic.cbes.model.entities.UserEntity;

public interface UserEntityRepository extends JpaRepository<UserEntity, Integer>{
    
    UserEntity findByUserLdapId(String userLdapId);
    UserEntity findByUsername(String userName);
}





