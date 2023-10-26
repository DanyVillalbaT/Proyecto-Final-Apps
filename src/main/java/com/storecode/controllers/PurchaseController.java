package com.storecode.controllers;

import com.storecode.models.User;
import com.storecode.models.UserSessionSingleton;
import com.storecode.services.ShoppingCartService;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ItemCartService itemCartService;

    private User user;

    public void createPurchase(Purchase purchase) {

        user = UserSessionSingleton.getINSTANCIA().getUserSession();
        ShoppingCart shoppingCart = shoppingCartService.findByUser(user);
        List<ItemCart> itemsCart = itemCartService.getByShoppingCart(shoppingCart);
        String message = null;

        if (itemsCart.isEmpty()) {
            message = "El carrito de compras se encuentra vacio";
        }else{
            PurchaseDetail purchaseDetail = new PurchaseDetail();
            purchaseDetail.setAccumulatedValue(shoppingCart.getAccumulatedValue());
            purchaseDetail.setDeliveryAddress(user.getAddress());
            purchaseDetail.setPaymentMethod("Tarjeta de credito");
            purchaseDetail.setItemsCart(itemsCart);

            Purchase purchase = new Purchase();
            purchase.setStatus("Generada");
            purchase.setDate(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
            //Impuesto del 19%
            //Más envío
            purchase.setTotalValue(purchaseDetail.getAccumulatedValue());
        }


    }


}
