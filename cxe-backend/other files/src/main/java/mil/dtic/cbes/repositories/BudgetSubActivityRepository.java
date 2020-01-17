package mil.dtic.cbes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import mil.dtic.cbes.model.BudgetSubActivity;


public interface BudgetSubActivityRepository extends JpaRepository<BudgetSubActivity, Integer> {
	public BudgetSubActivity getById(Integer id);
}
