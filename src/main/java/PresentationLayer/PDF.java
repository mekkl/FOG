/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.BillOfMaterials;
import FunctionLayer.Calculator;
import FunctionLayer.Customer;
import FunctionLayer.FogException;
import FunctionLayer.Inquiry;
import FunctionLayer.LogicFacade;
import FunctionLayer.Product;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alexander W. Hørsted-Andersen 
 */
public class PDF extends Command {

    /** Gets the customer, the inquiry and BillOfMaterials - and generates a PDF from them which is set on the request
    *
    * @author Orchi
    */
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws FogException{

        String customerEmail = request.getParameter("customer");
        Customer cus = LogicFacade.viewCustomerByEmail(customerEmail);
        int id = Integer.parseInt(request.getParameter("id"));
        Inquiry inquiry = LogicFacade.viewInquiry(id);
        BillOfMaterials bom = Calculator.getBillOfMaterials(inquiry);
        List<Product> flatMat, pitchedMat;

        flatMat = LogicFacade.getFlatRoofProducts();
        pitchedMat = LogicFacade.getPitchedRoofProducts();

        request.setAttribute("generatedPDF", LogicFacade.generatePDF(cus, inquiry, bom)); 
        request.setAttribute("pitchedMat", pitchedMat);
        request.setAttribute("flatMat", flatMat);
        request.setAttribute("customer", cus);
        request.setAttribute("bom", bom);
        request.setAttribute("inquiry", inquiry);
        request.setAttribute("id", id);
        

        return "inquiry";
    }

}
