/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.Customer;
import FunctionLayer.FogException;
import FunctionLayer.Inquiry;
import FunctionLayer.Product;
import java.util.List;

/**
 *
 * @author Mellem
 */
public class JspUtilTable {
    /**
     * Creates a html String table from inquiryList
     * @param tableTagId
     * @param inquiryList
     * @return String html table part
     * @throws FogException 
     */
    public static String tableInquiry(String tableTagId, List<Inquiry> inquiryList) throws FogException {
        StringBuilder sb = new StringBuilder();
        
        sb.append("<table class=\"table table-bordered\" id=\"").append(tableTagId).append("\">");
        sb.append(completeTHead(tableTagId,"inquiry"));
        
        for (Inquiry inquiry : inquiryList) {
            sb.append(row(inquiry));
        }

        sb.append("</table>");
        return sb.toString();
    }
    /**
     * Creates html table from customerList
     * @param tableTagId
     * @param customerList
     * @return String html table part
     * @throws FogException 
     */
    public static String tableCustomer(String tableTagId, List<Customer> customerList) throws FogException {
        StringBuilder sb = new StringBuilder();
        
        sb.append("<table class=\"table table-bordered\" id=\"").append(tableTagId).append("\">");
        sb.append(completeTHead(tableTagId,"customer"));
        
        for (Customer customer : customerList) {
            sb.append(row(customer));
        }

        sb.append("</table>");
        return sb.toString();
    }
    
    /**
     * TableHead switch that adds Table head to html table string
     * @param tableTagId
     * @param objType
     * @return String html table part
     * @throws FogException 
     */
    private static String completeTHead(String tableTagId,String objType) throws FogException{
        StringBuilder sb = new StringBuilder();
        
        switch(objType){
            case "inquiry":
            case "Inquiry":
                sb.append("<thead>").append("<tr>");
                sb.append(th(tableTagId,"ID",0));
                sb.append(th(tableTagId,"Status",1));
                sb.append(th(tableTagId,"Customer Email",2));
                sb.append(th(tableTagId,"Assigned Employee",3));
                sb.append(th(tableTagId,"Inquiry sent",4));
                sb.append(th(tableTagId,"Wished delivery",5));
                sb.append(th(tableTagId,"Customer comment",6));
                sb.append(th(tableTagId,"Employee comment",7));
                sb.append("</tr>").append("</thead>");
                break;
            case "customer":
            case "Customer":
                sb.append("<thead>").append("<tr>");
                sb.append(th(tableTagId,"Email",0));
                sb.append(th(tableTagId,"Name",1));
                sb.append(th(tableTagId,"Surname",2));
                sb.append(th(tableTagId,"Phonenumber",3));
                sb.append(th(tableTagId,"Address",4));
                sb.append(th(tableTagId,"Zipcode",5));
                sb.append(th(tableTagId,"City",6));
                sb.append("</tr>").append("</thead>");
                break;
            default:
                throw new FogException(" No object with specified type: "+objType);
                
        }
        return sb.toString();
    } 
    
    /**
     * Creates the rows for html table string for inquiry
     * @param inquiry
     * @return hmtl table part
     */
    private static String row(Inquiry inquiry){
        StringBuilder sb = new StringBuilder();
        
        sb.append("<tr class=\"clickablerow\" onclick=\"findInquiryByEmailAndId(\'").append(inquiry.getEmail()).append("\',\'").append(inquiry.getId()).append("\')\">");
        sb.append("<td>").append(inquiry.getId()).append("</td>");
        sb.append("<td>").append(inquiry.getStatus()).append("</td>");
        sb.append("<td>").append(inquiry.getEmail()).append("</td>");
        sb.append("<td>").append(inquiry.getId_employee()).append("</td>");
        sb.append("<td>").append(inquiry.getDate()).append("</td>");
        sb.append("<td>").append(inquiry.getPeriod()).append("</td>");
        sb.append("<td>").append(inquiry.getCommentCustomer()).append("</td>");
        sb.append("<td>").append(inquiry.getCommentEmployee()).append("</td>");
        sb.append("</tr>");
        
        return sb.toString();
    }

    /**
     * Creates the rows for html table string for customer
     * @param customer
     * @return html table part
     */
    private static String row(Customer customer){
        StringBuilder sb = new StringBuilder();
        
        sb.append("<tr class=\"clickablerow\" onclick=\"findInquiriesByEmail(\'").append(customer.getEmail()).append("\')\">");
        sb.append("<td>").append(customer.getEmail()).append("</td>");
        sb.append("<td>").append(customer.getName()).append("</td>");
        sb.append("<td>").append(customer.getSurname()).append("</td>");
        sb.append("<td>").append(customer.getPhonenumber()).append("</td>");
        sb.append("<td>").append(customer.getAddress()).append("</td>");
        sb.append("<td>").append(customer.getZipcode()).append("</td>");
        sb.append("<td>").append(customer.getCity()).append("</td>");
        sb.append("</tr>");
        
        return sb.toString();
    }
    
    /**
     * Creates string html table
     * @param tableTagId
     * @param colName
     * @param colIndex
     * @return String html table
     */
    private static String th(String tableTagId, String colName, int colIndex){
        StringBuilder sb = new StringBuilder();
        
        sb.append("<th class=\"sorthead\" onclick=\"sortTable(\'").append(tableTagId).append("\',").append(colIndex).append(")\">");
        sb.append("<b>").append(colName).append("</b> <i class=\"fa fa-fw fa-sort\"></i>");
        sb.append("<input type=\"hidden\" class=\"ascdesc\" value=\"asc\">");
        sb.append("</th>");
        
        return sb.toString();
    }
    
    /**
     * Creates link list from inquiryList as html syntax
     * @param inquiriesList
     * @return String with html syntax link list
     */
    public static String utilPreviousInquiries(List<Inquiry> inquiriesList) {
        StringBuilder res = new StringBuilder();

        inquiriesList.forEach((inquiry) -> {
            res.append("<li class=\"list-group-item btn btn-default\"> <a style=\"display: block\" href=\"FrontController?command=chooseInquiry&inquiryId=")
                    .append(inquiry.getId())
                    .append("\">")
                    .append(inquiry.getId())
                    .append("</a></li> ");
        });

        return res.toString();
    }

    /**
     * Creates html syntax dropdown menu
     * @param products
     * @param roofType
     * @return String with html dropdown table
     */
    public static String utilDropDownFlat(List<Product> products, String roofType) {
        StringBuilder res = new StringBuilder();

        for (Product p : products) {
            if (roofType.equals("rejsning") && (p.getCategory().equals("tagsten") || p.getCategory().equals("tagpap"))) {
                res.append("<option>")
                        .append(p.getName())
                        .append("</option>");
            }
            if (roofType.equals("fladt") && (p.getCategory().equals("trapeztag") || p.getCategory().equals("tagpap"))) {
                res.append("<option>")
                        .append(p.getName())
                        .append("</option>");
            }
        }

        return res.toString();
    }
    
}
