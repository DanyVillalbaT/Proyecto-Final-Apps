package com.storecode.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.storecode.models.Product;
import com.storecode.services.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {

	 @Autowired
	    private ProductService productService;
	
	    @GetMapping("/list-products")
	    public String listProducts(Model model) {
	    	model.addAttribute("listProducts", productService.getAll());
	        return "product/list-products";
	    }

	    @GetMapping("/productdetail/{idProduct}")
	   	public String viewDetail(@PathVariable("idProducto") long idProduct, Model model) {
	   		Product product = productService.getByiId(idProduct);
	   		model.addAttribute("product", product);
	   		return "product/detail-product";
	   	}
	   
}
