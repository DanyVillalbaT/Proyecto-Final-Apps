package com.storecode.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.storecode.services.UserService;

@Controller
@RequestMapping("/shoppingcarts")
public class ShoppingCartController {

	@Autowired
	private ShoppingCartService shoppingCartService;

	@Autowired
	private ItemCartService itemCartService;

	@Autowired
	private ProductService productService;
	@Autowired
	private UserService userService;

	private User user;

	// Method to add a product to the shopping cart
	@PostMapping("/user/addProductCart")
	public String addProducts(@RequestParam(value = "id") long idProduct,
			@RequestParam(value = "quantity") int quantity, Model model) {
		user = userService.getByiId(1);
		ShoppingCart shoppingCart = shoppingCartService.findByUser(user);
		if (shoppingCart == null) {
			shoppingCart = new ShoppingCart();
			shoppingCart.setUser(user);
			shoppingCartService.save(shoppingCart);
		}

		Product product = productService.getByiId(idProduct);
		String message = null;
		ItemCart itemCart = null;
		if (itemCartService.existsByShoppingCartAndProduct(shoppingCart, product)) {
			message = "El producto seleccionado ya se encuentra en el carrito de compras";
			model.addAttribute("product", product);
			model.addAttribute("mensaje", message);
			return "product/detailProduct";
		} else {
			if (quantity < 0) {
				message = "La cantidad ingresada no es válida";
				return "";
			} else if (quantity > product.getStock()) {
				message = "La cantidad ingresada sobrepasa el stock disponible del producto";
				model.addAttribute("product", product);
				model.addAttribute("mensaje", message);
				return "product/detailProduct";
			} else {
				itemCart = new ItemCart();
				itemCart.setProduct(product);
				itemCart.setQuantityItems(quantity);
				double accumulatedValue = quantity * product.getPrice();
				itemCart.setAccumulatedValue(accumulatedValue);
				itemCart.setShoppingCart(shoppingCart);
				itemCartService.save(itemCart);

				product.setStock(product.getStock() - quantity);
				productService.save(product);

				List<ItemCart> itemsCart = itemCartService.getByShoppingCart(shoppingCart);

				double totalAmount = 0;
				for (ItemCart itemCartAux : itemsCart) {
					totalAmount += itemCartAux.getAccumulatedValue();
				}

				shoppingCart.setTotalValueItems(totalAmount);
				shoppingCartService.save(shoppingCart);

				model.addAttribute("message", message);
				model.addAttribute("itemsCart", itemsCart);
				model.addAttribute("totalAmount", totalAmount);
				return "shoppingCart/shoppingCart";
			}
		}

	}

	@GetMapping("/user/addOne/{idItemCart}")
	public String addOneQuantity(@PathVariable("idItemCart") long idItemCart, Model model) {

		ItemCart itemCart = itemCartService.getById(idItemCart);
		Product product = itemCart.getProduct();
		ShoppingCart shoppingCart = shoppingCartService.getById(itemCart.getShoppingCart().getId());
		int quantity = 1;
		String message = null;
		List<ItemCart> itemsCart = null;
		if (quantity > product.getStock()) {
			message = "El stock del producto ya se encuentra agotado";
			itemsCart = itemCartService.getByShoppingCart(shoppingCart);
			model.addAttribute("mensaje", message);
			model.addAttribute("itemsCart", itemsCart);
			model.addAttribute("totalAmount", shoppingCart.getTotalValueItems());
			return "shoppingCart/shoppingCart";
		} else {
			itemCart.setQuantityItems(itemCart.getQuantityItems() + quantity);
			itemCart.setAccumulatedValue(itemCart.getAccumulatedValue() + product.getPrice());
			itemCartService.save(itemCart);

			product.setStock(product.getStock() - quantity);
			productService.save(product);

			shoppingCart.setTotalValueItems(shoppingCart.getTotalValueItems() + product.getPrice());
			shoppingCartService.save(shoppingCart);

			itemsCart = itemCartService.getByShoppingCart(shoppingCart);

			model.addAttribute("mensaje", message);
			model.addAttribute("itemsCart", itemsCart);
			model.addAttribute("totalAmount", shoppingCart.getTotalValueItems());
			return "shoppingCart/shoppingCart";

		}

	}

	@GetMapping("/user/removeOne/{idItemCart}")
	public String removeOneQuantity(@PathVariable("idItemCart") long idItemCart, Model model) {

		ItemCart itemCart = itemCartService.getById(idItemCart);
		Product product = itemCart.getProduct();
		ShoppingCart shoppingCart = shoppingCartService.getById(itemCart.getShoppingCart().getId());
		int quantity = 1;
		String message = null;
		List<ItemCart> itemsCart = null;
		if (itemCart.getQuantityItems() - quantity == 0) {
			itemCartService.deleteById(idItemCart);
			message = "El producto ha sido eliminado del carrito de compras";

			product.setStock(product.getStock() + quantity);
			productService.save(product);

			shoppingCart.setTotalValueItems(shoppingCart.getTotalValueItems() - product.getPrice());
			shoppingCartService.save(shoppingCart);

			itemsCart = itemCartService.getByShoppingCart(shoppingCart);
			model.addAttribute("mensaje", message);
			model.addAttribute("itemsCart", itemsCart);
			model.addAttribute("totalAmount", shoppingCart.getTotalValueItems());
			return "shoppingCart/shoppingCart";
		} else {
			itemCart.setQuantityItems(itemCart.getQuantityItems() - quantity);
			itemCart.setAccumulatedValue(itemCart.getAccumulatedValue() - product.getPrice());
			itemCartService.save(itemCart);

			product.setStock(product.getStock() + quantity);
			productService.save(product);

			shoppingCart.setTotalValueItems(shoppingCart.getTotalValueItems() - product.getPrice());
			shoppingCartService.save(shoppingCart);

			itemsCart = itemCartService.getByShoppingCart(shoppingCart);

			model.addAttribute("mensaje", message);
			model.addAttribute("itemsCart", itemsCart);
			model.addAttribute("totalAmount", shoppingCart.getTotalValueItems());
			return "shoppingCart/shoppingCart";

		}

	}

	@GetMapping("/user/deleteItem/{idItemCart}")
	public String deleteItem(@PathVariable("idItemCart") long idItemCart, Model model) {

		ItemCart itemCart = itemCartService.getById(idItemCart);
		Product product = itemCart.getProduct();
		ShoppingCart shoppingCart = shoppingCartService.getById(itemCart.getShoppingCart().getId());
		int quantity = itemCart.getQuantityItems();
		double accumulatedValue = itemCart.getAccumulatedValue();

		itemCartService.deleteById(idItemCart);
		String message = "El producto ha sido eliminado del carrito de compras";

		product.setStock(product.getStock() + quantity);
		productService.save(product);

		shoppingCart.setTotalValueItems(shoppingCart.getTotalValueItems() - accumulatedValue);
		shoppingCartService.save(shoppingCart);

		List<ItemCart> itemsCart = itemCartService.getByShoppingCart(shoppingCart);

		model.addAttribute("mensaje", message);
		model.addAttribute("itemsCart", itemsCart);
		model.addAttribute("totalAmount", shoppingCart.getTotalValueItems());
		return "shoppingCart/shoppingCart";

	}

}
