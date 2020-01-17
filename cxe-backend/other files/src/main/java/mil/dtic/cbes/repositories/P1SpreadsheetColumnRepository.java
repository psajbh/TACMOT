package mil.dtic.cbes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mil.dtic.cbes.model.P1SpreadsheetColumn;

public interface P1SpreadsheetColumnRepository extends JpaRepository<P1SpreadsheetColumn, Integer>{
	List<P1SpreadsheetColumn> findAllByOrderByColumnOrderAsc();
}
