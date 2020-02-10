//package mil.dtic.cbes.services.prcp;
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
//import mil.dtic.cbes.model.dto.UserDto;
//import mil.dtic.cbes.model.enums.PRCPType;
//import mil.dtic.cbes.model.enums.Role;
//import mil.dtic.cbes.service.impl.PRCPFileServiceImpl;
//import mil.dtic.cbes.service.prcp.PRCPFileService;
//import mil.dtic.cbes.utils.exceptions.rest.PRCPFileProcessingException;
//import mil.dtic.cbes.utils.exceptions.rest.user.NotAuthorizedException;
//import mil.dtic.cbes.utils.processors.PRCPDataProcessor;
//import mil.dtic.cbes.utils.processors.PRCPFileProcessor;
//
//@RunWith(SpringRunner.class)
//public class TestPRCPFileService {
//
//	@Mock
//	private PRCPFileProcessor prcpFileProcessor;
//
//	@Mock 
//	private PRCPDataProcessor prcpDataProcessor;
//	
//	@Rule
//	public ExpectedException thrown = ExpectedException.none();
//	
//	private PRCPFileService prcpFileService;
//	
//	@Before
//	public void setUp() {
//		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//		messageSource.setBasename("classpath:messages");
//		messageSource.setCacheSeconds(10);
//		
//		prcpFileService = new PRCPFileServiceImpl(prcpFileProcessor, messageSource,prcpDataProcessor);
//	}
//	
//	@Test
//	public void testGetPRCPDataFileNullResponse() {		
//		Mockito.when(prcpFileProcessor.getPrcpDataFor(Mockito.any(PRCPType.class))).thenReturn(null);
//		
//		thrown.expect(PRCPFileProcessingException.class);
//		thrown.expectMessage("The PRCP file was unable to be processed for download.");
//		
//		UserDto userDto = new UserDto();
//		userDto.setRole(Role.GROUP_R2_APP_ADMIN.getName());
//		
//		prcpFileService.getPrcpDataFile(PRCPType.P1, userDto);
//	}
//	
//	@Test
//	public void testGetPRCPDataFileUserNotAuthorized() {
//		thrown.expect(NotAuthorizedException.class);
//		thrown.expectMessage("You are not authorized to download P1 PRCP file.");
//		
//		UserDto userDto = new UserDto();
//		userDto.setRole(Role.GROUP_R2_SITEADMIN.getName());
//		
//		prcpFileService.getPrcpDataFile(PRCPType.P1, userDto);
//	}
//}
