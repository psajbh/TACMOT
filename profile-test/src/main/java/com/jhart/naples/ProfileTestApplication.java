package com.jhart.naples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProfileTestApplication {

	public static void main(String[] args) {
		int i = 0;
		if (args.length > 0) {
			for(String arg : args) {
				System.out.println("arg-"+i++  + " value: " +  arg);
			}
		}
		
		SpringApplication.run(ProfileTestApplication.class, args);
	}

}
