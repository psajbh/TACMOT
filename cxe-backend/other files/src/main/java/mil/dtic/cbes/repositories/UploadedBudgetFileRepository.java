package mil.dtic.cbes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mil.dtic.cbes.model.UploadedBudgetFile;

public interface UploadedBudgetFileRepository extends JpaRepository<UploadedBudgetFile, Integer>{
	List<UploadedBudgetFile> findAllByOrderByDateCreatedAsc();
	List<UploadedBudgetFile> findAllByOrderByDateCreatedDesc();
	UploadedBudgetFile getById(Integer id);
}
