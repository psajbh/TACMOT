package com.jhart.cxe;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.junit.Test;

public class DateTesting {
    
    @Test
    public void testDateFormatting() throws Exception {
        
//        Date d = new Date();
//        System.out.println("d: " + d);
//        System.out.println("d string: " + d.toString());
//        System.out.println();
        
        //lastLoginDate = dateFormatterNew.format(ldt);
        String testDate = "2013-05-30 13:52:08.0";
        String date = "2019-05-23 00:00:00.0";
        
        //DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEEE, MMM d, yyyy HH:mm:ss a");
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, MMM d, yyyy HH:mm:ss a");
        Date parsedDate = sdf.parse(date);
        sdf.format(parsedDate);
        
        //LocalDateTime ldt = LocalDateTime.parse(date);
//        System.out.println("ldt: " + ldt);
//        sdf.format
        
    }
    
    @Test
    public void testDateFormatting2() throws Exception {
        
        
        // date format 1
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEEE, MMM d, yyyy HH:mm:ss");
        String date = "2019-05-23 00:00:00";
        LocalDateTime ldateTime = LocalDateTime.parse(date, dateFormatter);
        String d = dateFormatter.format(ldateTime);
        System.out.println();
        
//        // date format 2
//        private static final DateTimeFormatter dateFormatterNew 
//            = DateTimeFormatter.ofPattern("EEEE, MMM d, yyyy HH:mm:ss a");
//
//        public static void main(String[] args) {
//
//            String date = "2019-05-23 00:00:00.0";
//
//            // string to LocalDateTime
//            LocalDateTime ldateTime = LocalDateTime.parse(date, dateFormatter);
//
//            System.out.println(dateFormatter.format(ldateTime));
//
//            // change date format
//            System.out.println(dateFormatterNew.format(ldateTime));
//

        
    
    }
    

}
