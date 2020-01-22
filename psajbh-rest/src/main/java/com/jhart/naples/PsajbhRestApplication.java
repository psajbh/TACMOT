package com.jhart.naples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan({ "com.jhart.naples.rest","com.jhart.naples.web"})
public class PsajbhRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(PsajbhRestApplication.class, args);
	}

}
