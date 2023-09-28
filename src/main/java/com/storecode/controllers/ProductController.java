package com.storecode.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.storecode.models.Product;
import com.storecode.services.CategoryService;
import com.storecode.services.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {

	 @Autowired
	    private ProductService productService;
	 @Autowired
	 	private CategoryService categoryService;
	
	    @GetMapping("/listProducts")
	    public String listProducts(Model model) {
	    	model.addAttribute("listProducts", productService.getAll());
	    	Product pro =  new Product();
	    	pro.setId(1);
	    	System.out.println("Precio" + productService.getByiId(pro.getId()).getPrice());
	        return "product/listProducts";
	    }

	    @GetMapping("/productdetail/{idProduct}")
	   	public String viewDetail(@PathVariable("idProduct") long idProduct, Model model) {
	    	System.out.println("variable "+idProduct);
	   		Product product = productService.getByiId(idProduct);
	   		model.addAttribute("product", product);
	   		return "product/detailProduct";
	   	}
	    @GetMapping("/addProduct")
	    public String showProductForm(Model model) {
	    	model.addAttribute("categories",categoryService.getAll());
	        model.addAttribute("product", new Product());
	        return "product/addProduct";
	    }

	    @PostMapping("/saveProduct")
	    public String saveProduct(@ModelAttribute Product product, Model model) {
	    	productService.save(product);
	        System.out.println("Producto guardado: " + product.getName());
	        return "redirect:/products/productsTable"; // Redirecciona al formulario después de guardar
	    }
	    @GetMapping("/productsTable")
	    public String showProductsTable(Model model) {
	    	model.addAttribute("products", productService.getAll());
	        return "product/productsTable";
	    }
	    
	    @GetMapping("/deleteProducto/{idProducto}")
	    public String deleteProducto(@PathVariable("idProducto") long idProducto, Model model) {
	    	Product producto = productService.getByiId(idProducto);
	    	productService.delete(idProducto);
	        model.addAttribute("productos", productService.getAll());
	        return "redirect:/products/productsTable";
	    }
	    
	    
}
