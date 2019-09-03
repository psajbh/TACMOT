package com.jhart.cxe;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.junit.Test;

public class DateFormatTest {
	
	private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
	private static final DateTimeFormatter dateFormatterNew = DateTimeFormatter.ofPattern("EEEE, MMM d, yyyy HH:mm:ss a");
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
    private static final SimpleDateFormat sdfNew = 	new SimpleDateFormat("EEEE, MMM d, yyyy HH:mm:ss a");
    
    @Test
    public void testGetDateString() {
        TimeZone.setDefault(TimeZone.getTimeZone("America/New_York"));
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, Calendar.SEPTEMBER, 3, 6, 59);
        Date date = calendar.getTime();
        System.out.println(date);
        
        Date d = new Date();
        System.out.println(d);

        
    }
	
	@Test
	public void testDateFormat() throws Exception{
		
		String date = "2019-05-23 00:00:00.0";

		// string to LocalDateTime
        LocalDateTime ldateTime = LocalDateTime.parse(date, dateFormatter);

        System.out.println(dateFormatter.format(ldateTime));

        // change date format
        System.out.println(dateFormatterNew.format(ldateTime));
	}
	
	@Test
	public void testSimpleDateFormat() throws Exception{
		
		String dateString = "2019-05-23 00:00:00.0";

        try {

			// string to date
            Date date = sdf.parse(dateString);

            System.out.println(sdf.format(date));
			
            System.out.println(sdfNew.format(date));

        } catch (ParseException e) {
            e.printStackTrace();
        }
	}
	
	

}
