/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.FogException;
import FunctionLayer.Inquiry;
import FunctionLayer.LogicFacade;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Get list of all inquiries. Get email, from request and generate table of inquiries corresponding to to the email.
 * Set inquiry table on request attribute. 
 * @author ML
 */
public class ViewInquiries extends Command
{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws FogException {
        List<Inquiry> i = LogicFacade.viewInquiries();
        String email = request.getParameter("email");
        String tableTagId = "inquirytabel";
        String inquiryTable = JspUtilTable.tableInquiry(tableTagId, i);
        
        if (email != null) request.setAttribute("email", email);
        else request.setAttribute("email", "");
        
        request.setAttribute("inquirytable", inquiryTable);
        
        return "inquiries";
    }
    
}
