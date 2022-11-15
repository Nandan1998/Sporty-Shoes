package com.shoes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shoes.entity.Login;
import com.shoes.repository.UserRepository;
import com.shoes.service.UserDetailsService;

@Controller
public class LoginController {
	
	@Autowired
    private UserRepository userRepo;
	
	@Autowired
	public UserDetailsService useservice;
	
	@GetMapping("")
	public String Login() {
		return "home";
	}
	
	
	@GetMapping("/registration")
	public String showRegistrationForm(Model model) {
	    model.addAttribute("user", new Login());
	    return "signup_form";
	}
	
	@PostMapping("/process_register")
	public String processRegister(Login user) { 
	    userRepo.save(user);
	    return "register_success";
	}
	
	@RequestMapping("/users")
	public String listUsers(Model model, String keyword) {
	    List<Login> listUsers;
	    if(keyword==null) {
	    	listUsers = userRepo.findAll();
	    }
	    else {
	    	listUsers = useservice.findByKeyword(keyword);
	    }
	    model.addAttribute("listUsers", listUsers);
	    return "users";
	}
	
	@RequestMapping("/purchase_form")
	public String PurchaseForm() {
		return "purchase_form";
	}
	
	@RequestMapping("/purchase_success")
	public String PurchaseSuccess() {
		return "purchase_success";
	}
	
}
