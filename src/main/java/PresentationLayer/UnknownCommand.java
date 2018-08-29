package PresentationLayer;

import FunctionLayer.FogException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * If the system is unable to find a corresponding command from the Command class hashmap, this class is the default response.
 *
 */
public class UnknownCommand extends Command {

    @Override
    String execute( HttpServletRequest request, HttpServletResponse response ) throws FogException {
        String msg = "Unknown command. Contact IT";
        throw new FogException( msg );
    }
}
