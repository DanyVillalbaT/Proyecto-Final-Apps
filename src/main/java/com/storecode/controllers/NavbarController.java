package com.storecode.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.storecode.models.Provider;
import com.storecode.models.User;
import com.storecode.models.UserSessionSingleton;

import com.storecode.models.ItemCart;
import com.storecode.models.ShoppingCart;
import com.storecode.models.User;

import com.storecode.services.CategoryService;
import com.storecode.services.ItemCartService;
import com.storecode.services.ProductService;

import com.storecode.services.ProviderService;

import com.storecode.services.ShoppingCartService;

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
	 

	 @Autowired
	private ShoppingCartService shoppingCartService;
	@Autowired
	private ItemCartService itemCartService;
	
	

	

	@GetMapping("/shopping-cart")
	public String showShoppingCart(Model model) {

		User user = UserSessionSingleton.getINSTANCIA().getUserSession();
		ShoppingCart shoppingCart = shoppingCartService.findByUser(user);
		List<ItemCart> itemsCart = null;
		String message = null;
		if (shoppingCart == null) {
			shoppingCart = new ShoppingCart();
			shoppingCart.setUser(user);
			shoppingCart.setTotalValueItems(0);
			shoppingCartService.save(shoppingCart);
			
			 model.addAttribute("user",user);

			
			model.addAttribute("mensaje", message);
			model.addAttribute("itemsCart", itemsCart);
			model.addAttribute("totalAmount", shoppingCart.getTotalValueItems());
			return "shoppingCart/shoppingCart";
		} else {
			itemsCart = itemCartService.getByShoppingCart(shoppingCart);
			
			 model.addAttribute("user",user);

			model.addAttribute("mensaje", message);
			model.addAttribute("itemsCart", itemsCart);
			model.addAttribute("totalAmount", shoppingCart.getTotalValueItems());
			return "shoppingCart/shoppingCart";
		}


	}

}
