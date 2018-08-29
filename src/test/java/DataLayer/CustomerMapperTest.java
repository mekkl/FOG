/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import FunctionLayer.Customer;
import FunctionLayer.FogException;
import FunctionLayer.LogicFacade;
import FunctionLayer.LoginException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Stanislav
 */
public class CustomerMapperTest {

    private static Connection testConnection;
    private static final String USER = "test";
    private static final String USERPW = "Password1";
    private static final String DBNAME = "fogTest";
    private static final String HOST = "207.154.222.88";

    @Before
    public void setUp() {
        //        INSERT INTO 
        //CustomerTest (email, name, surname, phonenumber, address, zipcode, password)
        //VALUES 
        //('test1@test.dk','Hans','Hansen',11223344,'Torskevej 1',2750, 'Hansen1'),
        //('test2@test.dk','Tom','Tomsen',44332211,'Sildevej 2',2000, 'Tomsen1'),
        //('test3@test.dk','Hanne','Hannesen',11112233,'Makrelvej 3',2800, 'Hannesen'),
        //('test4@test.dk','Pernille','Pernillesen',44443322,'Spættevej 4',2800, 'Pernillesen'),
        //('test5@test.dk','Dorthe','Larsen',44533322,'Totalvej 2',2800, 'Dorthel'),
        //('test8@test.dk','Anders','Andersen',42343322,'Absvej 2',2730, '333aaa'),
        //('test6@test.dk','Oliver','Håkonsson',24566333,'Taskestrupvej 42',2000, 'gggg');

        try {
            // avoid making a new connection for each test
            if (testConnection == null || testConnection.isClosed()) {
                String url = String.format("jdbc:mysql://%s:3306/%s", HOST, DBNAME);
                Class.forName("com.mysql.jdbc.Driver");

                testConnection = DriverManager.getConnection(url, USER, USERPW);
                // Make mappers use test 
                DBConnector.setConn(testConnection);
            }
            // reset test database
            try (Statement stmt = testConnection.createStatement()) {
                stmt.execute("drop table if exists Customer");
                stmt.execute("create table Customer like CustomerTest");
                stmt.execute("insert into Customer select * from CustomerTest");
            }

        } catch (ClassNotFoundException | SQLException ex) {
            testConnection = null;
            System.out.println("Could not open connection to database: " + ex.getMessage());
        }
    }

    @Test
    public void testSetUpOK() {
        // Just check that we have a connection.
        assertNotNull(testConnection);
    }

    @Test
    public void testLogin() throws FogException, LoginException {
        String email = "test1@test.dk";
        String password = "Hansen1";
        Customer customer = CustomerMapper.login(email, password, "1111");
        assertTrue(email.equals(customer.getEmail()));
        assertTrue(password.equals(customer.getPassword()));
    }

    @Test(expected = LoginException.class)
    public void testLoginException() throws FogException, LoginException {
        String email = "harrypotter@hogwarts.com";
        String password = "sectumsembra";
        Customer customer = LogicFacade.login(email, password, "11111");
    }

    @Test
    public void testGetCity() throws FogException {
        System.out.println("getCity");
        int zipcode = 9999;
        String expResult = "Gotham";
        String result = CustomerMapper.getCity(zipcode);
        assertEquals(expResult, result);
    }

    //@Test
    public void testCreateCustomer() throws FogException {
        System.out.println("createCustomer");
        Customer c = new Customer("batman@robin.dk", "Bruce", "Wayne", 44332211, "Gothamvej 1", 9999, "Catwoman1", "Gotham");
        String expResult = "Wayne";
        Customer result = CustomerMapper.createCustomer(c);
        assertEquals(expResult, result.getSurname());
    }

    @Test
    public void testAllCustomers() throws FogException {
        System.out.println("allCustomers");
        List<Customer> result = CustomerMapper.allCustomers();
        assertNotNull(result);
        assertTrue(result.size() > 2);
    }

    @Test
    public void testCustomerByEmail() throws FogException {
        System.out.println("customerByEmail");
        String email = "test1@test.dk";
        Customer result = CustomerMapper.customerByEmail(email);
        assertNotNull(result);
        assertEquals("Hansen", result.getSurname());
    }

}
