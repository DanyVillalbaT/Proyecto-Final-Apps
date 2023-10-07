package com.storecode.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.storecode.models.Provider;
import com.storecode.models.User;
import com.storecode.models.UserSessionSingleton;
import com.storecode.services.CategoryService;
import com.storecode.services.ProductService;
import com.storecode.services.ProviderService;
import com.storecode.services.UserService;

@Controller
@RequestMapping("/navbar")
public class NavbarController {
	
	@Autowired
    private ProductService productService;
	 @Autowired
	 	private CategoryService categoryService;
	 @Autowired
		private ProviderService providerService;
		@Autowired
	    private UserService userService;
		
	 	private User user = UserSessionSingleton.getINSTANCIA().getUserSession(); 
		
	
	 @GetMapping("/home")
	    public String home(Model model) {
		 String cargo = UserSessionSingleton.getINSTANCIA().getCargo();
		 model.addAttribute("user",user);
	        model.addAttribute("cargo", cargo);
	        return "home/main-content";
	    }
	 @GetMapping("/product")
	    public String products(Model model) {
		 model.addAttribute("listProducts", productService.getAll());
		 model.addAttribute("listCategories", categoryService.getAll());
		 model.addAttribute("user",user);
	        return "product/listProducts";
	        
	    }
	 @GetMapping("/productsTable")
	    public String showProductsTable(Model model) {
	    	model.addAttribute("products", productService.getAll());
	        return "product/productsTable";
	    }
	 @GetMapping("/categoriesTable")
		public String showCategoriesTable(Model model) {
			model.addAttribute("categories", categoryService.getAll());
			return "category/categoriesTable";
		}
	 @GetMapping("/listProviders")
	  	public String listProviders(Provider provider, Model model) {
	  		model.addAttribute("providers", providerService.getAll());
	        return "/provider/listProviders";
	  	}
	 @GetMapping("/usersTable")
	    public String showUsersTable(Model model) {
	    	model.addAttribute("users", userService.getAll());
	        return "user/usersTable";
	    }
	 @GetMapping("/logOut")
	    public String remove(Model model) {
		 UserSessionSingleton.getINSTANCIA().setUserSession(null);
	    	
	        return "redirect:/users/login";
	    }
	 


}
