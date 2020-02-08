package mil.dtic.cbes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mil.dtic.cbes.model.UploadedBudgetFile;

public interface UploadedBudgetFileRepository extends JpaRepository<UploadedBudgetFile, Integer>{
	List<UploadedBudgetFile> findAllByOrderByDateCreatedAsc();
	
	List<UploadedBudgetFile> findAllByOrderByDateCreatedDesc();
	
	UploadedBudgetFile getById(Integer id);
	
	@Query(value = "SELECT u FROM UploadedBudgetFile u WHERE u.type = :fileType")
	public List<UploadedBudgetFile> findAllByType(@Param("fileType") String fileType);
}
