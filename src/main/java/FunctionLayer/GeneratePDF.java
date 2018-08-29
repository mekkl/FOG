/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.activation.DataSource;
import org.apache.commons.mail.*;

/**
 *
 * @author awha8
 */
public class GeneratePDF {

    final static String PATH = "/var/log/tomcat8/itextExamples/";
    final static String DESTPDF = PATH + "Tilbud på carport.pdf";
    final static String TOPHTML = "Top.html";
    final static String SIDEHTML = "Side.html";

    /**
     * Generates a PDF file, that contains customer, inquiry and bill of
     * materials details. The PDf file is then emailed to the customers email
     * adress.
     *
     * @param customer Customer Object
     * @param inquiry Inquiry Object
     * @param bom BillOfMaterials Object
     * @return MultiPartEmail Object.
     * @throws FogException
     */
    public static MultiPartEmail createPDF(Customer customer, Inquiry inquiry, BillOfMaterials bom) throws FogException {

        //create empty directory for file output
        new File(PATH).mkdirs();

        // Creating a PdfDocument object   
        PdfWriter writer;
        try {
            writer = new PdfWriter(DESTPDF);
        } catch (FileNotFoundException ex) {
            throw new FogException(ex.getMessage());
        }

        // Creating a PdfDocument object      
        PdfDocument pdf = new PdfDocument(writer);

        // Creating a Document object       
        Document doc = new Document(pdf);

        float[] pointColumnWidthsTitle = {100F};  //1 wide
        Table title_table = new Table(pointColumnWidthsTitle);
        title_table.addCell(new Paragraph("Tilbud på carport"));
        doc.add(title_table.setBold());
        doc.add(new Paragraph("\n"));

        /////////////////////
        //Creating customer table
        /////////////////////
        float[] pointColumnWidthsCustomer = {50F, 50F};  //2 wide
        Table table_cus = new Table(pointColumnWidthsCustomer);
        table_cus.addCell(new Cell().add("Kunde info").setBold());
        table_cus.addCell(new Cell().add(""));
        table_cus.addCell(new Cell().add("Email"));
        table_cus.addCell(new Cell().add(customer.getEmail()));
        table_cus.addCell(new Cell().add("Name"));
        table_cus.addCell(new Cell().add(customer.getName()));
        table_cus.addCell(new Cell().add("Surname"));
        table_cus.addCell(new Cell().add(customer.getSurname()));
        table_cus.addCell(new Cell().add("Phonenumber"));
        table_cus.addCell(new Cell().add(customer.getPhonenumber() + ""));
        table_cus.addCell(new Cell().add("Address"));
        table_cus.addCell(new Cell().add(customer.getAddress()));
        table_cus.addCell(new Cell().add("Zipcode"));
        table_cus.addCell(new Cell().add(customer.getZipcode() + ""));
        doc.add(table_cus);
        doc.add(new Paragraph("\n"));

        ////////////////////////////
        // Creating inquiry info //
        //////////////////////////
        float[] pointColumnWidthsInquiry = {50F, 50F};  //2 wide
        Table table_inquiry = new Table(pointColumnWidthsInquiry);
        table_inquiry.addCell(new Cell().add("Forespørgsel").setBold());
        table_inquiry.addCell(new Cell().add(""));
        table_inquiry.addCell(new Cell().add("Carport højde"));
        table_inquiry.addCell(new Cell().add(inquiry.getCarportHeight() + ""));
        table_inquiry.addCell(new Cell().add("Carport længde"));
        table_inquiry.addCell(new Cell().add(inquiry.getCarportLength() + ""));
        table_inquiry.addCell(new Cell().add("Carport bredde"));
        table_inquiry.addCell(new Cell().add(inquiry.getCarportWidth() + ""));
        table_inquiry.addCell(new Cell().add("Skur længde"));
        if (inquiry.getShackLength() > 0) {
            table_inquiry.addCell(new Cell().add(inquiry.getShackLength() + ""));
        } else {
            table_inquiry.addCell(new Cell().add("-"));
        }

        table_inquiry.addCell(new Cell().add("Skur bredde"));
        if (inquiry.getShackWidth() > 0) {
            table_inquiry.addCell(new Cell().add(inquiry.getShackWidth() + ""));
        } else {
            table_inquiry.addCell(new Cell().add("-"));
        }

        table_inquiry.addCell(new Cell().add("Tagtype"));
        table_inquiry.addCell(new Cell().add(inquiry.getRoofType()));

        table_inquiry.addCell(new Cell().add("Taghældning"));
        if (inquiry.getAngle() != null) {
            table_inquiry.addCell(new Cell().add(inquiry.getAngle()));
        } else {
            table_inquiry.addCell(new Cell().add("-"));
        }

        table_inquiry.addCell(new Cell().add("Kommentar ansat"));
        if (inquiry.getCommentEmployee() != null) {
            table_inquiry.addCell(new Cell().add(inquiry.getCommentEmployee()));
        } else {
            table_inquiry.addCell(new Cell().add("-"));
        }

        table_inquiry.addCell(new Cell().add("Kommentar kunde"));
        if (inquiry.getCommentEmployee() != null) {
            table_inquiry.addCell(new Cell().add(inquiry.getCommentCustomer()));
        } else {
            table_inquiry.addCell(new Cell().add("-"));
        }

        table_inquiry.addCell(new Cell().add("Ønsket levering til"));
        if (inquiry.getPeriod() != null) {
            table_inquiry.addCell(new Cell().add(inquiry.getPeriod() + ""));
        } else {
            table_inquiry.addCell(new Cell().add("-"));
        }

        table_inquiry.addCell(new Cell().add("Status"));
        table_inquiry.addCell(new Cell().add(inquiry.getStatus()));

        table_inquiry.addCell(new Cell().add("Behandlet af"));
        if (inquiry.getId_employee() > 0) {
            table_inquiry.addCell(new Cell().add(inquiry.getId_employee() + ""));
        } else {
            table_inquiry.addCell(new Cell().add("-"));
        }

        table_inquiry.addCell(new Cell().add("Forespørgsel afsendt den"));
        if (inquiry.getDate() + "" != null) {
            table_inquiry.addCell(new Cell().add(inquiry.getDate() + ""));
        } else {
            table_inquiry.addCell(new Cell().add("intet valgt"));
        }

        doc.add(table_inquiry);
        doc.add(new Paragraph("\n"));

        /////////////////////
        // Creating bom table       
        /////////////////////
        float[] pointColumnWidthsBom = {50F, 50F, 50F, 50F, 50F};  //5 wide
        Table table_bom = new Table(pointColumnWidthsBom);

        // Adding cells to the table   
        table_bom.addCell(new Cell().add("Stykliste").setBold());
        table_bom.addCell(new Cell());
        table_bom.addCell(new Cell());
        table_bom.addCell(new Cell());
        table_bom.addCell(new Cell());
        table_bom.addCell(new Cell().add("Product"));
        table_bom.addCell(new Cell().add("Category"));
        table_bom.addCell(new Cell().add("Qty"));
        table_bom.addCell(new Cell().add("Unit"));
        table_bom.addCell(new Cell().add("Usability Comment"));

        for (OrderLine j : bom.getMaterials()) {
            table_bom.addCell(new Cell().add(j.getProductName()));
            table_bom.addCell(new Cell().add(j.getProductCategory()));
            table_bom.addCell(new Cell().add(j.getQuantity() + ""));
            table_bom.addCell(new Cell().add(j.getAmountType()));
            table_bom.addCell(new Cell().add(j.getUsabilityComment()));
        }

        doc.add(table_bom);
        doc.close();
        byte[] pdfAttachment;
        pdfAttachment = loadFile(DESTPDF);

        return sendEmail(customer, pdfAttachment);
    }

