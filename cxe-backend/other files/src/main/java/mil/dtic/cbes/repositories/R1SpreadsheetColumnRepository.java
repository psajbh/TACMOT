package mil.dtic.cbes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mil.dtic.cbes.model.R1SpreadsheetColumn;

public interface R1SpreadsheetColumnRepository extends JpaRepository<R1SpreadsheetColumn, Integer>{
	List<R1SpreadsheetColumn> findAllByOrderByColumnOrderAsc();
}
