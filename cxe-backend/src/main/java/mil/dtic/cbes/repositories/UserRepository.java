package mil.dtic.cbes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import mil.dtic.cbes.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	User getById(Integer id);
}
