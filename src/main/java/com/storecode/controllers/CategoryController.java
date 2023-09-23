package com.storecode.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.storecode.services.CategoryService;

@Controller
@RequestMapping("/categories")
public class CategoryController {

	 @Autowired
	 private CategoryService categoryService;
	 
	@GetMapping("/list-categories")
    public String listCategories(Model model) {
    	model.addAttribute("listCategories", categoryService.getAll());
        return "category/list-categories";
    }
	
}
