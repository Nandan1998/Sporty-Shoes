package com.shoes.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shoes.entity.Purchase;
import com.shoes.service.PurchaseService;

@Controller
public class PurchaseController { 
	
	@Autowired
	private PurchaseService purchaseService;
	
	@GetMapping("/manage_purchase")
	public String managePurchase(Model model) {
		model.addAttribute("purchases", purchaseService.getAllPurchases());
		return "manage_purchase";
	}
	
	@PostMapping("/searchPurchaseDate")
	public String searchPurchaseDate(@RequestParam("keyword") String keyword,Model model) {
		Date date=null;
		try {
		//DateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
		date = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(keyword).getTime());
		}catch(Exception e) { System.out.println(e); }
		List<Purchase> sPurchase = purchaseService.getPurchaseByDate(date);
		if(sPurchase.isEmpty()) {
			model.addAttribute("action", "No purchases on the selected date");
			model.addAttribute("purchases", purchaseService.getAllPurchases());
			return "managePurchase";
		}else {
			model.addAttribute("searchHeading","selected Date");
			model.addAttribute("sPurchase", sPurchase);
			return "searchPurchase";	
		}
		
	}
	
	@PostMapping("/searchPurchaseCategory")
	public String searchPurchaseCategory(@RequestParam("keyword") String keyword,Model model) {
		List<Purchase> sPurchase = purchaseService.getPurchaseByCategory(keyword);
		if(sPurchase.isEmpty()) {
			model.addAttribute("action", "No purchases on the Entered Category");
			model.addAttribute("purchases", purchaseService.getAllPurchases());
			return "managePurchase";
		}else {
			model.addAttribute("searchHeading","Entered Catogery");
			model.addAttribute("sPurchase", sPurchase);
			return "searchPurchase";	
		}
		
	}
	
	@GetMapping("/purchase")
	public String Purchase(Model model, HttpSession session) {
		session.setAttribute("sessions", session);	
		return "purchase_success";

}
}
