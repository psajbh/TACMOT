package mil.dtic.cbes.utils.transform.impl.exhibit.r2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mil.dtic.cbes.model.dto.IDto;
import mil.dtic.cbes.model.dto.core.BudgetActivityDto;
import mil.dtic.cbes.model.dto.core.ServiceAgencyDto;
import mil.dtic.cbes.model.dto.exhibit.r2.ProgramElementDto;
import mil.dtic.cbes.model.dto.security.UserDto;
import mil.dtic.cbes.model.entities.IEntity;
import mil.dtic.cbes.model.entities.core.BudgetActivityEntity;
import mil.dtic.cbes.model.entities.core.ServiceAgencyEntity;
import mil.dtic.cbes.model.entities.exhibit.r2.ProgramElementEntity;
import mil.dtic.cbes.model.entities.security.UserEntity;
import mil.dtic.cbes.utils.exceptions.transform.TransformerException;
import mil.dtic.cbes.utils.transform.Transformer;
import mil.dtic.cbes.utils.transform.impl.UserTransformer;
import mil.dtic.cbes.utils.transform.impl.budgetactivity.R2BudgetActivityTransformer;
import mil.dtic.cbes.utils.transform.impl.serviceagency.ServiceAgencyTransformer;

@Component
public class ProgramElementTransformer implements Transformer {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private R2BudgetActivityTransformer budgetActivityTransformer;
	
	@Autowired
	private ServiceAgencyTransformer serviceAgencyTransformer;
	
	@Autowired
	private UserTransformer userTransformer;
	
