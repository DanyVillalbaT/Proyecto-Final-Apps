package com.storecode.controllers;

import com.storecode.models.*;
import com.storecode.services.ItemCartService;
import com.storecode.services.PurchaseService;
import com.storecode.services.ShoppingCartService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Transactional
    @GetMapping("/user/createPurchase")
    public String createPurchase(Model model, RedirectAttributes redirectAttributes) {

        user = UserSessionSingleton.getINSTANCIA().getUserSession();
        ShoppingCart shoppingCart = shoppingCartService.findByUser(user);
        List<ItemCart> itemsCart = itemCartService.getByShoppingCart(shoppingCart);
        String message = null;

        if (itemsCart.isEmpty()) {
            message = "El carrito de compras se encuentra vacio";
            return "redirect:/navbar/shopping-cart";
        } else {
            PurchaseDetail purchaseDetail = new PurchaseDetail();
            purchaseDetail.setAccumulatedValue(shoppingCart.getTotalValueItems());
            purchaseDetail.setDeliveryAddress(user.getAddress());
            purchaseDetail.setPaymentMethod("Tarjeta de credito");
            purchaseDetail.setItemsCart(itemsCart);

            Purchase purchase = new Purchase();
            purchase.setStatus("Generada");
            purchase.setDate(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
            int IVA = (int) (shoppingCart.getTotalValueItems() * 0.19);
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

            redirectAttributes.addAttribute("userId", user.getId());
            redirectAttributes.addAttribute("purchaseId", purchase.getId());

            return "redirect:/pdfs/exportPurchase/pdf";

        }

    }


}
