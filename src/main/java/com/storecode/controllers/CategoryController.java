package com.storecode.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.storecode.services.CategoryService;
import com.storecode.services.ProductService;

@Controller
@RequestMapping("/categories")
public class CategoryController {

	 @Autowired
	 private CategoryService categoryService;

	 @Autowired
	 private ProductService productService;
	 
	@GetMapping("/list-categories")
	@ResponseBody
    public String listCategories(Model model) {
    	model.addAttribute("listCategories", categoryService.getAll());
        return "category/list-categories";
    }
	
	@GetMapping("/list-products")
	@ResponseBody
    public String listproducts(Model model) {
    	model.addAttribute("listProducts", productService.getProductsByCategory(0));
        return "category/list-categories";
    }
	
}
