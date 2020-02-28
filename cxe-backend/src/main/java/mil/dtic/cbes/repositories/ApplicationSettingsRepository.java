package mil.dtic.cbes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import mil.dtic.cbes.model.ApplicationSettings;

public interface ApplicationSettingsRepository extends JpaRepository<ApplicationSettings, Integer> {

}
