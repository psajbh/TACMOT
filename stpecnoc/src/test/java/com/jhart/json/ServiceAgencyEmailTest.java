package com.jhart.json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class ServiceAgencyEmailTest {

    //https://stackoverflow.com/questions/39657197/how-to-convert-this-json-string-to-normal-java-arraylist-using-gson
    
    private List<ServiceAgencyEmail> saEmailList = new ArrayList<>();
    
    @Before
    public void setup() {
        
        saEmailList = new ArrayList<>();
        
        ServiceAgencyEmail sae = new ServiceAgencyEmail();
        sae.setSaCode("OSD");
        sae.setEmails("abcosd@mail.mil,defosd@mail.mil,xyzosd@gmail.mil");
        saEmailList.add(sae);
        
        sae = new ServiceAgencyEmail();
        sae.setSaCode("NAVY");
        sae.setEmails("abcnavy@mail.mil,defnavy@mail.mil,xyznavy@gmail.mil");
        saEmailList.add(sae);
        
        sae = new ServiceAgencyEmail();
        sae.setSaCode("AF");
        sae.setEmails("abcaf@mail.mil,defaf@mail.mil,xyzaf@gmail.mil");
        saEmailList.add(sae);

        //System.out.println("saEmailList: " + saEmailList);
        //System.out.println();
    }
    
    @Test
    public void testObjectToJson() throws Exception {
        ServiceAgencyEmailList sael = new ServiceAgencyEmailList();
        //sael.setId(1L);
        sael.setServiceAgencyEmails(saEmailList);
        Gson gson = new Gson();
        String strSaEmails = gson.toJson(sael);
        System.out.println("strSaEmails: " + strSaEmails);
    }
    
    @Ignore
    public void testServiceAgencyEmail() {
        
        Gson gson = new Gson();
        //Type listType = new TypeToken<ArrayList<ServiceAgencyEmail>>() {}.getType();
        Type saEmail = new TypeToken<ServiceAgencyEmail>() {}.getType();
        //String json = "{ }";
        //List<ServiceAgencyEmail> configValue = gson.fromJson(json, listType);
        String value = gson.toJson(saEmail);
        System.out.println(value);
        System.out.println();
    }
    
    //strSaEmails: {"serviceAgencyEmails":[{"saCode":"OSD","emails":"abcosd@mail.mil,defosd@mail.mil,xyzosd@gmail.mil"},{"saCode":"NAVY","emails":"abcnavy@mail.mil,defnavy@mail.mil,xyznavy@gmail.mil"},{"saCode":"AF","emails":"abcaf@mail.mil,defaf@mail.mil,xyzaf@gmail.mil"}],"id":1}
    
    @Test
    public void testConvertJsonToObject() throws Exception{
        
        String json = "{'saCode':'OSD','emails':'abcosd@mail.mil,defosd@mail.mil,xyzosd@gmail.mil'}";
        Gson gson = new Gson();
        ServiceAgencyEmail sae = gson.fromJson(json, ServiceAgencyEmail.class);
        assertNotNull(sae);
        assertEquals("OSD", sae.getSaCode());
        String[] emails = sae.getEmails().split(",");
        assertEquals(3,emails.length);
    }
    
    
    @Test
    public void testConvertJsonToListObject() throws Exception{
        
//        String json1 = "{'serviceAgencyEmails':[{'saCode':'OSD','emails':'abcosd@mail.mil,defosd@mail.mil,xyzosd@gmail.mil'},"
//                + "{'saCode':'NAVY','emails':'abcnavy@mail.mil,defnavy@mail.mil,xyznavy@gmail.mil'},"
//                + "{'saCode':'AF','emails':'abcaf@mail.mil,defaf@mail.mil,xyzaf@gmail.mil'}],'id':1}";
        
      String json1 = "{'serviceAgencyEmails':[{'saCode':'OSD','emails':'abcosd@mail.mil,defosd@mail.mil,xyzosd@gmail.mil'},"
      + "{'saCode':'NAVY','emails':'abcnavy@mail.mil,defnavy@mail.mil,xyznavy@gmail.mil'},"
      + "{'saCode':'AF','emails':'abcaf@mail.mil,defaf@mail.mil,xyzaf@gmail.mil'}]}";
        
        
        ServiceAgencyEmailList saEmailList = null;
        
        Gson gson = new Gson();
        
        try {
            saEmailList = gson.fromJson(json1, ServiceAgencyEmailList.class);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(saEmailList);
        
        
    }

    @Test
    public void testCreateServiceAgencyFromJsonFile() throws Exception{
        
        try{
            Reader reader = new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("json/test.json"), "UTF-8");
            Gson gson = new GsonBuilder().create();
            ServiceAgencyEmail sae = gson.fromJson(reader, ServiceAgencyEmail.class);
            assertNotNull(sae);
            System.out.println();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}



