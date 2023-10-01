package com.storecode.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.storecode.services.CategoryService;
import com.storecode.services.ProductService;

import com.storecode.models.Category;
import com.storecode.models.Product;
import com.storecode.services.CategoryService;
import com.storecode.services.ProductService;

@Controller
@RequestMapping("/categories")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
    private ProductService productService;

	@GetMapping("/categoriesTable")
	public String showProductsTable(Model model) {
		model.addAttribute("categories", categoryService.getAll());
		return "category/categoriesTable";
	}

	@GetMapping("/deleteCategory/{idCategory}")
	public String deleteProduct(@PathVariable("idCategory") long idCategory, Model model) {
		Category category = categoryService.getByiId(idCategory);
		 if (productService.existsByCategory(category)) {
	            model.addAttribute("mensajeError", "No se puede eliminar la categoría porque está siendo utilizada en productos.");
	            System.out.println("CATEGORIA SIENDO USADA");
	            return "error";
	        }
		categoryService.delete(idCategory);
		model.addAttribute("categories", categoryService.getAll());
		return "redirect:/categories/categoriesTable";
	}

	@GetMapping("/addCategory")
	public String showProductForm(Model model) {
		model.addAttribute("category", new Category());
		return "category/addCategory";
	}

	@PostMapping("/saveCategory")
	public String saveProduct(@ModelAttribute Category category, Model model) {
		categoryService.save(category);
		System.out.println("Producto guardado: " + category.getName());
		return "redirect:/categories/categoriesTable"; // Redirecciona al formulario después de guardar
	}

	@GetMapping("editCategory/{idCategory}")
	public String showUpdateForm(@PathVariable("idCategory") long idCategory, Model model) {
		Category category = categoryService.getByiId(idCategory);
		model.addAttribute("category", category);
		return "category/updateCategory";
	}

	@PostMapping("/updateCategory/{idCategory}")
	public String updateProduct(@PathVariable("idCategory") long idCategory, Category category, BindingResult result,
			Model model) {
		Category categoryOld = categoryService.getByiId(idCategory);
		categoryOld.setName(category.getName());
		categoryService.save(categoryOld);
		model.addAttribute("categories", categoryService.getAll());
		return "redirect:/categories/categoriesTable";

	}

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
