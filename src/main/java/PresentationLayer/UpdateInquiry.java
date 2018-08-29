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
 * Get Customer, Inquiry info from request.
 * Calculate BillOfMaterials, FlatMat and PitchedMat for Customer and set them on session.
 * @author ML
 */
public class UpdateInquiry extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws FogException {
        String customer = request.getParameter("customer");
        int id = Integer.parseInt(request.getParameter("id"));
        
        int height = Integer.parseInt(request.getParameter("height"));
        int length = Integer.parseInt(request.getParameter("length"));
        int width = Integer.parseInt(request.getParameter("width"));
        int shackLength = 0, shackWidth = 0;
        if(request.getParameter("withShack").equals("ja")) {
            shackLength = Integer.parseInt(request.getParameter("shackLength"));
            shackWidth = Integer.parseInt(request.getParameter("shackWidth"));
        }
        String angle = null;
        String roofType = request.getParameter("roofType");
        String roofMat;
        if(roofType.equals("rejsning")){
            angle = request.getParameter("angle");
            roofMat = request.getParameter("pitchedMat");
        }else {
            roofMat = request.getParameter("flatMat");
        }
        String comment = request.getParameter("comment");
        String status = request.getParameter("status");
        
        Inquiry inquiry = LogicFacade.updateInquiry(id, height, length, width, shackLength, shackWidth, roofType, roofMat, angle, comment, status);
        BillOfMaterials bom = Calculator.getBillOfMaterials(inquiry);
        
        // roof mats -----
        List<Product> flatMat, pitchedMat;
        flatMat = LogicFacade.getFlatRoofProducts();
        pitchedMat = LogicFacade.getPitchedRoofProducts();
        request.setAttribute("pitchedMat", pitchedMat);
        request.setAttribute("flatMat", flatMat);
        // ---------------
        
        request.setAttribute("customer", LogicFacade.viewCustomerByEmail(customer));
        request.setAttribute("bom", bom);
        request.setAttribute("inquiry", inquiry);
        
        return "inquiry";
    }
    
}
