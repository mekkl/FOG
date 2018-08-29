/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.BillOfMaterials;
import FunctionLayer.Customer;
import FunctionLayer.FogException;
import FunctionLayer.Inquiry;
import FunctionLayer.LogicFacade;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Stanislav
 */
public class ChooseInquiry extends Command {
    /**
     * Gets all customer inquiries from session, then sets the chosen inquiry via id on the session object.
     * @param request
     * @param response
     * @return
     * @throws FogException
     */
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws FogException {
        HttpSession session = request.getSession(false);
        Customer c = (Customer) session.getAttribute("customer");
       //Get list of all of logged in customer inquiries with the status "gemt"(saved)
        List<Inquiry> inquiries = LogicFacade.getCustomerInquiries(c);
        int inquiryId = Integer.parseInt(request.getParameter("inquiryId"));
        Inquiry inquiry = LogicFacade.getChosenInquiry(inquiries, inquiryId);

        //String commentCustomer, Date period
        if (inquiry.getShackWidth() > 0) {
            session.setAttribute("shackCheckbox", "on");
        }
        if (inquiry.getPeriod() != null) {
            session.setAttribute("wishedDelivery", inquiry.getPeriod().getYear() + "-" + inquiry.getPeriod().getMonth() + "-" + inquiry.getPeriod().getDate());
        }
        BillOfMaterials bom = LogicFacade.calculateBillofMaterials(inquiry);
        inquiry.setBom(bom);
        
        //Get the SVG for the chosen lengths, from top and from side
        StringBuilder top = LogicFacade.getSVGFromSide(inquiry);
        StringBuilder side = LogicFacade.getSVGFromTop(inquiry);

        
        session.setAttribute("inquiry", inquiry);
        request.setAttribute("svgTop", top.toString());
        request.setAttribute("svgSide", side.toString());

        return "QuickBuild";
    }

}
