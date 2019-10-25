package com.jhart.naples.consume;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class EchoConsumerImpl implements EchoConsumer{
    
    private RestTemplate restTemplate;
    
    public EchoConsumerImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        System.out.println("inside consumer EchoConsumer with restTemplate: " + restTemplate);
    }
    
    // localhost:8084/restapp/
    // need to include a QueryParameter, which is the echo.
    
    @Override
    public String getEcho(String echo) {
        Map<String,String> urlVariables = new HashMap<>();
        urlVariables.put("input", echo);
        
        URI url = UriComponentsBuilder
                .fromHttpUrl("http://localhost:8080/ingredients/{input}")
                .build(urlVariables);
        
        String value = restTemplate.getForObject(url, String.class);
                
        return value;
    }
    
    

}
