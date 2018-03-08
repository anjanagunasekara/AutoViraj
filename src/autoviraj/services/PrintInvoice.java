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
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrintInvoice {

    /**
     * @param args the command line arguments
     */
    
    public static void printInvoice(Invoice invoice) throws Exception {

        Document layoutDocument = new Document();
        String path = "src/resources/invoice.pdf";
        PdfWriter docWriter = PdfWriter.getInstance(layoutDocument, new FileOutputStream(path));
        layoutDocument.open();

        addTitle(layoutDocument);

        addTable(layoutDocument, invoice);

        layoutDocument.close();

    }

    public static void addTitle(Document layoutDocument) throws DocumentException {
        try {
            Image img = Image.getInstance("C:\\Users\\Sehan Rathnayake\\Pictures\\garage.jpg");
            img.scaleToFit(80, 80);
            img.setAlignment(Image.MIDDLE);
            layoutDocument.add(img);

            Font boldFont = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
            Paragraph title = new Paragraph("SERVICE INVOICE", boldFont);
            title.setAlignment(Element.ALIGN_CENTER);
            layoutDocument.add(title);
            
            PdfPTable invoiceNoTable = new PdfPTable(new float[]{400,120,80});
            PdfPCell emptycell=new PdfPCell(new Paragraph(""));
            emptycell.setBorder(PdfPCell.NO_BORDER);
            
            invoiceNoTable.addCell(emptycell);
            
            Font invoiceNoHeadingFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            Paragraph invoiceNoHeading = new Paragraph("Invoice No : ",invoiceNoHeadingFont);
            PdfPCell invoiceNoHeadingCell =new PdfPCell(invoiceNoHeading);
            invoiceNoHeadingCell.setBorder(PdfPCell.NO_BORDER);
            
            Font invoiceNoValueFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            invoiceNoValueFont.setColor(BaseColor.ORANGE);
            Paragraph invoiceNoValue = new Paragraph("0001",invoiceNoValueFont);
            PdfPCell invoiceNoValueCell =new PdfPCell(invoiceNoValue);
            invoiceNoValueCell.setBorder(PdfPCell.NO_BORDER);
            
            invoiceNoTable.addCell(invoiceNoHeadingCell);
            invoiceNoTable.addCell(invoiceNoValueCell);
            layoutDocument.add(invoiceNoTable);
            
        } catch (BadElementException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public static void addTable(Document layoutDocument, Invoice invoice) throws DocumentException {
        
        Font headingFont = new Font(Font.FontFamily.HELVETICA, 10);
        Font valueFont = new Font(Font.FontFamily.COURIER, 10);
                
        PdfPTable table = new PdfPTable(new float[]{50, 230, 70, 70, 80});

        PdfPTable customerTable = new PdfPTable(new float[]{35, 85});

        CellBackground cellBackgroundCustomer = new CellBackground(0, 4, 0, 2);

        Paragraph customerHeading = new Paragraph(" Customer  :", headingFont);
        PdfPCell customerHeadingCell = new PdfPCell(customerHeading);
        customerHeadingCell.setBorder(PdfPCell.NO_BORDER);
        customerHeadingCell.setRowspan(4);
        customerTable.addCell(customerHeadingCell);

        Font adressFont = new Font(Font.FontFamily.COURIER, 10);

        PdfPCell customerNameCell = new PdfPCell(new Paragraph(invoice.getCustomer().getName(), valueFont));
        customerNameCell.setBorder(PdfPCell.NO_BORDER);
        customerTable.addCell(customerNameCell);
        String[] address = invoice.getCustomer().getAddress().split(",");
        for (int i = 0; i < address.length; i++) {
            PdfPCell customerDataCell = new PdfPCell(new Paragraph(address[i].trim(), adressFont));
            customerDataCell.setBorder(PdfPCell.NO_BORDER);
            customerTable.addCell(customerDataCell);
        }

        customerTable.setSpacingBefore(4);
        customerTable.setSpacingAfter(6);

        PdfPCell customerCell = new PdfPCell(customerTable);
        customerCell.setColspan(2);
        customerCell.setRowspan(4);
        customerCell.setBorder(PdfPCell.NO_BORDER);
        customerCell.setCellEvent(cellBackgroundCustomer);
        table.addCell(customerCell);

        PdfPTable vehicleInfoTable = new PdfPTable(new float[]{50,5,60});

        Paragraph dateHeading = new Paragraph("  Date",headingFont);
        PdfPCell dateHeadingCell = new PdfPCell(dateHeading);
        dateHeadingCell.setBorder(PdfPCell.NO_BORDER);

        Paragraph dateValue= new Paragraph(PdfUtils.formatDate(invoice.getDate()),valueFont);
        PdfPCell dateValueCell = new PdfPCell(dateValue);
        dateValueCell.setBorder(PdfPCell.NO_BORDER);

        Paragraph vehicleNoHeading = new Paragraph("  Vehicle No",headingFont);
        PdfPCell vehicleNoHeadingCell = new PdfPCell(vehicleNoHeading);
        vehicleNoHeadingCell.setBorder(PdfPCell.NO_BORDER);

        Paragraph vehicleNoValue = new Paragraph(invoice.getVehicle().getRegNo(),valueFont);
        PdfPCell vehicleNoValueCell = new PdfPCell(vehicleNoValue);
        vehicleNoValueCell.setBorder(PdfPCell.NO_BORDER);

        Paragraph vehicleTypeHeading = new Paragraph("  Vehicle Type",headingFont);
        PdfPCell vehicleTypeHeadingCell = new PdfPCell(vehicleTypeHeading);
        vehicleTypeHeadingCell.setBorder(PdfPCell.NO_BORDER);

        Paragraph vehicleTypeValue = new Paragraph(invoice.getVehicle().getType(),valueFont);
        PdfPCell vehicleTypeValueCell = new PdfPCell(vehicleTypeValue);
        vehicleTypeValueCell.setBorder(PdfPCell.NO_BORDER);

        Paragraph vehicleModelHeading = new Paragraph("  Vehicle Model",headingFont);
        PdfPCell vehicleModelHeadingCell = new PdfPCell(vehicleModelHeading);
        vehicleModelHeadingCell.setBorder(PdfPCell.NO_BORDER);

        Paragraph vehicleModelValue = new Paragraph(invoice.getVehicle().getModel(),valueFont);
        PdfPCell vehicleModelValueCell = new PdfPCell(vehicleModelValue);
        vehicleModelValueCell.setBorder(PdfPCell.NO_BORDER);
        
        PdfPCell midCell = new PdfPCell(new Paragraph(":",headingFont));
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
        vehicleInfoTable.addCell(vehicleModelHeadingCell);
         vehicleInfoTable.addCell(midCell);
        vehicleInfoTable.addCell(vehicleModelValueCell);

        PdfPCell others = new PdfPCell(vehicleInfoTable);
        CellBackground cellBackgroundOther = new CellBackground(0, 4, 2, 0);
        others.setColspan(3);
        others.setRowspan(4);
        others.setBorder(PdfPCell.NO_BORDER);
        others.setCellEvent(cellBackgroundOther);

        table.addCell(others);

        PdfPTable itemTable = new PdfPTable(new float[]{50, 230, 70, 70, 80});

        Paragraph itemIdHeading = new Paragraph("Item ID", headingFont);
        PdfPCell itemIdHeadingCell = new PdfPCell(itemIdHeading);
        itemIdHeadingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        itemIdHeadingCell.setBackgroundColor(BaseColor.ORANGE);
        itemTable.addCell(itemIdHeadingCell);

        Paragraph descriptionHeading = new Paragraph("Description", headingFont);
        PdfPCell descriptionHeadingCell = new PdfPCell(descriptionHeading);
        descriptionHeadingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        descriptionHeadingCell.setBackgroundColor(BaseColor.ORANGE);
        itemTable.addCell(descriptionHeadingCell);

        Paragraph quanityHeading = new Paragraph("Quantity", headingFont);
        PdfPCell quantityHeadingCell = new PdfPCell(quanityHeading);
        quantityHeadingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        quantityHeadingCell.setBackgroundColor(BaseColor.ORANGE);
        itemTable.addCell(quantityHeadingCell);

        Paragraph rateHeading = new Paragraph("Rate(Rs.)", headingFont);
        PdfPCell rateHeadingCell = new PdfPCell(rateHeading);
        rateHeadingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        rateHeadingCell.setBackgroundColor(BaseColor.ORANGE);
        itemTable.addCell(rateHeadingCell);

        Paragraph amountHeading = new Paragraph("Amount(Rs.)", headingFont);
        PdfPCell amountHeadingCell = new PdfPCell(amountHeading);
        amountHeadingCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        amountHeadingCell.setBackgroundColor(BaseColor.ORANGE);
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

        Paragraph total = new Paragraph("empty");
        PdfPCell to = new PdfPCell(total);
        to.setColspan(3);
        to.setRowspan(4);
        //to.setBackgroundColor(BaseColor.YELLOW);
        CellBackground cellBackgroundOther2 = new CellBackground(4, 0, 0, 4);
        to.setBorder(PdfPCell.NO_BORDER);
        to.setCellEvent(cellBackgroundOther2);
        to.setBorderColor(BaseColor.RED);

        table.addCell(to);

        Font totalFont = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);

        Paragraph subTotal = new Paragraph("Sub Total", headingFont);
        PdfPCell subTotalCell = new PdfPCell(subTotal);
        subTotalCell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
        table.addCell(subTotalCell);

        Paragraph subTotalVal = new Paragraph(PdfUtils.formatDouble(invoice.getSubtotal()), valueFont);
        PdfPCell subTotalValCell = new PdfPCell(subTotalVal);
        subTotalValCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        subTotalValCell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
        table.addCell(subTotalValCell);

        Paragraph discount = new Paragraph("Discount", headingFont);
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

        Paragraph netTotal = new Paragraph("Net total", totalFont);
        PdfPCell netTotalCell = new PdfPCell(netTotal);
        netTotalCell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
        table.addCell(netTotalCell);

        Paragraph netTotalVal = new Paragraph(PdfUtils.formatDouble(invoice.getNetTotal()), valueFont);

        PdfPCell netTotalValCell = new PdfPCell(netTotalVal);
        netTotalValCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        netTotalValCell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
        table.addCell(netTotalValCell);

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
