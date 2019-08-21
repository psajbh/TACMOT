package com.jhart.naples.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v1")
@RestController
public class ProfileController {

	@Value("${application.environment}")
	private String applicationEnv;

	@GetMapping
	public String getApplicationEnv() {
		return applicationEnv;
	}

}
