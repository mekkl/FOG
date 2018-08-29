/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.Customer;
import FunctionLayer.FogException;
import FunctionLayer.LogicFacade;
import FunctionLayer.Inquiry;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Gets Customer, Inquiry, roofMaterialPitched & roofMaterialFlat from session.
 * Set customer email on inquiry, status as "ny" and update corresponding
 * Inquiry on Database. Removes above info from session. Get list of Inquiries
 * for given Customer. Set on session: Inquiries, roofMaterialPitched,
 * roofMaterialFlat, Lastpage and Customer. Return Quickbuild
 *
 * @author Alexander W. Hørsted-Andersen
 */
public class SendInquiry extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws FogException {
        HttpSession session = request.getSession(false);
        Customer customer = (Customer) session.getAttribute("customer");
        Inquiry inquiry = (Inquiry) session.getAttribute("inquiry");

        inquiry.setEmail(customer.getEmail());
        if (inquiry.getStatus().equals("gemt")) {
            inquiry.setStatus("ny");
            LogicFacade.sendSavedInquiry(inquiry);
        } else {
            inquiry.setStatus("ny");
            LogicFacade.SendInquiry(inquiry);
        }

        List<Inquiry> inquiriesList = LogicFacade.getCustomerInquiries(customer);
        String inquiries = LogicFacade.utilPreviousInquiries(inquiriesList);

        session.removeAttribute("inquiry");
        session.setAttribute("inquiries", inquiries);
        session.setAttribute("customer", customer);

        return "QuickBuild";
    }

}
