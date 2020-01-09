package mil.dtic.cbes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import mil.dtic.cbes.model.Config;

public interface ConfigRepository extends JpaRepository<Config, Integer>{
	Config findByName(String name);
}
