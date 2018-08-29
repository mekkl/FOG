/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.FogException;
import FunctionLayer.LogicFacade;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Orchi
 */
public class QuickBuild extends Command {

    public QuickBuild() {
    }

    /** sends you to the QuickBuild site from the index page. Retrieving some materials from database, which is set on the session.
    *
    * @author Orchi
    */
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws FogException {
        HttpSession session = request.getSession();

        String roofMaterialPitched = LogicFacade.getRoofMaterials("rejsning");
        String roofMaterialFlat = LogicFacade.getRoofMaterials("fladt");

        session.setAttribute("roofMaterialPitched", roofMaterialPitched);
        session.setAttribute("roofMaterialFlat", roofMaterialFlat);

        return "QuickBuild";
    }
}
