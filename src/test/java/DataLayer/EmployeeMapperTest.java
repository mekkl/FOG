/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import FunctionLayer.Employee;
import FunctionLayer.FogException;
import FunctionLayer.LoginException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Stanislav
 */
public class EmployeeMapperTest {

        //INSERT INTO
        //EmployeeTest(name, surname, password)
        //VALUES('Martin', 'Fogmaster', 'emp1'),
            //('Johannes', 'Fog','emp2'),
            //('Frodo', 'Baggings','emp3');


    @Test
    public void testLogin() throws FogException, LoginException {
        System.out.println("login");
        int id = 1;
        String pwd = "emp1";
        String ipAddress = "123123";
        Employee result = EmployeeMapper.login(id, pwd, ipAddress);
        assertNotNull(result);
        assertEquals("Martin",result.getName());
    }

    @Test(expected = LoginException.class)
    public void testLoginException() throws LoginException, Exception {
        Employee result = EmployeeMapper.login(0, "", "");
    }
}
