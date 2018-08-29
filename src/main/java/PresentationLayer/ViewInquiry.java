/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.BillOfMaterials;
import FunctionLayer.Calculator;
import FunctionLayer.FogException;
import FunctionLayer.Inquiry;
import FunctionLayer.LogicFacade;
import FunctionLayer.Product;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Get Customer from request, and use it to get latest inquiry from it. Then calculate BillOFMaterials from it. 
 * Calculate and et set list of PitchedMats and FlatMats, Customer, BillOfMaterials and Inquiry on request atrtributes. 
 * @author Mellem
 */
public class ViewInquiry extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws FogException {

        String customer = request.getParameter("customer");
        Inquiry i = LogicFacade.viewLatestInquiryByEmail(customer);
        Inquiry inquiry = LogicFacade.viewInquiry(i.getId());
        BillOfMaterials bom = Calculator.getBillOfMaterials(inquiry);
        List<Product> flatMat, pitchedMat;

        flatMat = LogicFacade.getFlatRoofProducts();
        pitchedMat = LogicFacade.getPitchedRoofProducts();
        
        request.setAttribute("pitchedMat", pitchedMat);
        request.setAttribute("flatMat", flatMat);
        request.setAttribute("customer", LogicFacade.viewCustomerByEmail(customer));
        request.setAttribute("bom", bom);
        request.setAttribute("inquiry", i);
        
        return "inquiry";
    }
    
}
