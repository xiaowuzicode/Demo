package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping(value = "/sayHello")
public class HelloSpringBoot {

	@RequestMapping(value = "/sayHello",method=RequestMethod.GET)
	@ResponseBody
	public String sayHello(String name) {
		return "Hello SpringBoot " + name;

	}

}
