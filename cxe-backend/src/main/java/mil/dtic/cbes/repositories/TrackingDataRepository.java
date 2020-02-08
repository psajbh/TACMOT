package mil.dtic.cbes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import mil.dtic.cbes.model.TrackingData;

public interface TrackingDataRepository extends JpaRepository<TrackingData, Integer> {
	public TrackingData getById(Integer id);
}
