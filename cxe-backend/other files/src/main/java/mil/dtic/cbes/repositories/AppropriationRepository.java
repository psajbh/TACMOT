package mil.dtic.cbes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import mil.dtic.cbes.model.Appropriation;

public interface AppropriationRepository extends JpaRepository<Appropriation, Integer> {
	public Appropriation getById(Integer id);
}
