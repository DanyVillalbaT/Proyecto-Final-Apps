package com.storecode.util;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import com.storecode.models.ItemCart;
import com.storecode.models.ItemDetail;
import com.storecode.models.Purchase;
import com.storecode.models.PurchaseDetail;
import jakarta.servlet.http.HttpServletResponse;


public class PdfExportClass {

    public PdfExportClass() {
        super();
    }

    private Purchase purchase;
    private PurchaseDetail purchaseDetail;
    private List<ItemDetail> itemsDetail;

    public PdfExportClass(Purchase purchase, PurchaseDetail purchaseDetail, List<ItemDetail> itemsDetail) {
        this.purchase = purchase;
        this.purchaseDetail = purchaseDetail;
        this.itemsDetail = itemsDetail;
    }

    private void writeTableHeader(PdfPTable table) {

        try {
            float[] columnWidths = {2f, 3f, 2f, 2f, 3f};
            table.setWidths(columnWidths);
        } catch (DocumentException e) {
            e.printStackTrace(); // o manejar la excepción de otra manera
        }

        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.WHITE);

        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPhrase(new Phrase("#", font));
        table.addCell(cell);

        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPhrase(new Phrase("Nombre producto", font));
        table.addCell(cell);

        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPhrase(new Phrase("Precio Unitario", font));
        table.addCell(cell);

        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPhrase(new Phrase("Cantidad", font));
        table.addCell(cell);

        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPhrase(new Phrase("Valor Acumulado", font));
        table.addCell(cell);

    }

    private void writeTableData(PdfPTable table) {

        for (ItemDetail itemDetail: itemsDetail) {

            table.addCell(String.valueOf(itemDetail.getId()));
            table.addCell(itemDetail.getProduct().getName());
            table.addCell(String.valueOf(itemDetail.getProduct().getPrice()));
            table.addCell(String.valueOf(itemDetail.getQuantityItems()));
            table.addCell(String.valueOf(itemDetail.getAccumulatedValue()));

        }

    }

//    HttpServletResponse response
    public void export(ByteArrayOutputStream baos) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4.rotate());
//        response.getOutputStream()
        PdfWriter.getInstance(document, baos);

        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(15);
        font.setColor(Color.BLACK);

        Font font2 = FontFactory.getFont(FontFactory.HELVETICA);
        font2.setColor(Color.BLACK);

        Image img = Image.getInstance("src/main/resources/static/img/logo.png");
        img.scaleAbsolute(100, 80);

        Paragraph p = new Paragraph("Factura Compra ", font);

        Chunk c1 = new Chunk("Id Factura: " , font);
        Chunk c2 = new Chunk(String.valueOf(purchase.getId()) , font2);
        Paragraph p2 = new Paragraph();
        p2.add(c1);
        p2.add(c2);

        Chunk c3 = new Chunk("Fecha Compra: " , font);
        Chunk c4 = new Chunk(String.valueOf(purchase.getDate()) , font2);
        Paragraph p3 = new Paragraph();
        p3.add(c3);
        p3.add(c4);

        Chunk c5 = new Chunk("Nombre Persona: " , font);
        Chunk c6 = new Chunk(purchase.getUser().getName() , font2);
        Paragraph p4 = new Paragraph();
        p4.add(c5);
        p4.add(c6);

        Chunk c7 = new Chunk("Cedula: " , font);
        Chunk c8 = new Chunk(purchase.getUser().getDocument() , font2);
        Paragraph p5 = new Paragraph();
        p5.add(c7);
        p5.add(c8);

        Chunk c9 = new Chunk("Dirección de entrega: " , font);
        Chunk c10 = new Chunk(purchaseDetail.getDeliveryAddress() , font2);
        Paragraph p6 = new Paragraph();
        p6.add(c9);
        p6.add(c10);

        Chunk c11 = new Chunk("Método de pago: " , font);
        Chunk c12 = new Chunk(purchaseDetail.getPaymentMethod() , font2);
        Paragraph p7 = new Paragraph();
        p7.add(c11);
        p7.add(c12);

        Paragraph saltoLinea = new Paragraph("\n");


        img.setAlignment(Paragraph.ALIGN_LEFT);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        p2.setAlignment(Paragraph.ALIGN_JUSTIFIED);
        p3.setAlignment(Paragraph.ALIGN_JUSTIFIED);
        p4.setAlignment(Paragraph.ALIGN_JUSTIFIED);
        p5.setAlignment(Paragraph.ALIGN_JUSTIFIED);
        p6.setAlignment(Paragraph.ALIGN_JUSTIFIED);
        p7.setAlignment(Paragraph.ALIGN_JUSTIFIED);


        document.add(img);
        document.add(p);
        document.add(saltoLinea);
        document.add(p2);
        document.add(saltoLinea);
        document.add(p3);
        document.add(saltoLinea);
        document.add(p4);
        document.add(saltoLinea);
        document.add(p5);
        document.add(saltoLinea);
        document.add(p6);
        document.add(saltoLinea);
        document.add(p7);
        document.add(saltoLinea);


        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {3.5f, 2.0f,1.5f, 2.0f, 2.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);
        document.add(saltoLinea);

        Chunk c13 = new Chunk("IVA (19%): " , font);
        Chunk c14 = new Chunk(String.valueOf(purchaseDetail.getIVA()) , font2);
        Paragraph p8 = new Paragraph();
        p8.add(c13);
        p8.add(c14);

        Chunk c15 = new Chunk("Costo de envío: " , font);
        Chunk c16 = new Chunk(String.valueOf(purchaseDetail.getDeliveryCost()) , font2);
        Paragraph p9 = new Paragraph();
        p9.add(c15);
        p9.add(c16);

        Chunk c17 = new Chunk("Total: " , font);
        Chunk c18 = new Chunk(String.valueOf(purchase.getTotalValue()) , font2);
        Paragraph p10 = new Paragraph();
        p10.add(c17);
        p10.add(c18);

        document.add(p8);
        document.add(saltoLinea);
        document.add(p9);
        document.add(saltoLinea);
        document.add(p10);

        document.close();

    }
}
