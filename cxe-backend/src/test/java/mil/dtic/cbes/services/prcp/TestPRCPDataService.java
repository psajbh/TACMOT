//package mil.dtic.cbes.services.prcp;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.rules.ExpectedException;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.context.support.ReloadableResourceBundleMessageSource;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import mil.dtic.cbes.model.dto.user.UserDto;
//import mil.dtic.cbes.model.entities.IEntity;
//import mil.dtic.cbes.model.entities.P1DataEntity;
//import mil.dtic.cbes.model.entities.R1DataEntity;
//import mil.dtic.cbes.model.enums.PRCPType;
//import mil.dtic.cbes.model.enums.Role;
//import mil.dtic.cbes.repositories.P1DataRepository;
//import mil.dtic.cbes.repositories.R1DataRepository;
//import mil.dtic.cbes.service.BudgetFileUploadService;
//import mil.dtic.cbes.service.impl.PRCPDataServiceImpl;
//import mil.dtic.cbes.service.prcp.PRCPDataService;
//import mil.dtic.cbes.service.prcp.PRCPFileService;
//import mil.dtic.cbes.utils.exceptions.rest.PRCPDataProcessingException;
//import mil.dtic.cbes.utils.exceptions.rest.PRCPUnknownTypeException;
//import mil.dtic.cbes.utils.exceptions.rest.user.NotAuthorizedException;
//import mil.dtic.cbes.utils.exceptions.service.TransformerException;
//import mil.dtic.cbes.utils.processors.PRCPFileProcessor;
//import mil.dtic.cbes.utils.transform.impl.PRCPP1DataTableTransformer;
//import mil.dtic.cbes.utils.transform.impl.PRCPR1DataTableTransformer;
//
//@RunWith(SpringRunner.class)
//public class TestPRCPDataService {
//
//	@Mock
//	private PRCPFileProcessor prcpFileProcessor;
//	
//	@Mock
//	private BudgetFileUploadService budgetFileUploadService;
//	
//	@Mock
//	private R1DataRepository r1Repo;
//
//	@Mock
//	private P1DataRepository p1Repo;
//	
//	@Mock
//	private PRCPP1DataTableTransformer prcpP1DataTableTransformer;
//	
//	@Mock
//	private PRCPR1DataTableTransformer prcpR1DataTableTransformer;
//	
//	@Rule
//	public ExpectedException thrown = ExpectedException.none();
//	
//	private PRCPDataService prcpDataService;
//	
//	@Before
//	public void setUp() {
//		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//		messageSource.setBasename("classpath:messages");
//		messageSource.setCacheSeconds(10);
//				
//		prcpDataService = new PRCPDataServiceImpl(budgetFileUploadService, messageSource, prcpP1DataTableTransformer,
//				prcpR1DataTableTransformer, r1Repo, p1Repo, prcpFileProcessor);
//	}
//	
//	@Test
//	public void testNoR1DataFound() {
//		Mockito.when(r1Repo.findAll()).thenReturn(new ArrayList<>());
//		
//		thrown.expect(PRCPDataProcessingException.class);
//		thrown.expectMessage("No R1 data was found.");
//		
//		UserDto userDto = new UserDto();
//		userDto.setRole(Role.GROUP_R2_APP_ADMIN.getName());
//		
//		prcpDataService.getR1Data(userDto);
//	}
//	
//	@Test
//	public void testR1DataTransformationFailure() {
//		List<R1DataEntity> list = Arrays.asList(new R1DataEntity());
//		
//		Mockito.when(r1Repo.findAll()).thenReturn(list);
//		
//		Mockito.when(prcpR1DataTableTransformer.transform(Mockito.any(IEntity.class))).thenThrow(TransformerException.class);
//		
//		thrown.expect(PRCPDataProcessingException.class);
//		thrown.expectMessage("Unable to process R1 data due to internal error.");
//		
//		UserDto userDto = new UserDto();
//		userDto.setRole(Role.GROUP_R2_APP_ADMIN.getName());
//		
//		prcpDataService.getR1Data(userDto);
//	}
//	
//	@Test
//	public void testNoP1DataFound() {
//		Mockito.when(p1Repo.findAll()).thenReturn(new ArrayList<>());
//		
//		thrown.expect(PRCPDataProcessingException.class);
//		thrown.expectMessage("No P1 data was found.");
//		
//		UserDto userDto = new UserDto();
//		userDto.setRole(Role.GROUP_R2_APP_ADMIN.getName());
//		
//		prcpDataService.getP1Data(userDto);
//	}
//	
//	@Test
//	public void testP1DataTransformationFailure() {
//		List<P1DataEntity> list = Arrays.asList(new P1DataEntity());
//		
//		Mockito.when(p1Repo.findAll()).thenReturn(list);
//		
//		Mockito.when(prcpP1DataTableTransformer.transform(Mockito.any(IEntity.class))).thenThrow(TransformerException.class);
//		
//		thrown.expect(PRCPDataProcessingException.class);
//		thrown.expectMessage("Unable to process P1 data due to internal error.");
//		
//		UserDto userDto = new UserDto();
//		userDto.setRole(Role.GROUP_R2_APP_ADMIN.getName());
//		
//		prcpDataService.getP1Data(userDto);
//	}
//	
//	@Test
//	public void testUserNotAuthorizedDeleteR1Data() {
//		thrown.expect(NotAuthorizedException.class);
//		thrown.expectMessage("You are not authorized to delete PRCP data.");
//		
//		UserDto userDto = new UserDto();
//		userDto.setRole(Role.GROUP_R2_SITEADMIN.getName());
//		
//		prcpDataService.deletePRCPData(PRCPType.R1, userDto);
//	}
//	
//	@Test
//	public void testUserNotAuthorizedDeleteP1Data() {
//		thrown.expect(NotAuthorizedException.class);
//		thrown.expectMessage("You are not authorized to delete PRCP data.");
//		
//		UserDto userDto = new UserDto();
//		userDto.setRole(Role.GROUP_R2_SITEADMIN.getName());
//		
//		prcpDataService.deletePRCPData(PRCPType.P1, userDto);
//	}
//}
