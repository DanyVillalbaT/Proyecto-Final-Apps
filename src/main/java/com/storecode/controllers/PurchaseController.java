package com.storecode.controllers;

import com.storecode.models.*;
import com.storecode.services.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;

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

    @PostMapping("/user/createPurchase")
    public void createPurchase(Model model) {

        user = UserSessionSingleton.getINSTANCIA().getUserSession();
        ShoppingCart shoppingCart = shoppingCartService.findByUser(user);
        List<ItemCart> itemsCart = itemCartService.getByShoppingCart(shoppingCart);
        String message = null;

        if (itemsCart.isEmpty()) {
            message = "El carrito de compras se encuentra vacio";
            return "redirect:/navbar/shopping-cart";
        }else{
            PurchaseDetail purchaseDetail = new PurchaseDetail();
            purchaseDetail.setAccumulatedValue(shoppingCart.getTotalValueItems());
            purchaseDetail.setDeliveryAddress(user.getAddress());
            purchaseDetail.setPaymentMethod("Tarjeta de credito");
            purchaseDetail.setItemsCart(itemsCart);

            Purchase purchase = new Purchase();
            purchase.setStatus("Generada");
            purchase.setDate(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
            int IVA = (int) (shoppingCart.getAccumulatedValue() * 0.19);
            purchaseDetail.setIVA(IVA);
            int deliveryCost = 5000;
            purchaseDetail.setDeliveryCost(deliveryCost);
            purchase.setTotalValue(purchaseDetail.getAccumulatedValue() + IVA + deliveryCost);
            purchase.setPurchaseDetail(purchaseDetail);
            purchase.setUser(user);

            purchaseService.save(purchase);
            message = "La compra se ha realizado con exito";

            shoppingCart.setTotalValueItems(0);
            shoppingCartService.save(shoppingCart);

            itemCartService.deleteByShoppingCart(shoppingCart);

        }

        List<Purchase> purchases = purchaseService.findByUser(user);
        model.addAttribute("mensaje", message);
        model.addAttribute("purchases", purchases);

        return "purchases/listPurchases";

    }


}
