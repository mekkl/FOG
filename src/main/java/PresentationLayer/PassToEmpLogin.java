/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.FogException;
import FunctionLayer.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ML
 */
public class PassToEmpLogin extends Command {

    /** Sends you to the employee page
    *
    * @author Orchi
    */
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws FogException, LoginException {
        return "emplogin";
    }
    
}
