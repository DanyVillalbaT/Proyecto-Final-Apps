package com.storecode.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/navbar")
public class NavbarController {
	
	 @GetMapping("/home")
	    public String home() {
	        return "home/main-content";
	    }
	 @GetMapping("/product")
	    public String products() {
	        return "product/list-products";
	    }


}
