package mil.dtic.cbes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import mil.dtic.cbes.model.BudgetCycle;
@Deprecated
public interface BudgetCycleRepository extends JpaRepository<BudgetCycle, Integer>  {
	public BudgetCycle getById(Integer id);
	
}
