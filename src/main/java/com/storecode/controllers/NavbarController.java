package com.storecode.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.storecode.services.ProductService;

@Controller
@RequestMapping("/navbar")
public class NavbarController {
	
	@Autowired
    private ProductService productService;
	
	 @GetMapping("/home")
	    public String home() {
	        return "home/main-content";
	    }
	 @GetMapping("/product")
	    public String products(Model model) {
		 model.addAttribute("listProducts", productService.getAll());
	        return "product/listProducts";
	    }


}
