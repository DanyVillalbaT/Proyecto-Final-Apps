package com.storecode.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.utils.ObjectUtils;
import com.storecode.models.CloudinaryConfig;
import com.storecode.models.Product;
import com.storecode.services.CategoryService;
import com.storecode.services.ProductService;
import com.storecode.services.ProviderService;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProviderService providerService;
	@Autowired
	private CloudinaryConfig cloudc;

	@GetMapping("/listProducts")
	public String listProducts(Model model) {
		model.addAttribute("listProducts", productService.getAll());
		model.addAttribute("listCategories", categoryService.getAll());
		return "product/listProducts";
	}

	@GetMapping("/category/{idCategory}")
	public String viewByCategory(@PathVariable("idCategory") int idCategory, Model model) {
		List<Product> products = productService.getProductsByCategory(idCategory);
		model.addAttribute("listProducts", products);
		model.addAttribute("listCategories", categoryService.getAll());
		return "product/productsByCategory";
	}

	@GetMapping("/productdetail/{idProduct}")
	public String viewDetail(@PathVariable("idProduct") long idProduct, Model model) {
		System.out.println("variable " + idProduct);
		Product product = productService.getByiId(idProduct);
		model.addAttribute("product", product);
		return "product/detailProduct";
	}

	@GetMapping("/addProduct")
	public String showProductForm(Model model) {
		model.addAttribute("providers", providerService.getAll());
		model.addAttribute("categories", categoryService.getAll());
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

	@GetMapping("/deleteProduct/{idProduct}")
	public String deleteProduct(@PathVariable("idProduct") long idProducto, Model model) {
		Product producto = productService.getByiId(idProducto);
		productService.delete(idProducto);
		model.addAttribute("productos", productService.getAll());
		return "redirect:/products/productsTable";
	}

	@GetMapping("editProduct/{idProduct}")
	public String showUpdateForm(@PathVariable("idProduct") long idProduct, Model model) {
		Product product = productService.getByiId(idProduct);
		model.addAttribute("product", product);
		model.addAttribute("providers", providerService.getAll());
		model.addAttribute("categories", categoryService.getAll());
		return "product/updateProduct";
	}

	@PostMapping("/updateProduct/{idProduct}")
	public String updateProduct(@PathVariable("idProduct") long idProduct, Product product, BindingResult result,
			Model model) {
		// productService.save(product);
		Product productOldProduct = productService.getByiId(idProduct);

		productOldProduct.setName(product.getName());
		productOldProduct.setDescription(product.getDescription());
		productOldProduct.setPrice(product.getPrice());
		productOldProduct.setCategory(product.getCategory());
		productOldProduct.setStock(product.getStock());
		productOldProduct.setProvider(product.getProvider());
		productOldProduct.setImg(product.getImg());

		productService.save(productOldProduct);
		model.addAttribute("products", productService.getAll());
		return "redirect:/products/productsTable";

	}
	  @PostMapping("/image")
	    public String addProducto(@ModelAttribute Product product, BindingResult result, Model model, @RequestParam("fileImg") MultipartFile fileImg) {
	        System.out.println("SI ESTA ENTRANDO");
	        try {
	        	Map uploadResult = cloudc.upload(fileImg.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
	            System.out.println(uploadResult.get("url").toString());
	            product.setImg(uploadResult.get("url").toString());
	            productService.save(product);
	    		System.out.println("Producto guardado: " + product.getName());
	    		return "redirect:/products/productsTable"; // Redirecciona al formulario después de guardar
	           
	        } catch (Exception e) {
	        	System.out.println(e.getMessage());
	        }
	        return "redirect:/products/productsTable";
	    }
	  
	  @PostMapping("/imageUpdate/{idProduct}")
	    public String update(@PathVariable("idProduct") long idProduct,Product product, BindingResult result ,Model model, @RequestParam("fileImg") MultipartFile fileImg) {
	        System.out.println("SI ESTA ENTRANDO");
	        try {
	        	Map uploadResult = cloudc.upload(fileImg.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
	            System.out.println(uploadResult.get("url").toString());
	            product.setImg(uploadResult.get("url").toString());
	            Product productOldProduct = productService.getByiId(idProduct);
	    		productOldProduct.setName(product.getName());
	    		productOldProduct.setDescription(product.getDescription());
	    		productOldProduct.setPrice(product.getPrice());
	    		productOldProduct.setCategory(product.getCategory());
	    		productOldProduct.setStock(product.getStock());
	    		productOldProduct.setProvider(product.getProvider());
	    		productOldProduct.setImg(product.getImg());

	    		productService.save(productOldProduct);
	    		model.addAttribute("products", productService.getAll());
	    		return "redirect:/products/productsTable";
	           
	        } catch (Exception e) {
	        	System.out.println(e.getMessage());
	        }
	        return "redirect:/products/productsTable";
	    }

}
