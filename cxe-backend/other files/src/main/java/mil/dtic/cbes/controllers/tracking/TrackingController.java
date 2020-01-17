package mil.dtic.cbes.controllers.tracking;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mil.dtic.cbes.controllers.BaseRestController;
import mil.dtic.cbes.model.TrackingData;
import mil.dtic.cbes.service.tracking.TrackingService;

@RestController
public class TrackingController extends BaseRestController {
	
	@Autowired
	private TrackingService trackingService;
	
	@GetMapping("tracking/gettrackingdata")
	public List<TrackingData> getR1Data(){
		return trackingService.getTrackingData();
	}
	
	@PostMapping("tracking/delete")
	public void deleteTrackingRecord(@RequestBody Integer id) {
		trackingService.deleteTrackingData(id);
	}

}
