/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.Employee;
import FunctionLayer.FogException;
import FunctionLayer.Inquiry;
import FunctionLayer.LogicFacade;
import FunctionLayer.LoginException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ML
 */
public class LoginEmployee extends Command {
    
    /** If login is succesful the employee is set on the session aswell as all the inquiries, else an exception is thrown
    * returns "inquiries"
    * @author Orchi
    */
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws FogException, LoginException {
        int id;
             
        if (request.getParameter("id").equals("")) id = 0;
        else id = Integer.parseInt(request.getParameter("id"));
        String pwd = request.getParameter("pwd");
        String ipAddress = request.getRemoteAddr();

        Employee emp = LogicFacade.login(id, pwd, ipAddress);
        request.getSession().setAttribute("employee", emp);

        List<Inquiry> i = LogicFacade.viewInquiries();
        String email = request.getParameter("email");
        String tableTagId = "inquirytabel";
        String inquiryTable = JspUtilTable.tableInquiry(tableTagId, i);
        
        if (email != null) request.setAttribute("email", email);
        else request.setAttribute("email", "");
        request.setAttribute("tableTagId", tableTagId);
        request.setAttribute("inquirytable", inquiryTable);

        return "inquiries";

        
        
    }
    
}
