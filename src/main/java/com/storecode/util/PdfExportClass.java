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
import jakarta.servlet.http.HttpServletResponse;

public class PdfExportClass {

    public PDFExport() {
        super();
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);


        Font hola = FontFactory.getFont(FontFactory.COURIER_OBLIQUE);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("OPCION 1", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("OPCION 2", hola));
        table.addCell(cell);

        cell.setPhrase(new Phrase("OPCION 3", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("OPCION 4", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("OPCION 5", font));
        table.addCell(cell);

    }

    private void writeTableData(PdfPTable table) {

        table.addCell("Dato 1");
        table.addCell("Dato 2");
        table.addCell("Dato 3");
        table.addCell("Dato 4");
        table.addCell("Dato 5");

    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(15);
        font.setColor(Color.BLACK);

        Paragraph p = new Paragraph("Factura Compra ", font);
        Paragraph p2 = new Paragraph("Id Factura : ", font);
        Paragraph p3 = new Paragraph("Fecha Compra : " , font);
        Paragraph p4 = new Paragraph("Nombre Persona : " , font);
        Paragraph p5 = new Paragraph("Cedula : " , font);


        p.setAlignment(Paragraph.ALIGN_CENTER);
        p2.setAlignment(Paragraph.ALIGN_JUSTIFIED);
        p3.setAlignment(Paragraph.ALIGN_JUSTIFIED);
        p4.setAlignment(Paragraph.ALIGN_JUSTIFIED);
        p5.setAlignment(Paragraph.ALIGN_JUSTIFIED);



        document.add(p);
        document.add(p4);
        document.add(p2);
        document.add(p3);
        document.add(p5);


        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {3.5f, 2.0f,1.5f, 2.0f, 2.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
