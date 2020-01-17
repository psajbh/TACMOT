package mil.dtic.cbes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import mil.dtic.cbes.model.ProgramElement;

public interface ProgramElementRepository extends JpaRepository<ProgramElement, Integer>{

}