	@Override
	public ProgramElementDto transform(IEntity entity) throws TransformerException {
		log.trace("transform - programElementEntity");
		ProgramElementEntity programElementEntity = (ProgramElementEntity) entity;
		
        if (null == programElementEntity) {
            throw new TransformerException(TransformerException.TRANSFORM_ENTITY_NULL);
        }
        
        if (null != programElementEntity.getId()) {
        	ProgramElementDto programElementDto = new ProgramElementDto();
        	
        	programElementDto.setId(programElementEntity.getId());
        	programElementDto.setPeNumber(programElementEntity.getPeNumber());
        	programElementDto.setBudgetCycle(programElementEntity.getBudgetCycle());
        	programElementDto.setBudgetYear(programElementEntity.getBudgetYear());
        	programElementDto.setR1Number(programElementEntity.getR1Number());
        	programElementDto.setSubmissionDate(programElementEntity.getSubmissionDate());
        	programElementDto.setPeTitle(programElementEntity.getPeTitle());
        	
        	BudgetActivityEntity budgetActivityEntity = programElementEntity.getBudgetActivityEntity();
        	BudgetActivityDto budgetActivityDto = budgetActivityTransformer.transform(budgetActivityEntity);
        	programElementDto.setBudgetActivityDto(budgetActivityDto);
        	
        	ServiceAgencyEntity serviceAgencyEntity = programElementEntity.getServiceAgencyEntity();
        	ServiceAgencyDto serviceAgencyDto = serviceAgencyTransformer.transform(serviceAgencyEntity);
        	programElementDto.setServiceAgencyDto(serviceAgencyDto);
        	

        	programElementDto.setPeTag(programElementEntity.getPeTag());
        	programElementDto.setPeTest(programElementDto.getPeTest());
        	programElementDto.setPeEditableSw(programElementEntity.getPeEditableSw());
        	programElementDto.setPeStatusSubmission(programElementEntity.getPeStatusSubmission());
        	
        	programElementDto.setPeState(programElementEntity.getPeState());
        	programElementDto.setPeInitSrc(programElementEntity.getPeInitSrc());
        	programElementDto.setPeFormat(programElementEntity.getPeFormat());
        	programElementDto.setDateCreated(programElementEntity.getDateCreated());
        	programElementDto.setPeMdap(programElementEntity.getPeMdap());
        	programElementDto.setPeCompCost(programElementEntity.getPeCompCost());
        	programElementDto.setPeTotalCost(programElementEntity.getPeTotalCost());
        	programElementDto.setPeApy(programElementEntity.getPeApy());
        	programElementDto.setPePy(programElementEntity.getPePy());
        	programElementDto.setPeCy(programElementEntity.getPeCy());
        	programElementDto.setPeBy1(programElementEntity.getPeBy1());
        	programElementDto.setPeBy1Base(programElementEntity.getPeBy1Base());
        	programElementDto.setPeBy2(programElementEntity.getPeBy2());
        	programElementDto.setPeBy3(programElementEntity.getPeBy3());
        	programElementDto.setPeBy4(programElementEntity.getPeBy4());
        	programElementDto.setPeBy5(programElementEntity.getPeBy5());
        	 
        	programElementDto.setPePrevPbPy(programElementEntity.getPePrevPbPy());
        	programElementDto.setPePrevPbCy(programElementEntity.getPePrevPbCy());
        	programElementDto.setPePbBy1(programElementEntity.getPePbBy1());
        	programElementDto.setPePrevPbBy1Base(programElementEntity.getPePrevPbBy1Base());
        	programElementDto.setPeCurrPbPy(programElementEntity.getPeCurrPbPy());
        	programElementDto.setPeCurrPbCy(programElementEntity.getPeCurrPbCy());
        	programElementDto.setPeCurrPbBy1(programElementEntity.getPeCurrPbBy1());
        	programElementDto.setPeCurrPbBy1Base(programElementEntity.getPeCurrPbBy1Base());
        	programElementDto.setPeTotalAdjPy(programElementEntity.getPeTotalAdjPy());
        	programElementDto.setPeTotalAdjCy(programElementEntity.getPeTotalAdjCy());
        	programElementDto.setPeTotalAdjBy1(programElementEntity.getPeTotalAdjBy1());
        	programElementDto.setPeTotalAdjBy1Base(programElementEntity.getPeTotalAdjBy1Base());
        	programElementDto.setEditLockIdPeOnly(programElementEntity.getEditLockIdPeOnly());
        	programElementDto.setDateLockPe(programElementEntity.getDateLockPe());
        	
        	UserDto userDto = null;
        	
        	UserEntity userEntityCreate = programElementEntity.getCreatedByUserR2();
        	userDto = processUserEntity(userEntityCreate);
        	programElementDto.setCreatedByUserR2(userDto);
        	
        	UserEntity userEntityModify = programElementEntity.getModifiedByUserR2();
        	userDto = processUserEntity(userEntityModify);
        	programElementDto.setModifiedByUserR2(userDto);
        	
        	UserEntity userEntityModifyOverall = programElementEntity.getModifiedByUserIdOverall();
        	userDto = processUserEntity(userEntityModifyOverall);
        	programElementDto.setModifiedByUserIdOverall(userDto);
        	
    		programElementDto.setDateCreated(programElementEntity.getDateCreated());
    		programElementDto.setDateCreatedR2(programElementEntity.getDateCreatedR2());
    		programElementDto.setDateModifiedR2(programElementEntity.getDateModifiedR2());
    		programElementDto.setDateModifiedOverall(programElementEntity.getDateModifiedOverall());
    		programElementDto.setDateModified(programElementEntity.getDateModified());
        	
        	return programElementDto;
        	
        }
        log.error("transform- programElementEntity id is null, could not transform");
        return null;
	}
	
	
	@Override
	public ProgramElementEntity transform (IDto iDto) throws TransformerException {
		log.trace("transform - programElementDto");
		ProgramElementDto programElementDto = (ProgramElementDto) iDto;
		
		if (null == programElementDto) {
			throw new TransformerException(TransformerException.TRANSFORM_DTO_NULL);
		}
		
		ProgramElementEntity programElementEntity = new ProgramElementEntity();
		programElementEntity.setPeNumber(programElementDto.getPeNumber());
		programElementEntity.setBudgetCycle(programElementDto.getBudgetCycle());
		programElementEntity.setBudgetYear(programElementDto.getBudgetYear());
		programElementEntity.setR1Number(programElementDto.getR1Number());
		programElementEntity.setSubmissionDate(programElementDto.getSubmissionDate());
		programElementEntity.setPeTitle(programElementDto.getPeTitle());

		BudgetActivityDto budgetActivityDto = programElementDto.getBudgetActivityDto();
		BudgetActivityEntity budgetActivityEntity = budgetActivityTransformer.transform(budgetActivityDto);
		programElementEntity.setBudgetActivityEntity(budgetActivityEntity);
		
		ServiceAgencyDto serviceAgencyDto = programElementDto.getServiceAgencyDto();	
		ServiceAgencyEntity serviceAgencyEntity = serviceAgencyTransformer.transform(serviceAgencyDto);
		programElementEntity.setServiceAgencyEntity(serviceAgencyEntity);
		
		programElementEntity.setPeTag(programElementDto.getPeTag());
		programElementEntity.setPeTest(programElementDto.getPeTest());
		programElementEntity.setPeEditableSw(programElementDto.getPeEditableSw());
		programElementEntity.setPeStatusSubmission(programElementDto.getPeStatusSubmission());
		programElementEntity.setPeState(programElementDto.getPeState());
		programElementEntity.setPeInitSrc(programElementDto.getPeInitSrc());
		programElementEntity.setDateCreated(programElementDto.getDateCreated());
		programElementEntity.setPeFormat(programElementDto.getPeFormat());
		
		programElementEntity.setPeApy(programElementDto.getPeApy());
		programElementEntity.setPePy(programElementDto.getPePy());
		programElementEntity.setPeCy(programElementDto.getPeCy());
		programElementEntity.setPeBy1(programElementDto.getPeBy1());
		programElementEntity.setPeBy1Base(programElementDto.getPeBy1Base());
		programElementEntity.setPeBy2(programElementDto.getPeBy2());
		programElementEntity.setPeBy3(programElementDto.getPeBy3());
		programElementEntity.setPeBy4(programElementDto.getPeBy4());
		programElementEntity.setPeBy5(programElementDto.getPeBy5());
		
		programElementEntity.setPePrevPbPy(programElementDto.getPePrevPbPy());
		programElementEntity.setPePrevPbCy(programElementDto.getPePrevPbCy());
		programElementEntity.setPePbBy1(programElementDto.getPePbBy1());
		programElementEntity.setPePrevPbBy1Base(programElementDto.getPePrevPbBy1Base());
		programElementEntity.setPeCurrPbPy(programElementDto.getPeCurrPbPy());
		programElementEntity.setPeCurrPbCy(programElementDto.getPeCurrPbCy());
		programElementEntity.setPeCurrPbBy1(programElementDto.getPeCurrPbBy1());
		programElementEntity.setPeCurrPbBy1Base(programElementDto.getPeCurrPbBy1Base());
		programElementEntity.setPeTotalAdjPy(programElementDto.getPeTotalAdjPy());
		programElementEntity.setPeTotalAdjCy(programElementDto.getPeTotalAdjCy());
		programElementEntity.setPeTotalAdjBy1(programElementDto.getPeTotalAdjBy1());
		programElementEntity.setPePrevPbBy1Base(programElementDto.getPePrevPbBy1Base());
		programElementEntity.setPeTotalAdjBy1Base(programElementDto.getPeTotalAdjBy1Base());
		programElementEntity.setPeCompCost(programElementDto.getPeCompCost());
		programElementEntity.setPeTotalCost(programElementDto.getPeTotalCost());
		
		UserDto userDto = null;
		
		userDto = programElementDto.getCreatedByUserR2();
		UserEntity createdByUserR2 = processUserDto(userDto);
		programElementEntity.setCreatedByUserR2(createdByUserR2 );
		
		userDto = programElementDto.getModifiedByUserR2();
		UserEntity modifiedByUserR2 = processUserDto(userDto);
		programElementEntity.setModifiedByUserR2(modifiedByUserR2);
		
		userDto = programElementDto.getModifiedByUserIdOverall();
		UserEntity modifiedByUserIdOverall = processUserDto(userDto);
		programElementEntity.setModifiedByUserIdOverall(modifiedByUserIdOverall);
		
		programElementEntity.setDateCreated(programElementDto.getDateCreated());
		programElementEntity.setDateCreatedR2(programElementDto.getDateCreatedR2());
		programElementEntity.setDateModifiedR2(programElementDto.getDateModifiedR2());
		programElementEntity.setDateModifiedOverall(programElementDto.getDateModifiedOverall());
		programElementEntity.setDateModified(programElementDto.getDateModified());
		
		return programElementEntity;
	}
	
	private UserDto processUserEntity(UserEntity userEntity) {
		if (null == userEntity) {
			return null;
		}
		return userTransformer.transform(userEntity);
	}
	
	private UserEntity processUserDto(UserDto userDto) {
		if (null == userDto) {
			return null;
		}
		return userTransformer.transform(userDto);
	}
	

}
