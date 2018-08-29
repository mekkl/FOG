/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.FogException;
import FunctionLayer.LoginException;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Orchi
 */
public abstract class Command {

    private static HashMap<String, Command> commands;

    private static void initCommands() {
        commands = new HashMap<>();

        commands.put( "register", new Register() );
        commands.put( "QuickBuild", new QuickBuild());
        commands.put( "calculate", new Calculate());
        commands.put( "sendinquiry", new SendInquiry());
        commands.put( "viewinquiries", new ViewInquiries() );
        commands.put( "viewcustomers" , new ViewCustomers() );
        commands.put( "viewinquiry" , new ViewInquiry() );
        commands.put( "updateinquiry" , new UpdateInquiry() );
        commands.put( "pdf" , new PDF() );

        commands.put("QuickBuild", new QuickBuild());
        commands.put("login", new Login());
        commands.put("register", new Register());
        commands.put("calculate", new Calculate());
        commands.put("sendInquiry", new SendInquiry());
        commands.put("viewinquiries", new ViewInquiries());

        commands.put("viewcustomers", new ViewCustomers());

        commands.put("saveInquiry", new SaveInquiry());
        commands.put("newInquiry", new NewInquiry());
        commands.put("chooseInquiry", new ChooseInquiry());
        
        commands.put("loginemployee", new LoginEmployee());
        commands.put("passtoemplogin", new PassToEmpLogin());

    }

    static Command from(HttpServletRequest request) {
        String commandName = request.getParameter("command");
        if (commands == null) {
            initCommands();
        }

        return commands.getOrDefault(commandName, new UnknownCommand());
    }

    abstract String execute(HttpServletRequest request, HttpServletResponse response) throws FogException, LoginException; 
}
