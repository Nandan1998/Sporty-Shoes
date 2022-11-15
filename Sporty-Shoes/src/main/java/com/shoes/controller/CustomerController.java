package com.shoes.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shoes.entity.Product;
import com.shoes.repository.ProductRepository;
import com.shoes.service.CustomerService;
import com.shoes.service.ProductService;

@Controller
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	public ProductRepository prodrepo;
	
	@Autowired
	public ProductService prodservice;
	
	@PostMapping("/verifyCustLogin")
	public String verifyLogin(@RequestParam(name="email") String email,@RequestParam(name="password") String password,HttpSession session,Model model) {
		if(!email.isEmpty() || !password.isEmpty()) {
			if(customerService.loginVerify(email,password)) {
				session.setAttribute("uname", email);
				return "redirect:/products";  
			}
		}
		return "invalid_credentials";

}
	@RequestMapping("/products")
	public String product_list(Model model)
	{
		List<Product> listProduct;
		listProduct = prodrepo.findAll();
		model.addAttribute("listProduct", listProduct);
		return "products";
	}
}
