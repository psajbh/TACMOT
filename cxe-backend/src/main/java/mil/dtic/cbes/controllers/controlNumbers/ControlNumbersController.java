package mil.dtic.cbes.controllers.controlNumbers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mil.dtic.cbes.controllers.BaseRestController;
import mil.dtic.cbes.model.Agency;
import mil.dtic.cbes.model.P40ControlNumber;
import mil.dtic.cbes.model.ProgramElement;
import mil.dtic.cbes.repositories.AgencyRepository;
import mil.dtic.cbes.repositories.P40ControlNumberRepository;
import mil.dtic.cbes.repositories.ProgramElementRepository;
import mil.dtic.cbes.service.controlNumbers.ControlNumbersService;

@RestController
public class ControlNumbersController extends BaseRestController{
	@Autowired
	private ControlNumbersService ctrlNumService;
	
	@Autowired
	private ProgramElementRepository programElementRepo;
	
	@Autowired
	private P40ControlNumberRepository p40ctrlNumRepo;
	
	@Autowired
	private AgencyRepository agencyRepo;
	
	@GetMapping("/controlnumbers/getR2controlnumbers")
	public List<ProgramElement> getAllR2programElements() {
		return programElementRepo.findAll();
	}
	
	@GetMapping("/controlnumbers/getServiceAgencys")
	public List<Agency> getAllServiceAgencys() {
		return agencyRepo.findAll();
	}
	
	@PostMapping("/controlnumbers/saveData")
	public void saveControlNumbers(@RequestBody ArrayList<ProgramElement> pe) {
		programElementRepo.saveAll(pe);
	}
	
	@GetMapping("/controlnumbers/getP40controlnumbers")
	public List<P40ControlNumber> getAllP40controlNumbers() {
		return p40ctrlNumRepo.findAll();
	}
	
	@PostMapping("/controlnumbers/saveP40data")
	public void saveP40ControlNumbers(@RequestBody ArrayList<P40ControlNumber> ctrlNums) {
		p40ctrlNumRepo.saveAll(ctrlNums);
	}
	
}
