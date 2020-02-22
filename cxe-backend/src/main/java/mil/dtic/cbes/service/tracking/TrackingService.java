package mil.dtic.cbes.service.tracking;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mil.dtic.cbes.model.TrackingData;
import mil.dtic.cbes.repositories.TrackingDataRepository;

@Service
public class TrackingService {
	@Autowired
	private TrackingDataRepository trackingDataRepository;
	
	public List<TrackingData> getTrackingData() {
		 List<TrackingData> trackingData = trackingDataRepository.findAll();	
		 
		 for (Iterator<TrackingData> iter = trackingData.listIterator(); iter.hasNext();) {
			 if (iter.next().getActiveFlag() == 0) {
				 iter.remove();
			 }
		 }
		 return trackingData;
	}
	
	public void deleteTrackingData(Integer id) {
		TrackingData trackingData = trackingDataRepository.getById(id);
		trackingData.setActiveFlag(0);
		trackingDataRepository.save(trackingData);
	};	
}
