package com.utkarsh.flightreservation.controller;

import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.utkarsh.flightreservation.entities.User;
import com.utkarsh.flightreservation.repos.UserRepository;

import org.slf4j.Logger;
@Controller
public class UserController {
	@Autowired
    UserRepository userRepository;
	
	private static final Logger LOGGER=LoggerFactory.getLogger(UserController.class);
	@RequestMapping("/showReg")
	public String showRegistrationPage() {
		LOGGER.info("INSIDE showResgister Page");
		return "login/registerUser";
	}
	@RequestMapping(value="registerUser",method=RequestMethod.POST)
	public String register(@ModelAttribute("user") User user) {
		LOGGER.info("INSIDE regidterUser Page");
		
		userRepository.save(user);
		return "login/login";
	}
	@RequestMapping("/showLogin")
	public String showLoginPage() {
		LOGGER.info("INSIDE LoginView Page");
		return "login/login";
	}
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(@RequestParam("email")String email,@RequestParam("password")String password,ModelMap m) {
		LOGGER.info("INSIDE regidterUser Page");
	
		LOGGER.info("INSIDE login ");
		
		User user=userRepository.findByEmail(email);
		if(user==null)
			return "login/login";
		if(user.getPassword().equals(password)) {
			return "findFlights";
		}else{
			m.addAttribute("msg","Invalid Email or Password");
		}
		return "login/login";
	}
}
