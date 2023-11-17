package com.storecode.controllers;

import com.storecode.models.*;
import com.storecode.services.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    @Autowired
    private ItemDetailService itemDetailService;

    @Autowired
    private PurchaseDetailService purchaseDetailService;

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
            Purchase purchase = new Purchase();
            purchase.setStatus("Generada");
            purchase.setDate(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
            purchase.setUser(user);

            PurchaseDetail purchaseDetail = new PurchaseDetail();
            int IVA = (int) (shoppingCart.getTotalValueItems() * 0.19);
            purchaseDetail.setIVA(IVA);
            int deliveryCost = 5000;
            purchaseDetail.setDeliveryCost(deliveryCost);
            purchaseDetail.setDeliveryAddress(user.getAddress());
            purchaseDetail.setPaymentMethod("Tarjeta de credito");

            int accumulatedValue = 0;
            List<ItemDetail> itemsDetail = new ArrayList<>();

            for (ItemCart itemCart: itemsCart) {
                ItemDetail itemDetail = new ItemDetail();
                itemDetail.setProduct(itemCart.getProduct());
                itemDetail.setQuantityItems(itemCart.getQuantityItems());
                itemDetail.setAccumulatedValue(itemCart.getAccumulatedValue());
                accumulatedValue += itemCart.getAccumulatedValue();
                itemDetailService.save(itemDetail);
                itemsDetail.add(itemDetail);
            }

            purchaseDetail.setAccumulatedValue(accumulatedValue);
            purchase.setTotalValue(accumulatedValue + IVA + deliveryCost);
            purchaseService.save(purchase);
            purchaseDetail.setPurchase(purchase);
            purchaseDetailService.save(purchaseDetail);

            for (ItemDetail itemDetail: itemsDetail) {
                itemDetail.setPurchaseDetail(purchaseDetail);
                itemDetailService.save(itemDetail);
            }

            shoppingCart.setTotalValueItems(0);
            shoppingCartService.save(shoppingCart);

            itemCartService.deleteByShoppingCart(shoppingCart);

            redirectAttributes.addAttribute("userId", user.getId());
            redirectAttributes.addAttribute("purchaseId", purchase.getId());

            System.out.println("hola");
            return "redirect:/pdfs/exportPurchase/pdf";

        }

    }


}
