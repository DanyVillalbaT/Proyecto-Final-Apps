package com.storecode.controllers;

import com.lowagie.text.DocumentException;
import com.storecode.models.ItemDetail;
import com.storecode.models.Purchase;
import com.storecode.models.PurchaseDetail;
import com.storecode.models.User;
import com.storecode.services.ItemDetailService;
import com.storecode.services.PurchaseDetailService;
import com.storecode.services.PurchaseService;
import com.storecode.services.UserService;
import com.storecode.util.PdfExportClass;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/pdfs")
public class PdfExportController {

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private UserService userService;

    @Autowired
    private PurchaseDetailService purchaseDetailService;

    @Autowired
    private ItemDetailService itemDetailService;

    @GetMapping("/exportPurchase/pdf")
    public String exportToPDF(@RequestParam Long userId, @RequestParam Long purchaseId,  HttpServletResponse response, Model model) throws DocumentException, IOException {
        response.setContentType("application/pdf");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=factura.pdf";
        response.setHeader(headerKey, headerValue);

        Purchase purchase = purchaseService.getById(purchaseId);
        PurchaseDetail purchaseDetail = purchaseDetailService.getByPurchase(purchase);
        List<ItemDetail> itemsDetail = itemDetailService.getByPurchaseDetail(purchaseDetail);

        PdfExportClass exporter = new PdfExportClass(purchase, purchaseDetail ,itemsDetail);
        exporter.export(response);

        User user = userService.getByiId(userId);
        List<Purchase> purchases = purchaseService.findByUser(user);
        model.addAttribute("purchases", purchases);
        return "purchases/listPurchases";

    }
}
