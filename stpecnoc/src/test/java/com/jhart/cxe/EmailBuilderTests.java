package com.jhart.cxe;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class EmailBuilderTests {
    
    @Test
    public void testStringFormatIntegration() throws Exception {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");  
        Date date = new Date(System.currentTimeMillis());  
        String now = formatter.format(date);  
        
        String comma = ", ";
        String at = "at ";      
        String dot = ".";
        String messgePart01 = "Dear ";
        String messagePart02= "Thank you, ";
        String ownerName = "John Hart";
        String ownerEmail = "jhart@masslight.com";
        String ownerPhone = "239-322-7329";
        String messagePart1 = "This is notification that the justification book you submitted ";
        String messagePart2 = "with the tracking number of ";
        String jobId = "76d9a180-4e33-4335-b2cc-d10f26fed96d";
        String messagePart3 = "and a work flow status of ";
        String documentWorkFlowStatus = "Ready for Review";
        String messagePart4 = " has been downloaded "; 
        String messagePart4a = " by OSD Analyst ";
        String analystName = "Jack Ass";
        String messagePart5 = "OSD Analyst Contact Information: ";
        String name = "Name: ";
        String[] analystEmail = new String[] {"jhart.naples@gmail.com"};
        String email = "Email: ";
        String phone = "Phone: ";
        String analystPhone = "239-254-2673";
        String messagePart6 = "JBook Creator Contact Information: ";
        String messagePart7 = "If any contact information is not correct, please provide corrected"; 
        String messagePart8 = "information to the R2 Support Team: ";
        String senderEmail = "dtic.belvoir.pm.list.r2-support@mail.mil";
        String messagePart03 = "R2 Support Team";
        
        
        String newLine = String.format("%n");
        
        StringBuilder content = new StringBuilder();
        content.append(String.format("%s%s%s%s%s%s%s",messgePart01, ownerName, comma, newLine, newLine,messagePart1, newLine));
        content.append(String.format("%s%s%s",messagePart2,jobId,newLine));
        content.append(String.format("%s%s%s%s%s%s",messagePart3,documentWorkFlowStatus, messagePart4, newLine, at, now));
        content.append(String.format("%s%s%s%s%s", messagePart4a, analystName, dot, newLine, newLine));
        
        content.append(String.format("%s%s",messagePart5,newLine));
        content.append(String.format("%s%s%s",name,analystName,newLine));
        content.append(String.format("%s%s%s",email,analystEmail[0],newLine));
        content.append(String.format("%s%s%s%s",phone,analystPhone,newLine,newLine));
        
        content.append(String.format("%s%s",messagePart6, newLine));
        content.append(String.format("%s%s%s",name,ownerName,newLine));
        content.append(String.format("%s%s%s",email,ownerEmail,newLine));
        content.append(String.format("%s%s%s%s%s",phone,ownerPhone,newLine,newLine,newLine));
        
        content.append(String.format("%s%s%s%s%s%s%s", messagePart7,newLine, messagePart8, senderEmail, dot, newLine, newLine));
        
        content.append(String.format("%s%s%s%s",messagePart02, newLine, newLine, messagePart03));
        
        Assert.assertNotNull(content);
        
        System.out.println(content);
        
     
        

    }

}
