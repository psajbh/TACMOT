package com.jhart.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VersionController {
	
	@GetMapping("/version")
	public String  getVersion() {
		
		String json = readGitProperties();
		System.out.println();
		
		return null;
	}
	
	private String readGitProperties() {

	    ClassLoader classLoader = getClass().getClassLoader();
	    InputStream inputStream = classLoader.getResourceAsStream("git.properties");

	    try {
	        return readFromInputStream(inputStream);

	    } 
	    catch (IOException e) {
	        e.printStackTrace();
	        return "Version information could not be retrieved";

	    }

	}

	private String readFromInputStream(InputStream inputStream) throws IOException {

	    StringBuilder resultStringBuilder = new StringBuilder();

	    try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
	        String line;
	        while ((line = br.readLine()) != null) {
	            resultStringBuilder.append(line).append("\n");
	        }
	    }

	    return resultStringBuilder.toString();

	}	

}
