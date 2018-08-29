/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import DataLayer.CustomerMapper;
import DataLayer.EmployeeMapper;
import DataLayer.ProductMapper;
import DataLayer.InquiryMapper;
import PresentationLayer.JspUtilTable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.mail.MultiPartEmail;

/**
 * The purpose of the LogicFacade class is to facilitate the "facade layer" between the FunctionLayer and the DataLayer. 
 * @author 
 */
public class LogicFacade {

    //USER 
    public static Customer login(String email, String password, String ipAddress) throws LoginException, FogException {
        return CustomerMapper.login(email, password, ipAddress);
    }

    public static Customer createCostumer(Customer c) throws FogException {
        return CustomerMapper.createCustomer(c);
    }

    public static List<Product> getProducts() throws FogException {
        return ProductMapper.getProducts();
    }

    public static BillOfMaterials calculateBillofMaterials(Inquiry inquiry) throws FogException {
        return Calculator.getBillOfMaterials(inquiry);
    }

    public static void SendInquiry(Inquiry inquiry) throws FogException {
            InquiryMapper.registerInitialInquiry(inquiry);
    }
    
    public static void sendSavedInquiry(Inquiry inquiry) throws FogException {
            InquiryMapper.sendSavedInquiry(inquiry);
    }

    public static List<Inquiry> viewInquiries() throws FogException {
        return InquiryMapper.allInquiries();
    }

    public static List[] viewCustomersAndInquiries() throws FogException {
        List[] list = new List[2];

        list[0] = InquiryMapper.allInquiries();
        list[1] = CustomerMapper.customersWithInquiry();

        return list;
    }

    public static Inquiry viewInquiry(int id) throws FogException {
        return InquiryMapper.inquiryById(id);
    }

    public static Inquiry viewLatestInquiryByEmail(String customerEmail) throws FogException {
        return InquiryMapper.latestInquiryByCustomer(customerEmail);
    }

    public static List<Customer> viewAllCustomers() throws FogException {
        return CustomerMapper.allCustomers();
    }

    public static Customer viewCustomerByEmail(String email) throws FogException {
        return CustomerMapper.customerByEmail(email);
    }

    public static List<Customer> viewCustomersByInquiryStatus(String status) throws FogException {
        switch (status) {
            case "ny":
            case "behandles":
            case "behandlet":
                return CustomerMapper.customersByInquiryStatus(status);
            default:
                throw new FogException(" Ukendt status på forespørsel ");
        }
    }

    public static List<Inquiry> getCustomerInquiries(Customer customer) throws FogException {
        return InquiryMapper.getCustomerInquiries(customer);
    }

    public static Inquiry updateInquiry(int id, int height, int length, int width,
            int shackLength, int shackWidth, String roofType,
            String roofMat, String angle, String comment, String status)
            throws FogException {
        return InquiryMapper.updateInquiry(id, height, length, width, shackLength, shackWidth, roofType, roofMat, angle, comment, status);
    }

    public static String getRoofMaterials(String roofType) throws FogException {
        return JspUtilTable.utilDropDownFlat(ProductMapper.getProducts(), roofType);
    }

    public static List<Product> getPitchedRoofProducts() throws FogException {
        List<Product> products = ProductMapper.getProducts();
        List<Product> res = new ArrayList<>();

        for (Product pro : products) {
            if (pro.getCategory().equals("tagsten") || pro.getCategory().equals("tagpap")) {
                res.add(pro);
            }
        }
        return res;
    }

    public static List<Product> getFlatRoofProducts() throws FogException {
        List<Product> products = ProductMapper.getProducts();
        List<Product> res = new ArrayList<>();

        for (Product pro : products) {
            if (pro.getCategory().equals("trapeztag") || pro.getCategory().equals("tagpap")) {
                res.add(pro);
            }
        }
        return res;
    }

    public static String utilPreviousInquiries(List<Inquiry> inquiriesList) {
        return JspUtilTable.utilPreviousInquiries(inquiriesList);
    }

    public static Inquiry getChosenInquiry(List<Inquiry> inquiries, int inquiryId) {
        for (Inquiry i : inquiries) {
            if (i.getId() == inquiryId) {
                return i;
            }
        }
        return null;
    }

    public static Employee login(int id, String pwd, String ipAddress) throws LoginException, FogException {
        return EmployeeMapper.login(id, pwd, ipAddress);
    }

    public static MultiPartEmail generatePDF(Customer customer, Inquiry inquiry, BillOfMaterials bom) throws FogException {
        return GeneratePDF.createPDF(customer, inquiry, bom);
    }

    public static StringBuilder getSVGFromSide(Inquiry inquiry) {
        return new SVGFromSide(inquiry).getSVG();
    }

    public static StringBuilder getSVGFromTop(Inquiry inquiry) {
        return new SVGFromTop(inquiry).getSVG();
    }
}
