package com.storecode.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.storecode.models.ItemCart;
import com.storecode.models.ShoppingCart;
import com.storecode.models.User;
import com.storecode.services.CategoryService;
import com.storecode.services.ItemCartService;
import com.storecode.services.ProductService;
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
	private ShoppingCartService shoppingCartService;
	@Autowired
	private ItemCartService itemCartService;
	@Autowired
	private UserService userService;

	@GetMapping("/home")
	public String home() {
		return "home/main-content";
	}

	@GetMapping("/product")
	public String products(Model model) {
		model.addAttribute("listProducts", productService.getAll());
		model.addAttribute("listCategories", categoryService.getAll());
		return "product/listProducts";

	}

	@GetMapping("/shopping-cart")
	public String showShoppingCart(Model model) {

		User user = userService.getByiId(1);
		ShoppingCart shoppingCart = shoppingCartService.findByUser(user);
		List<ItemCart> itemsCart = null;
		String message = null;
		if (shoppingCart == null) {
			shoppingCart = new ShoppingCart();
			shoppingCart.setUser(user);
			shoppingCart.setTotalValueItems(0.0);
			shoppingCartService.save(shoppingCart);

			model.addAttribute("mensaje", message);
			model.addAttribute("itemsCart", itemsCart);
			model.addAttribute("totalAmount", shoppingCart.getTotalValueItems());
			return "shoppingCart/shoppingCart";
		} else {
			itemsCart = itemCartService.getByShoppingCart(shoppingCart);

			model.addAttribute("mensaje", message);
			model.addAttribute("itemsCart", itemsCart);
			model.addAttribute("totalAmount", shoppingCart.getTotalValueItems());
			return "shoppingCart/shoppingCart";
		}

	}

}
