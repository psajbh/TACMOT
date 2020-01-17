package mil.dtic.cbes.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import mil.dtic.cbes.model.P1Data;
import mil.dtic.cbes.model.R1Data;
import mil.dtic.cbes.service.impl.PRCPDataServiceImpl.PrcpType;

public interface PRCPDataService {
	List<R1Data> getR1Data();
	
	List<P1Data> getP1Data();

	int addPrcpFile(MultipartFile uploadFile);

	void updateP1R1(int p1r1Type, List<String> mapHeaderValues);

	HashMap<String, String> loadMapping(int p1r1Type);

	void process(int type, String filename, int budgetYr);

	void updateP1R1Mapping(int type, String filename);

	void deletePRCPdata(PrcpType t);
}
