package com.feature.base64;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;

public class Base64Encoder {

    public static void main(String[] args) throws Exception{
        Base64Encoder base64Encoder = new Base64Encoder();
        base64Encoder.processSimpleInput();
        base64Encoder.testUrl();
    } 
    
    private void processSimpleInput() {
        String originalInput = "test input";
        String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
        String processSimpleInput =String.format("original input: %s  enncoded output: %s", originalInput, encodedString);
        System.out.println(processSimpleInput);
        
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        String decodedString = new String(decodedBytes);
        System.out.println("decoded string: " + decodedString);
    }
    
    
    private void testUrl() throws URISyntaxException{
        String testUrl = "http://www.baeldung.com?key1=value+1&key2=value%40%21%242&key3=value%253";
        URI uri = new URI(testUrl);
        String scheme = uri.getScheme();
        String host = uri.getHost();
        String rawQuery = uri.getRawQuery();
        String testUrlresults = String.format("scheme: %s ,host: %s ,rawQuery: %s", scheme, host, rawQuery);
        System.out.println(testUrlresults);
        
    }
    
     
    
}
