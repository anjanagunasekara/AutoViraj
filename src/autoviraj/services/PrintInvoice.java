/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoviraj.services;

/**
 *
 * @author Sehan Rathnayake
 */
import autoviraj.gui.InputOutputForm;
import autoviraj.models.Invoice;
import autoviraj.models.InvoiceItem;
import autoviraj.models.InvoiceService;
import autoviraj.utils.PdfUtils;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.Font;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrintInvoice {

    /**
     * @param args the command line arguments
     */
    private static BaseColor themeColor = BaseColor.RED;
       private static BaseFont basefont;
    
    public static void printInvoice(Invoice invoice) throws Exception {

        Document layoutDocument = new Document();
        String path = InputOutputForm.invoiceFilePath + "//" + invoice.getInvoiceId() + ".pdf";
        PdfWriter docWriter = PdfWriter.getInstance(layoutDocument, new FileOutputStream(path));
        layoutDocument.setMargins(0, 0, 15, 5);
        layoutDocument.open();
        basefont = BaseFont.createFont(InputOutputForm.binPath + "//Lato-Bold.ttf", BaseFont.WINANSI,BaseFont.EMBEDDED,true);
       
        addTitle(layoutDocument,invoice.getInvoiceId());

        addTable(layoutDocument, invoice);

        layoutDocument.close();

    }

    public static void addTitle(Document layoutDocument,int invoiceId) throws DocumentException {
        try {

            Image img = Image.getInstance(PrintInvoice.class.getResource("/resources/Berner_logo.jpg"));
            img.scaleToFit(180, 180);
            img.setAlignment(Image.MIDDLE);
            layoutDocument.add(img);

            Font boldFont = new Font(basefont, 18, Font.BOLD);
            Paragraph title = new Paragraph("SERVICE INVOICE", boldFont);
            PdfPCell titleCell = new PdfPCell(title);
            titleCell.setBorder(PdfPCell.NO_BORDER);
            titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            titleCell.setColspan(3);

            PdfPCell emptycell = new PdfPCell(new Paragraph(""));
            emptycell.setBorder(PdfPCell.NO_BORDER);
            emptycell.setRowspan(2);

            PdfPTable header = new PdfPTable(new float[]{55, 60, 55});
            Font addressFont = new Font(basefont, 11);
            Font teleFont = new Font(basefont, 9);
            
            Phrase address1 = new Phrase();
            address1.add(new Chunk("BERNER Head Office\n", new Font(basefont, 12, Font.BOLD)));
            address1.add(new Chunk("Ignite Enterprises,\nNelumkanuwa,\nKatupotha,\nKurunegala.", addressFont));

            PdfPCell address1Cell = new PdfPCell(address1);

            address1Cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            address1Cell.setBorder(PdfPCell.NO_BORDER);

            Phrase address2 = new Phrase();
            address2.add(new Chunk("BERNER Automobile\nPromotion Team\n", new Font(basefont, 12, Font.BOLD)));
            address2.add(new Chunk("187/B Thalalla east,\nKekanadura,\nMatara.", addressFont));

            PdfPCell address2Cell = new PdfPCell(address2);
            address2Cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            address2Cell.setBorder(PdfPCell.NO_BORDER);

            Paragraph telephone1 = new Paragraph("Tel/Fax : +94375734208\nE-mail : ignite.lanka@gmail.com\nWeb : www.berner.eu", teleFont);
            PdfPCell telephone1Cell = new PdfPCell(telephone1);
            telephone1Cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            telephone1Cell.setBorder(PdfPCell.NO_BORDER);

            Paragraph telephone2 = new Paragraph("Tel : +9412258244\nMob : +94719969039\nE-mail: bernerautomobile@gmail.com", teleFont);
            PdfPCell telephone2Cell = new PdfPCell(telephone2);
            telephone2Cell.setHorizontalAlignment(Element.ALIGN_LEFT);

            telephone2Cell.setBorder(PdfPCell.NO_BORDER);

            PdfPCell emptyCell2 = new PdfPCell(new Paragraph(" "));
            emptyCell2.setColspan(3);
            emptyCell2.setBorder(PdfPCell.NO_BORDER);

            header.addCell(titleCell);
            header.addCell(emptyCell2);
            header.addCell(address1Cell);
            header.addCell(emptycell);
            header.addCell(address2Cell);
            header.addCell(telephone1Cell);
            header.addCell(telephone2Cell);
            header.addCell(emptyCell2);

            layoutDocument.add(header);
 
            //400, 120, 80
            PdfPTable invoiceNoTable = new PdfPTable(new float[]{105, 55, 125});
            PdfPCell emptycell3 = new PdfPCell(new Paragraph(""));
             PdfPCell emptycell4 = new PdfPCell(new Paragraph(" "));
            emptycell3.setBorder(PdfPCell.NO_BORDER);
            emptycell4.setColspan(3);
            emptycell4.setBorder(PdfPCell.NO_BORDER);
             
            invoiceNoTable.addCell(emptycell3);

            Font invoiceNoHeadingFont = new Font(basefont, 14, Font.BOLD);
            Paragraph invoiceNoHeading = new Paragraph("Invoice No : ", invoiceNoHeadingFont);
            PdfPCell invoiceNoHeadingCell = new PdfPCell(invoiceNoHeading);
            invoiceNoHeadingCell.setBorder(PdfPCell.NO_BORDER);

            Font invoiceNoValueFont = new Font(basefont, 14, Font.BOLD);
            invoiceNoValueFont.setColor(themeColor);
            Paragraph invoiceNoValue = new Paragraph(String.format("%05d", invoiceId), invoiceNoValueFont);
            PdfPCell invoiceNoValueCell = new PdfPCell(invoiceNoValue);
            invoiceNoValueCell.setBorder(PdfPCell.NO_BORDER);

            invoiceNoTable.addCell(invoiceNoHeadingCell);
            invoiceNoTable.addCell(invoiceNoValueCell);
            invoiceNoTable.addCell(emptycell4);
            layoutDocument.add(invoiceNoTable);

        } catch (BadElementException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public static void addTable(Document layoutDocument, Invoice invoice) throws DocumentException {

        Font headingFont = new Font(basefont, 12);
        Font valueFont = new Font(Font.FontFamily.COURIER, 12);

        PdfPTable table = new PdfPTable(new float[]{50, 230, 70, 70, 80});

        PdfPTable customerTable = new PdfPTable(new float[]{35, 85});

        CellBackground cellBackgroundCustomer = new CellBackground(0, 4, 0, 2);

        Paragraph customerHeading = new Paragraph(" Customer  :", headingFont);
        PdfPCell customerHeadingCell = new PdfPCell(customerHeading);
        customerHeadingCell.setBorder(PdfPCell.NO_BORDER);
        customerHeadingCell.setRowspan(4);
        customerTable.addCell(customerHeadingCell);

        Font adressFont = new Font(Font.FontFamily.COURIER, 12);

        PdfPCell customerNameCell = new PdfPCell(new Paragraph(invoice.getCustomer().getName(), valueFont));
        customerNameCell.setBorder(PdfPCell.NO_BORDER);
        customerNameCell.setRowspan(4);
        customerTable.addCell(customerNameCell);
       
        customerTable.setSpacingBefore(4);
        customerTable.setSpacingAfter(6);

        PdfPCell customerCell = new PdfPCell(customerTable);
        customerCell.setColspan(2);
        customerCell.setRowspan(3);
        customerCell.setBorder(PdfPCell.NO_BORDER);
        customerCell.setCellEvent(cellBackgroundCustomer);
        table.addCell(customerCell);

        PdfPTable vehicleInfoTable = new PdfPTable(new float[]{50, 5, 60});

        Paragraph dateHeading = new Paragraph("  Date", headingFont);
        PdfPCell dateHeadingCell = new PdfPCell(dateHeading);
        dateHeadingCell.setBorder(PdfPCell.NO_BORDER);

        Paragraph dateValue = new Paragraph(PdfUtils.formatDate(invoice.getDate()), valueFont);
        PdfPCell dateValueCell = new PdfPCell(dateValue);
        dateValueCell.setBorder(PdfPCell.NO_BORDER);

        Paragraph vehicleNoHeading = new Paragraph("  Vehicle No", headingFont);
        PdfPCell vehicleNoHeadingCell = new PdfPCell(vehicleNoHeading);
        vehicleNoHeadingCell.setBorder(PdfPCell.NO_BORDER);

        Paragraph vehicleNoValue = new Paragraph(invoice.getVehicle().getRegNo(), valueFont);
        PdfPCell vehicleNoValueCell = new PdfPCell(vehicleNoValue);
        vehicleNoValueCell.setBorder(PdfPCell.NO_BORDER);

        Paragraph vehicleTypeHeading = new Paragraph("  Vehicle Type", headingFont);
        PdfPCell vehicleTypeHeadingCell = new PdfPCell(vehicleTypeHeading);
        vehicleTypeHeadingCell.setBorder(PdfPCell.NO_BORDER);

        Paragraph vehicleTypeValue = new Paragraph(invoice.getVehicle().getType(), valueFont);
        PdfPCell vehicleTypeValueCell = new PdfPCell(vehicleTypeValue);
        vehicleTypeValueCell.setBorder(PdfPCell.NO_BORDER);

        PdfPCell midCell = new PdfPCell(new Paragraph(":", headingFont));
        midCell.setBorder(PdfPCell.NO_BORDER);

        vehicleInfoTable.setSpacingBefore(4);
        vehicleInfoTable.setSpacingAfter(8);
        vehicleInfoTable.addCell(dateHeadingCell);
        vehicleInfoTable.addCell(midCell);
        vehicleInfoTable.addCell(dateValueCell);
        vehicleInfoTable.addCell(vehicleNoHeadingCell);
        vehicleInfoTable.addCell(midCell);
        vehicleInfoTable.addCell(vehicleNoValueCell);
        vehicleInfoTable.addCell(vehicleTypeHeadingCell);
        vehicleInfoTable.addCell(midCell);
        vehicleInfoTable.addCell(vehicleTypeValueCell);
      
        PdfPCell others = new PdfPCell(vehicleInfoTable);
        CellBackground cellBackgroundOther = new CellBackground(0, 4, 2, 0);
        others.setColspan(3);
        others.setRowspan(3);
        others.setBorder(PdfPCell.NO_BORDER);
        others.setCellEvent(cellBackgroundOther);

        table.addCell(others);

        PdfPTable itemTable = new PdfPTable(new float[]{50, 230, 70, 70, 80});

        Paragraph itemIdHeading = new Paragraph("Item ID", headingFont);
        PdfPCell itemIdHeadingCell = new PdfPCell(itemIdHeading);
        itemIdHeadingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        itemIdHeadingCell.setBackgroundColor(themeColor);
        itemTable.addCell(itemIdHeadingCell);

        Paragraph descriptionHeading = new Paragraph("Description", headingFont);
        PdfPCell descriptionHeadingCell = new PdfPCell(descriptionHeading);
        descriptionHeadingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        descriptionHeadingCell.setBackgroundColor(themeColor);
        itemTable.addCell(descriptionHeadingCell);

        Paragraph quanityHeading = new Paragraph("Quantity", headingFont);
        PdfPCell quantityHeadingCell = new PdfPCell(quanityHeading);
        quantityHeadingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        quantityHeadingCell.setBackgroundColor(themeColor);
        itemTable.addCell(quantityHeadingCell);

        Paragraph rateHeading = new Paragraph("Rate(Rs.)", headingFont);
        PdfPCell rateHeadingCell = new PdfPCell(rateHeading);
        rateHeadingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        rateHeadingCell.setBackgroundColor(themeColor);
        itemTable.addCell(rateHeadingCell);

        Paragraph amountHeading = new Paragraph("Amount(Rs.)", headingFont);
        PdfPCell amountHeadingCell = new PdfPCell(amountHeading);
        amountHeadingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        amountHeadingCell.setBackgroundColor(themeColor);
        itemTable.addCell(amountHeadingCell);
        // items

        for (InvoiceService service : invoice.getInvoiceServices()) {

            Paragraph serviceID = new Paragraph(service.getServiceId(), valueFont);
            PdfPCell serviceIDCell = new PdfPCell(serviceID);
            serviceIDCell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
            itemTable.addCell(serviceIDCell);

            Paragraph description = new Paragraph(service.getName(), valueFont);
            PdfPCell descriptionCell = new PdfPCell(description);
            descriptionCell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
            itemTable.addCell(descriptionCell);

            Paragraph quantity = new Paragraph(PdfUtils.formatDouble(service.getUnits())
                    + service.getUnitName(), valueFont);
            PdfPCell quantityCell = new PdfPCell(quantity);
            quantityCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            quantityCell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
            itemTable.addCell(quantityCell);

            Paragraph unitPrice = new Paragraph(PdfUtils.formatDouble(service.getUnitPrice()), valueFont);
            PdfPCell unitPriceCell = new PdfPCell(unitPrice);
            unitPriceCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            unitPriceCell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
            itemTable.addCell(unitPriceCell);

            Paragraph amount = new Paragraph(PdfUtils.formatDouble(service.getUnits() * service.getUnitPrice()), valueFont);
            PdfPCell amountCell = new PdfPCell(amount);
            amountCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            amountCell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
            itemTable.addCell(amountCell);

        }

        for (InvoiceItem item : invoice.getInvoiceItems()) {

            Paragraph itemID = new Paragraph(item.getItemId(), valueFont);
            PdfPCell itemIDCell = new PdfPCell(itemID);
            itemIDCell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
            itemTable.addCell(itemIDCell);

            Paragraph description = new Paragraph(item.getName(), valueFont);
            PdfPCell descriptionCell = new PdfPCell(description);
            descriptionCell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
            itemTable.addCell(descriptionCell);

            Paragraph quantity = new Paragraph(PdfUtils.formatDouble(item.getUnits())
                    + item.getUnitName(), valueFont);
            PdfPCell quantityCell = new PdfPCell(quantity);
            quantityCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            quantityCell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
            itemTable.addCell(quantityCell);

            Paragraph unitPrice = new Paragraph(PdfUtils.formatDouble(item.getUnitPrice()), valueFont);
            PdfPCell unitPriceCell = new PdfPCell(unitPrice);
            unitPriceCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            unitPriceCell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
            itemTable.addCell(unitPriceCell);

            Paragraph amount = new Paragraph(PdfUtils.formatDouble(item.getUnits() * item.getUnitPrice()), valueFont);
            PdfPCell amountCell = new PdfPCell(amount);
            amountCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            amountCell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
            itemTable.addCell(amountCell);

        }
        int noOfEmptyRows = 10 - invoice.getInvoiceServices().size() - invoice.getInvoiceItems().size();
        for (int i = 0; i < 5 * noOfEmptyRows; i++) {
            Paragraph amount = new Paragraph(" ");
            PdfPCell emptyCell = new PdfPCell(amount);
            emptyCell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
            itemTable.addCell(emptyCell);
        }
        PdfPCell itemCell = new PdfPCell(itemTable);
        itemCell.setColspan(5);
        table.addCell(itemCell);

        PdfPTable meterReadings = new PdfPTable(new float[]{50, 5, 60});

        Paragraph currentMeterHeading = new Paragraph("  Current Meter (km)", headingFont);
        PdfPCell currentMeterHeadingCell = new PdfPCell(currentMeterHeading);
        currentMeterHeadingCell.setBorder(PdfPCell.NO_BORDER);

        Paragraph currentMeterValue = new Paragraph(PdfUtils.formatMeterReading((int)invoice.getCurrentMeter()), valueFont);
        PdfPCell currentMeterValueCell = new PdfPCell(currentMeterValue);
        currentMeterValueCell.setBorder(PdfPCell.NO_BORDER);
        
        Paragraph nextServiceHeading = new Paragraph("  Next Service Due (km)", headingFont);
        PdfPCell nextServiceHeadingCell = new PdfPCell(nextServiceHeading);
        nextServiceHeadingCell.setBorder(PdfPCell.NO_BORDER);

        Paragraph nextServiceValue = new Paragraph(PdfUtils.formatMeterReading((int)invoice.getNextService()), valueFont);
        PdfPCell nextServiceValueCell = new PdfPCell(nextServiceValue);
        nextServiceValueCell.setBorder(PdfPCell.NO_BORDER);

        meterReadings.setSpacingBefore(4);
        meterReadings.setSpacingAfter(8);
        meterReadings.addCell(currentMeterHeadingCell);
        meterReadings.addCell(midCell);
        meterReadings.addCell(currentMeterValueCell);
        meterReadings.addCell(nextServiceHeadingCell);
        meterReadings.addCell(midCell);
        meterReadings.addCell(nextServiceValueCell);

        PdfPCell meterReadingsCell = new PdfPCell(meterReadings);
        meterReadingsCell.setColspan(3);
        meterReadingsCell.setRowspan(4);
        //to.setBackgroundColor(BaseColor.YELLOW);
        CellBackground cellBackgroundOther2 = new CellBackground(4, 0, 0, 4);
        meterReadingsCell.setBorder(PdfPCell.NO_BORDER);
        meterReadingsCell.setCellEvent(cellBackgroundOther2);

        table.addCell(meterReadingsCell);

        Font totalFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

        Paragraph subTotal = new Paragraph(" Sub Total", headingFont);
        PdfPCell subTotalCell = new PdfPCell(subTotal);
        subTotalCell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
        table.addCell(subTotalCell);

        Paragraph subTotalVal = new Paragraph(PdfUtils.formatDouble(invoice.getSubtotal()), valueFont);
        PdfPCell subTotalValCell = new PdfPCell(subTotalVal);
        subTotalValCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        subTotalValCell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
        table.addCell(subTotalValCell);

        Paragraph discount = new Paragraph(" Discount", headingFont);
        PdfPCell discountCell = new PdfPCell(discount);
        discountCell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
        table.addCell(discountCell);

        Paragraph discountVal = new Paragraph(PdfUtils.formatDouble(invoice.getDiscount()), valueFont);
        PdfPCell discountValCell = new PdfPCell(discountVal);
        discountValCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        discountValCell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
        table.addCell(discountValCell);

        Paragraph empty = new Paragraph(" ");
        PdfPCell emptyCell = new PdfPCell(empty);
        emptyCell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
        table.addCell(emptyCell);
        table.addCell(emptyCell);

        Paragraph netTotal = new Paragraph(" Net total", totalFont);
        PdfPCell netTotalCell = new PdfPCell(netTotal);
        netTotalCell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
        table.addCell(netTotalCell);

        Paragraph netTotalVal = new Paragraph(PdfUtils.formatDouble(invoice.getNetTotal()), valueFont);

        PdfPCell netTotalValCell = new PdfPCell(netTotalVal);
        netTotalValCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        netTotalValCell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
        table.addCell(netTotalValCell);

        PdfPCell bottomline = new PdfPCell(new Paragraph(" "));
        bottomline.setColspan(5);
        // bottomline.setBorderColor(themeColor);
        bottomline.setBorderWidth(2);
        bottomline.setBorder(Rectangle.NO_BORDER);

        table.addCell(bottomline);

        PdfPTable footer = new PdfPTable(new float[]{150, 50});

        Paragraph signature = new Paragraph("");
        PdfPCell signatureCell = new PdfPCell(signature);
        signatureCell.setBorder(PdfPCell.BOTTOM);

        Paragraph cashier = new Paragraph("Cashier", headingFont);
        PdfPCell cashierCell = new PdfPCell(cashier);
        cashierCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cashierCell.setBorder(PdfPCell.NO_BORDER);

        PdfPCell emptyCell2 = new PdfPCell(new Paragraph(" "));
        emptyCell2.setColspan(2);
        emptyCell2.setBorder(PdfPCell.NO_BORDER);

        PdfPCell emptyCell3 = new PdfPCell(new Paragraph(" "));
        emptyCell3.setRowspan(2);

        emptyCell3.setBorder(PdfPCell.NO_BORDER);

        footer.addCell(emptyCell2);
        footer.addCell(emptyCell2);
        footer.addCell(emptyCell3);
        footer.addCell(signatureCell);
        footer.addCell(cashierCell);

        PdfPCell footerCell = new PdfPCell(footer);
        footerCell.setColspan(5);
        footerCell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(footerCell);

        layoutDocument.add(table);
    }
}

class CellBackground implements PdfPCellEvent {

    float top;
    float bottom;
    float left;
    float right;

    public CellBackground(float top, float bottom, float left, float right) {
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
    }

    public void cellLayout(PdfPCell cell, Rectangle rect,
            PdfContentByte[] canvas) {

        PdfContentByte cb = canvas[PdfPTable.BACKGROUNDCANVAS];

        cb.setLineWidth(.5f);
        cb.roundRectangle(
                rect.getLeft() + this.left, rect.getBottom() + this.bottom, rect.getWidth() - this.left - this.right,
                rect.getHeight() - this.top - this.bottom, 0);
        cb.setColorFill(BaseColor.WHITE);
        //cb.setRGBColorStroke(50, 50, 50);
        cb.fillStroke();

    }
}
