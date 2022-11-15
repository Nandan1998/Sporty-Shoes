package com.shoes.controller;
 
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shoes.entity.Product;
import com.shoes.repository.ProductRepository;
import com.shoes.service.ProductService;
import com.shoes.entity.Admin;
import com.shoes.service.AdminService;

@Controller
public class ProductController {
	
	@Autowired
	public ProductRepository prodrepo;
	
	@Autowired
	public ProductService prodservice;
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping("/verifyLogin")
	public String verifyLogin(@RequestParam(name="username") String username,@RequestParam(name="password") String password,HttpSession session,Model model) {
		if(!username.isEmpty() || !password.isEmpty()) {
			if(adminService.loginVerify(username,password)) {
				session.setAttribute("uname", username);
				return "redirect:/AdminLogin"; 
			}
		}
		return "invalid_credentials";
	}
	
	@RequestMapping("/AdminLogin")
	public String viewListProductPage(Model model, String keyword)
	{
		List<Product> listProduct;
		
		if(keyword == null) {
			listProduct = prodrepo.findAll();
		}else {
			listProduct = prodservice.findByKeyword(keyword);
		}
		model.addAttribute("listProduct", listProduct);
		return "product_manage";
	}
	
	@GetMapping("/changePassword")
	public String changeAdminPassword(HttpSession session, Model model)	{
		String username=(String) session.getAttribute("uname");
		Admin admin = adminService.getAdmin(username);
		model.addAttribute("admin", admin);
		return "change_password";
	}
	
	@PostMapping("/updatePassword")
	public String updatePassword(@RequestParam(name="oldPassword") String oldPassword,@RequestParam(name="newPassword") String newPassword,HttpSession session,Model model) {
		String username=(String) session.getAttribute("uname");
		Admin admin = adminService.getAdmin(username);
		if(oldPassword.equals(admin.getPassword())) {
			admin.setPassword(newPassword);
			adminService.updatePassword(admin);
			model.addAttribute("action", "Password changed Successfully");
			return "redirect:/AdminLogin";
		}else {
			model.addAttribute("action", "Old Password not matching");
			return "change_password";
		}
		
	}
			
	@RequestMapping("/newproduct")
	public String addNewProductPage(Model model)
	{
		Product product= new Product();
		model.addAttribute("product", product);
		
		return "new_product";
	}
	
	@RequestMapping(value = "/saveproduct", method = RequestMethod.POST)
	public String saveCategory(@ModelAttribute("product") Product product, Model md) 
	{   
		
		prodrepo.save(product);
		Iterable<Product> listProduct= prodrepo.findAll();
		md.addAttribute("listProduct", listProduct); 
		return "product_manage";
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteCategory(@PathVariable(name ="id") Integer id, Model m)
	{
		prodservice.delete(id);
		Iterable<Product> listProduct= prodservice.listAll();
		m.addAttribute("listProduct", listProduct);
		
		return "product_manage";
	}
	
	
	
	 
}
