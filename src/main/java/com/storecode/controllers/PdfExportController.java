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
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.concurrent.CompletableFuture;


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
        exportToPDFRedirect(userId, purchaseId, response);
        return "user/login";

    }

    public void exportToPDFRedirect(Long userId, Long purchaseId,  HttpServletResponse response) throws DocumentException, IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        Purchase purchase = purchaseService.getById(purchaseId);
        PurchaseDetail purchaseDetail = purchaseDetailService.getByPurchase(purchase);
        List<ItemDetail> itemsDetail = itemDetailService.getByPurchaseDetail(purchaseDetail);

//        User user = userService.getByiId(userId);
//        List<Purchase> purchases = purchaseService.findByUser(user);
//        model.addAttribute("purchases", purchases);

        try {
            PdfExportClass exporter = new PdfExportClass(purchase, purchaseDetail, itemsDetail);
            exporter.export(baos);
        } catch (DocumentException | IOException e) {
            // Manejar las excepciones según sea necesario
            e.printStackTrace();
        }

        // Escribir el contenido del PDF en el response
        try (OutputStream os = response.getOutputStream()) {
            response.setContentType("application/pdf");
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=factura.pdf";
            response.setHeader(headerKey, headerValue);

            os.write(baos.toByteArray());
            os.flush();
        } catch (IOException e) {
            // Manejar las excepciones según sea necesario
            e.printStackTrace();
        }

        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(5000); // Puedes ajustar esto según sea necesario
                // Realizar la redirección
                response.sendRedirect("/purchase/listPurchases");
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        });

        response.sendRedirect("/purchase/listPurchases");
        System.out.println("hola");

    }

    @PostMapping("/exportPurchaseHistory/pdf")
    public String exportPDF(@RequestParam Long userId, @RequestParam Long purchaseId,  HttpServletResponse response, Model model) throws DocumentException, IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        Purchase purchase = purchaseService.getById(purchaseId);
        PurchaseDetail purchaseDetail = purchaseDetailService.getByPurchase(purchase);
        List<ItemDetail> itemsDetail = itemDetailService.getByPurchaseDetail(purchaseDetail);

        User user = userService.getByiId(userId);
        List<Purchase> purchases = purchaseService.findByUser(user);
        model.addAttribute("purchases", purchases);

        try {
            PdfExportClass exporter = new PdfExportClass(purchase, purchaseDetail, itemsDetail);
            exporter.export(baos);
        } catch (DocumentException | IOException e) {
            // Manejar las excepciones según sea necesario
            e.printStackTrace();
        }

        // Escribir el contenido del PDF en el response
        try (OutputStream os = response.getOutputStream()) {
            response.setContentType("application/pdf");
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=factura.pdf";
            response.setHeader(headerKey, headerValue);

            os.write(baos.toByteArray());
            os.flush();
        } catch (IOException e) {
            // Manejar las excepciones según sea necesario
            e.printStackTrace();
        }

        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(1000); // Puedes ajustar esto según sea necesario
                // Realizar la redirección
                response.sendRedirect("/purchase/listPurchases");
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        });

        // Devolver el nombre de la vista (plantilla) a la cual redirigir desde el lado del servidor
        return "redirect:/purchase/listPurchases";

    }

//    @GetMapping("/exportPurchase/pdf")
//    public String exportToPDF(@RequestParam Long userId, @RequestParam Long purchaseId,  HttpServletResponse response, Model model) throws DocumentException, IOException {
//        response.setContentType("application/pdf");
//
//        String headerKey = "Content-Disposition";
//        String headerValue = "attachment; filename=factura.pdf";
//        response.setHeader(headerKey, headerValue);
//
//        Purchase purchase = purchaseService.getById(purchaseId);
//        PurchaseDetail purchaseDetail = purchaseDetailService.getByPurchase(purchase);
//        List<ItemDetail> itemsDetail = itemDetailService.getByPurchaseDetail(purchaseDetail);
//
//        PdfExportClass exporter = new PdfExportClass(purchase, purchaseDetail ,itemsDetail);
//        exporter.export(response);
//
//        User user = userService.getByiId(userId);
//        List<Purchase> purchases = purchaseService.findByUser(user);
//        model.addAttribute("purchases", purchases);
//        return "purchase/listPurchases";
//    }

    @GetMapping("/exportPurchase/pdf/{userId}/{purchaseId}")
    public String exportToPDF(@PathVariable("userId") long userId, @PathVariable("purchaseId") long purchaseId,
                              HttpServletResponse response, Model model) throws DocumentException, IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        Purchase purchase = purchaseService.getById(purchaseId);
        PurchaseDetail purchaseDetail = purchaseDetailService.getByPurchase(purchase);
        List<ItemDetail> itemsDetail = itemDetailService.getByPurchaseDetail(purchaseDetail);

        User user = userService.getByiId(userId);
        List<Purchase> purchases = purchaseService.findByUser(user);
        model.addAttribute("purchases", purchases);

        try {
            PdfExportClass exporter = new PdfExportClass(purchase, purchaseDetail, itemsDetail);
            exporter.export(baos);
        } catch (DocumentException | IOException e) {
            // Manejar las excepciones según sea necesario
            e.printStackTrace();
        }

        // Escribir el contenido del PDF en el response
        try (OutputStream os = response.getOutputStream()) {
            response.setContentType("application/pdf");
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=factura.pdf";
            response.setHeader(headerKey, headerValue);

            os.write(baos.toByteArray());
            os.flush();
        } catch (IOException e) {
            // Manejar las excepciones según sea necesario
            e.printStackTrace();
        }

        // Devolver el nombre de la vista (plantilla)
        return "purchase/listPurchases";

    }
}
