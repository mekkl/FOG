/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.Customer;
import FunctionLayer.FogException;
import FunctionLayer.LogicFacade;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mellem
 */
public class Register extends Command {

    /** Used to create a customer on the database, and then sets the customer on session
    * returns "QuickBuild"
    * @author Orchi
    */
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws FogException {
        Customer c = null;
        
        String email = request.getParameter( "email" );
        String password1 = request.getParameter( "password1" );
        String password2 = request.getParameter( "password2" );
        String name = request.getParameter( "name" );
        String surname = request.getParameter( "surname" );
        int phonenumber = Integer.parseInt(request.getParameter( "phonenumber" ));
        String address = request.getParameter( "address" );
        int zipcode = Integer.parseInt(request.getParameter( "zipcode" ));
        c = LogicFacade.createCostumer( new Customer(email, name, surname, phonenumber, address, zipcode, password1, null ));
        HttpSession session = request.getSession(false);
        session.setAttribute( "user", c );
        return "QuickBuild";
        








    }
    
}
