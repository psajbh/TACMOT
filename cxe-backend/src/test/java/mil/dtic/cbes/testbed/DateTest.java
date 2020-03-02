package mil.dtic.cbes.testbed;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

import mil.dtic.cbes.model.dto.core.budgetcycle.SubmissionDateDto;
//good article
//https://www.baeldung.com/java-string-to-date

public class DateTest {
	
	@Test
	public void testDateCreator() throws Exception {
		//2013-05-30 13:52:08
		
		SubmissionDateDto submissionDateDto = new SubmissionDateDto();
		submissionDateDto.setSubmissionDateId("Sep2019");
		submissionDateDto.setCode("Sep2019");
		submissionDateDto.setLabel("September 2019");
		submissionDateDto.setRank(9);
		
		Integer month = submissionDateDto.getRank();
		String sMonth = String.valueOf(month);
		if (sMonth.length() == 1) {
			sMonth = "0"+sMonth;
		}
		String[] sYear = submissionDateDto.getLabel().split(" ");
		String year = sYear[1];
		String day = "1";
		
		//String dateTemplate1 = day+"-"+sMonth+"-"+year+" "+"00:00:00";
		//String dataTemplate2 = year+"-"+sMonth+"-"+day+" "+"00:00:00";
		//String formatTemplate = "YYYY-MM-DD HH:mm:ss";
		String dateTemplate1 = day+"-"+sMonth+"-"+year;
		
		
		SimpleDateFormat formatter = new SimpleDateFormat(dateTemplate1);
		Date d = null;
		
//		try {
//			d = formatter.parse(dataTemplate1);
//		}
//		catch(Exception e) {
//			System.out.println(e.getMessage());
//		}
		System.out.println();
		
		
		
//		SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DD hh:mm:ss");
//		Date date=formatter.parse(label);
		
		
	}
	
	@Test
	public void testDateCreator2() throws Exception {
		String submissionDateId = "Sep2019";
		
		String month = submissionDateId.substring(0, 3);
		String year = submissionDateId.substring(3);
		
		String sDate2 = "1-"+month+"-"+year;
		SimpleDateFormat formatter2=new SimpleDateFormat("dd-MMM-yyyy");
		Date date2 = null;
		
		try {
			date2 =formatter2.parse(sDate2);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		System.out.println(date2);
	 
	}
	

}
