package com.jhart.cxe;

import org.junit.Assert;
import org.junit.Test;

public class EmailBuilderTests {
    
    @Test
    public void testStringFormatIntegration() throws Exception {
        
        String messgePart0 = "Dear ";
        String ownerName = "John Hart";
        String ownerEmail = "jhart@masslight.com";
        String ownerPhone = "239-322-7329";
        String messagePart1 = "This is notification that the justification book you submitted ";
        String messagePart2 = "with the id of ";
        String jobId = "abdefghigklmn012345";
        String workflowStatus = "and a work flow status of: ";
        String documentWorkFlowStatus = "Ready for Review";
        String isreviewed = "is now being reviewed by OSD Analyst ";
        String analystName = "Jack Ass";
        String osdContactHeader = "OSD Analyst Contact Information: ";
        String name = "Name: ";
        String[] analystEmail = new String[] {"jhart.naples@gmail.com"};
        String email = "Email: ";
        String phone = "Phone: ";
        String analystPhone = "239-254-2673";
        String jobCreatorContactHeader = "JBook Creator Contact Information: ";
        String ifIssues = "If any contact information is not correct, please provided corrected"; 
        String ifIssues2 = "information to the R2 Support Team: ";
        String senderEmail = "dtic.belvoir.pm.list.r2-support@mail.mil";
        String thankyou= "Thank you, ";
        String r2Support = "R2 Support Team";
        
        
        String newLine = String.format("%n");
        
        StringBuilder content = new StringBuilder();
        content.append(String.format("%s%s%s%s%s%s",messgePart0, ownerName, newLine, newLine,messagePart1, newLine));
        content.append(String.format("%s%s%s",messagePart2,jobId,newLine));
        content.append(String.format("%s%s%s",workflowStatus,documentWorkFlowStatus,newLine));
        content.append(String.format("%s%s%s%s",isreviewed, analystName, newLine, newLine));
        
        content.append(String.format("%s%s",osdContactHeader,newLine));
        content.append(String.format("%s%s%s",name,analystName,newLine));
        content.append(String.format("%s%s%s",email,analystEmail[0],newLine));
        content.append(String.format("%s%s%s%s%s",phone,analystPhone,newLine,newLine,newLine));
        
        content.append(String.format("%s%s",jobCreatorContactHeader, newLine));
        content.append(String.format("%s%s%s",name,ownerName,newLine));
        content.append(String.format("%s%s%s",email,ownerEmail,newLine));
        content.append(String.format("%s%s%s%s%s",phone,ownerPhone,newLine,newLine,newLine));
        
        content.append(String.format("%s%s%s%s%s%s", ifIssues,newLine, ifIssues2, senderEmail, newLine, newLine));
        content.append(String.format("%s%s%s%s",thankyou, newLine, newLine, r2Support));
        
        Assert.assertNotNull(content);
        
     
        

    }

}
