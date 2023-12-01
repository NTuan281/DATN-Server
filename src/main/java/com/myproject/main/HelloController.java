package com.myproject.main;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@GetMapping("/helo-world")
	public String helloWorld() {
		return "hello world retert";
	}
}
