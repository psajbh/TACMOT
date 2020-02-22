package mil.dtic.cbes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import mil.dtic.cbes.model.Agency;

public interface AgencyRepository extends JpaRepository<Agency, Integer> {
	public Agency getById(Integer id);

}
