package com.contact.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController {

	@RequestMapping(value = { "/hello" }, method = RequestMethod.GET)
	public String helloWorld() {
		System.out.println("Working Test Controller.");
		return "hello";
	}
}
