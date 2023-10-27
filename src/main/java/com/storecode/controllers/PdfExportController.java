package com.storecode.controllers;

import com.storecode.util.PdfExportClass;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

import com.lowagie.text.DocumentException;

@Controller
public class PdfExportController {

    @GetMapping("/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=factura.pdf";
        response.setHeader(headerKey, headerValue);

        PdfExportClass exporter = new PdfExportClass();
        exporter.export(response);

    }
}
