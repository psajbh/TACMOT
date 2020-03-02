package mil.dtic.cbes.repositories.core;

import org.springframework.data.jpa.repository.JpaRepository;

import mil.dtic.cbes.model.entities.core.BudgetActivityEntity;


public interface BudgetActivityRepository extends JpaRepository<BudgetActivityEntity, Integer>{

}