    /**
     * Loads file from given path on harddrive as bytearray
     *
     * @param sourcePath String
     * @return InputStream
     */
    private static byte[] loadFile(String sourcePath) throws FogException {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(sourcePath);
            return readFully(inputStream);
        } catch (FileNotFoundException ex) {
            throw new FogException(ex.getMessage());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ex) {
                    throw new FogException(ex.getMessage());
                }
            }
        }
    }

    /**
     * Return bytearryoutputstream from inputstream
     *
     * @param stream
     */
    private static byte[] readFully(InputStream stream) throws FogException {
        byte[] buffer = new byte[8192];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        int bytesRead;
        try {
            while ((bytesRead = stream.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
        } catch (IOException ex) {
            throw new FogException(ex.getMessage());
        }
        return baos.toByteArray();
    }

    /**
     * Send the created PDF as email
     *
     * @param PDF
     * @return
     * @throws EmailException
     */
    private static MultiPartEmail sendEmail(Customer customer, byte[] PDF) throws FogException {

        // create the mail
        MultiPartEmail email = new MultiPartEmail();

        email.setHostName("smtp.googlemail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("fakejohannesfog", "johannesfogpassword1"));
        email.setSSL(true);
        try {
            email.addTo(customer.getEmail(), "Fake Johannes Fog");
            email.setFrom("fakejohannesfog@gmail.com", "Fake Johannes Fog");
            email.setSubject("Ordre fra Fake Johannes Fog");
            email.setMsg("Besked om din ordre her.");

            //attach pdf 
            DataSource source = new ByteArrayDataSource(PDF, "application/pdf");
            email.attach(source, "Tilpud på carport", "Tilpud på carport");
            source.getInputStream().close();
            email.send();
        } catch (EmailException | IOException ex) {
            throw new FogException(ex.getMessage());
        }
        return email;
    }
}
