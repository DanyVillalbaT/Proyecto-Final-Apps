package com.storecode.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.storecode.models.Product;
import com.storecode.services.ProductService;
import com.storecode.services.CategoryService;

@Controller
@RequestMapping("/products")
public class ProductController {

	 	@Autowired
	    private ProductService productService;
	 	
	 	@Autowired
	    private CategoryService categoryService;
	
	    @GetMapping("/list-products")
	    public String listProducts(Model model) {
	    	model.addAttribute("listProducts", productService.getAll());
	    	model.addAttribute("listCategories", categoryService.getAll());
	        return "product/list-products";
	    }

	    @GetMapping("/productdetail/{idProduct}")
	   	public String viewDetail(@PathVariable("idProduct") long idProduct, Model model) {
	    	System.out.println("variable "+idProduct);
	   		Product product = productService.getByiId(idProduct);
	   		model.addAttribute("product", product);
	   		return "product/detailProduct";
	   	}
	   
}
