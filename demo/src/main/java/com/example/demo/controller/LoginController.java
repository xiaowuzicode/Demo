package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@Controller
@RequestMapping("/") 
public class LoginController {
	@Autowired 
	private UserService userService;
	
	@RequestMapping(value="/login",method={RequestMethod.GET})
	public String auth(){
		return "login";
		
	}
	
	@RequestMapping(value="/auth",method={RequestMethod.GET,RequestMethod.POST})
	public String auth(String name,String password,Model model){
		
		User u =userService.getUserById(name);
		if(u==null || !u.getName().equals("agan")){
			return "redirect:/login";
		}else{
			model.addAttribute("name", name+"最帅的人");
			return "index";
		}
		
	}
	
	

}
