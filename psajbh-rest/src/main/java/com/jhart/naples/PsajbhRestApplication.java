package com.jhart.naples;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.jhart.naples.repo.PersonRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class PsajbhRestApplication {
	
	@Autowired
    private PersonRepository personRepository;

	public static void main(String[] args) {
		SpringApplication.run(PsajbhRestApplication.class, args);
	}
	
	@PostConstruct
    public void setup(){
        log.info("Spring LDAP + Spring Boot Configuration Example");

        List<String> names = personRepository.getAllPersonNames();
        log.info("names: " + names);

        System.exit(-1);
    }

}
