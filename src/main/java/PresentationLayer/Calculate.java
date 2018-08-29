/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.BillOfMaterials;
import FunctionLayer.FogException;
import FunctionLayer.Inquiry;
import FunctionLayer.LogicFacade;
import java.sql.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * @author Stanislav
 */
public class Calculate extends Command {

    /**
     * Gets all the mesurements from request, then creates an inquiry with all of the info,
     * then calculates a bill of materials from the inquiry as a parameter, and then sets it on the 
     * session object, also adding shackCheckbox to session witch is used to decide wether the checkbox is checked
     * on reentry to the site, this also applies to wishedDelivery.
     * There is also added SVG to the session, that is created with the inquiry as a parameter.
     * @param request
     * @param response
     * @return String "QuickBuild" used by Frontcontroller.
     * @throws FogException
     */
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws FogException{
        HttpSession session = request.getSession(false);
        int height = Integer.parseInt(request.getParameter("height"));
        int length = Integer.parseInt(request.getParameter("length"));
        int width = Integer.parseInt(request.getParameter("width"));
        String roofType = request.getParameter("roofType");
        String angle = request.getParameter("angle");
        String comment = request.getParameter("comment");
        
        //Gets the chosen material for the chosen rooftype
        String roofMaterial = "";
        if (roofType.equals("rejsning") && request.getParameter("roofMaterialPitchedProduct") != null) {
            roofMaterial = request.getParameter("roofMaterialPitchedProduct");
        }
        if (roofType.equals("fladt") && request.getParameter("roofMaterialFlatProduct") != null) {
            roofMaterial = request.getParameter("roofMaterialFlatProduct");
        }

        String wishedDelivery = request.getParameter("wishedDelivery");
        Date wishDeliveryDate = null;
        if (wishedDelivery != null && wishedDelivery.length() > 1) {
            wishDeliveryDate = new Date(Integer.parseInt(wishedDelivery.substring(0, 4)) - 1900, Integer.parseInt(wishedDelivery.substring(5, 7)) - 1, Integer.parseInt(wishedDelivery.substring(8)));
        }
        String shackCheckbox = null;
        int shackLength = 0;
        int shackWidth = 0;
        shackCheckbox = request.getParameter("shackCheckbox");
        boolean withShack = false;
        // checks if the checkbox for added shack is ticked, if true it adds shack mesurements
        if (shackCheckbox != null && request.getParameter("shackCheckbox").equals("on")) {
            withShack = true;
            shackLength = Integer.parseInt(request.getParameter("shackLength"));
            shackWidth = Integer.parseInt(request.getParameter("shackWidth"));
        }

        Inquiry inquiry = new Inquiry(0, height, length, width, shackLength, shackWidth, roofType, roofMaterial, angle, comment, null, wishDeliveryDate, "", null, 1, null);
        inquiry.setWithShack(withShack);
        
        //Calculates the Bill of materials for the chosen lengths
        BillOfMaterials bom = LogicFacade.calculateBillofMaterials(inquiry);
        inquiry.setBom(bom);

        //Get the SVG for the chosen lengths, from top and from side
        StringBuilder top = LogicFacade.getSVGFromSide(inquiry);
        StringBuilder side = LogicFacade.getSVGFromTop(inquiry);

        session.setAttribute("svgTop", top.toString());
        session.setAttribute("svgSide", side.toString());
        session.setAttribute("shackCheckbox", shackCheckbox);
        session.setAttribute("wishedDelivery", wishedDelivery);
        session.setAttribute("inquiry", inquiry);
        return "QuickBuild";
    }
}
