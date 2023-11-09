package com.storecode.util;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
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
        this.itemDetail = itemsDetail;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("#", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Nombre producto", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Precio Unitario", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Cantidad", font));
        table.addCell(cell);

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

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(15);
        font.setColor(Color.BLACK);

        Paragraph p = new Paragraph("Factura Compra ", font);
        Paragraph p2 = new Paragraph("Id Factura : " + purchase.getId() , font);
        Paragraph p3 = new Paragraph("Fecha Compra : " + purchase.getDate() , font);
        Paragraph p4 = new Paragraph("Nombre Persona : " + purchase.getUser().getName() , font);
        Paragraph p5 = new Paragraph("Cedula : " + purchase.getUser().getDocument() , font);
        Paragraph p6 = new Paragraph("Dirección de entrega : " + purchaseDetail.getDeliveryAddress() , font);
        Paragraph p7 = new Paragraph("Método de pago : " + purchaseDetail.getPaymentMethod()) , font);


        p.setAlignment(Paragraph.ALIGN_CENTER);
        p2.setAlignment(Paragraph.ALIGN_JUSTIFIED);
        p3.setAlignment(Paragraph.ALIGN_JUSTIFIED);
        p4.setAlignment(Paragraph.ALIGN_JUSTIFIED);
        p5.setAlignment(Paragraph.ALIGN_JUSTIFIED);
        p6.setAlignment(Paragraph.ALIGN_JUSTIFIED);
        p7.setAlignment(Paragraph.ALIGN_JUSTIFIED);


        document.add(p);
        document.add(p2);
        document.add(p3);
        document.add(p4);
        document.add(p5);
        document.add(p6);
        document.add(p7);


        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {3.5f, 2.0f,1.5f, 2.0f, 2.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        Paragraph p8 = new Paragraph("IVA (19%) : " + purchaseDetail.getIVA(), font);
        Paragraph p9 = new Paragraph("Costo de envío : " + purchaseDetail.getDeliveryCost() , font);
        Paragraph p10 = new Paragraph("Total : " + purchase.getTotalValue() , font);

        document.add(p8);
        document.add(p9);
        document.add(p10);

        document.close();

    }
}
