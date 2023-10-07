package com.storecode.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.storecode.models.ItemCart;
import com.storecode.models.Product;
import com.storecode.models.ShoppingCart;
import com.storecode.models.User;
import com.storecode.services.ItemCartService;
import com.storecode.services.ProductService;
import com.storecode.services.ShoppingCartService;

@Controller
@RequestMapping("/shoppingcarts")
public class ShoppingCartController {

	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private ItemCartService itemCartService;
	
	@Autowired
	private ProductService productService;
	
	private User user;
	
	
	//Method to add a product to the shopping cart
	@PostMapping("/user/addProductCart")
	public String addProducts(@RequestParam(value = "idProduct") long idProduct, 
			@RequestParam(value = "quantity") int quantity, Model model) {
		user = null;
		ShoppingCart shoppingCart = shoppingCartService.existsByUser(user);
		if (shoppingCart == null) {
			shoppingCart = new ShoppingCart();
			shoppingCart.setUser(user);
			shoppingCartService.save(shoppingCart);
		}
		
		Product product = productService.getByiId(idProduct);
		String message = null;
		ItemCart itemCart = null;
		if(itemCartService.existsByShoppingCartAndProduct(shoppingCart, product)) {
			message = "El producto seleccionado ya se encuentra en el carrito de compras";
			model.addAttribute("product", product);
			model.addAttribute("mensaje", message);
			return "product/detailProduct";
		}else {
			if (quantity > product.getStock()) {
				message = "La cantidad escogida sobrepasa el stock disponible del producto";
				model.addAttribute("product", product);
				model.addAttribute("mensaje", message);
				return "product/detailProduct";
			}else {
				itemCart = new ItemCart();
				itemCart.setProduct(product);
				itemCart.setQuantityItems(quantity);
				double accumulatedValue = quantity * product.getPrice();
				itemCart.setAccumulatedValue(accumulatedValue);
				itemCart.setShoppingCart(shoppingCart);
				itemCartService.save(itemCart);
				
				List<ItemCart> itemsCart = itemCartService.getByShoppingCart(shoppingCart);
				
				double totalAmount = 0;
				for (ItemCart itemCartAux : itemsCart) {
					totalAmount += itemCartAux.getAccumulatedValue();
				}
				
				shoppingCart.setTotalValueItems(totalAmount);
				shoppingCartService.save(shoppingCart);
				
				model.addAttribute("itemsCart", itemsCart);
				model.addAttribute("totalAmount", totalAmount);
				return "shoppingCart/addProductCart";
			}
		}
		
	}
}
