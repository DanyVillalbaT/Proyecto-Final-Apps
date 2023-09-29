package com.storecode.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.storecode.models.Category;
import com.storecode.models.Product;
import com.storecode.services.CategoryService;

@Controller
@RequestMapping("/categories")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@GetMapping("/categoriesTable")
	public String showProductsTable(Model model) {
		model.addAttribute("categories", categoryService.getAll());
		return "category/categoriesTable";
	}

	@GetMapping("/deleteCategory/{idCategory}")
	public String deleteProduct(@PathVariable("idCategory") long idCategory, Model model) {
		Category category = categoryService.getByiId(idCategory);
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

}
