/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.Customer;
import FunctionLayer.FogException;
import FunctionLayer.Inquiry;
import FunctionLayer.LogicFacade;
import FunctionLayer.LoginException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Stanislav
 */
public class Login extends Command {

    /** If login is succesful the customer returned is set on the session aswell as the customer's inquiries. Else an exception is thrown
    * returns "QuickBuild" 
    * @author Orchi
    */
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws FogException, LoginException{
        Customer customer = null;
        if (request.getParameter("email") != null) {
            String email = (String) request.getParameter("email");
            String password = (String) request.getParameter("password");
            String ipAddress = request.getRemoteAddr();
            customer = LogicFacade.login(email, password, ipAddress);
            request.getSession().setAttribute("customer", customer);
        }

        List<Inquiry> inquiriesList = LogicFacade.getCustomerInquiries(customer);
        String inquiries = LogicFacade.utilPreviousInquiries(inquiriesList);
        request.getSession().setAttribute("inquiries", inquiries);
        
        return "QuickBuild";
    }

}
