/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import FunctionLayer.Customer;
import FunctionLayer.FogException;
import FunctionLayer.Inquiry;
import java.sql.Connection;
import java.sql.Date;
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
public class InquiryMapperTest {

    private static Connection testConnection;
    private static final String USER = "test";
    private static final String USERPW = "Password1";
    private static final String DBNAME = "fogTest";
    private static final String HOST = "207.154.222.88";

    @Before
    public void setUp() {
        //         INSERT INTO  
        //Inquiry (carportHeight,carportLength,carportWidth,shackWidth,shackLength,roofType,roofMaterial,angle,commentCustomer,commentEmployee,period, status, email)
        //VALUES
        //(320,420,320,200,120,'fladt','CEMBRIT OVENLYSPLADE B7 PVC GLASKLAR 1100X610X1MM',null,null,null,null,'gemt','test1@test.dk'),
        //(320,420,320,0,0,'rejsning','ROEDE VINGETAGSTEN GL. DANSK FORBRUG: 14,6 STK/M2','20','send Email',null,'2017-12-20','gemt','test1@test.dk'),
        //(240,470,360,360,220,'fladt','CEMBRIT OVENLYSPLADE B7 PVC GLASKLAR 1100X610X1MM',null,'Kan der vælges andre tag-materialer end det viste?',null,'2017-12-24','ny','test2@test.dk'),
        //(320,420,320,210,150,'rejsning','ROEDE VINGETAGSTEN GL. DANSK FORBRUG: 14,6 STK/M2','15','Hurtigts muligt','Ring op omkring leveringstidspunkt',null,'behandlet','test3@test.dk'),
        //(210,420,320,null,null,'rejsning','ROEDE VINGETAGSTEN GL. DANSK FORBRUG: 14,6 STK/M2','15',null,null,null,'behandlet','test3@test.dk'),
        //(320,570,410,null,null,'rejsning','ROEDE VINGETAGSTEN GL. DANSK FORBRUG: 14,6 STK/M2','25','Kan jeg sætte en tagrende på denne carport?','Kontakt vedr. valg af tagtype','2017-07-14','ny','test5@test.dk'),
        //(270,570,410,null,null,'rejsning','ROEDE VINGETAGSTEN GL. DANSK FORBRUG: 14,6 STK/M2','25',null,null,'2017-07-15','ny','test8@test.dk'),
        //(320,420,320,null,null,'rejsning','ROEDE VINGETAGSTEN GL. DANSK FORBRUG: 14,6 STK/M2','15','Ring tak!',null,null,'ny','test6@test.dk');
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
                stmt.execute("drop table if exists Inquiry");
                stmt.execute("create table Inquiry like InquiryTest");
                stmt.execute("insert into Inquiry select * from InquiryTest");
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

    //int id, int carportHeight, int carportLength, int carportWidth, int shackWidth, int shackLength, String roofType, String roofMaterial, String angle, String commentCustomer, String commentEmployee, Date period, String status, String email, int id_employee, Timestamp date
    @Test
    public void testRegisterInitialInquiry() throws FogException {
        System.out.println("registerInitialInquiry");
        Inquiry i = new Inquiry(0, 210, 700, 360, 0, 0, "rejsning", "ROEDE VINGETAGSTEN GL. DANSK FORBRUG: 14,6 STK/M2", "15", "", "", new Date(2017, 11, 21), "ny", "test1@test.dk", 0, null);
        Inquiry result = InquiryMapper.registerInitialInquiry(i);
        assertEquals("ROEDE VINGETAGSTEN GL. DANSK FORBRUG: 14,6 STK/M2", result.getRoofMaterial());
        assertTrue(result.getId() > 8);
    }

    @Test
    public void testAllInquiries() throws FogException {
        System.out.println("allInquiries");
        List<Inquiry> inquiries = InquiryMapper.allInquiries();
        assertNotNull(inquiries);
        assertTrue(inquiries.size() > 1);

    }

    @Test
    public void inquiryById() throws FogException {
        System.out.println("inquiryById");
        int id = 1;
        Inquiry inquiry = InquiryMapper.inquiryById(id);
        assertNotNull(inquiry);
        assertTrue(inquiry.getCarportLength() == 420);
    }

    @Test(expected = FogException.class)
    public void inquiryByIdFailException() throws FogException {
        System.out.println("inquiryById Fail");
        int id = 0;
        Inquiry inquiry = InquiryMapper.inquiryById(id);
    }

    @Test
    public void latestInquiryByCustomer() throws FogException {
        System.out.println("latestInquiryByCustomer");
        String email = "test1@test.dk";
        Inquiry inquiry = InquiryMapper.latestInquiryByCustomer(email);
        assertNotNull(inquiry);
    }
    
    @Test
    public void getCustomerInquiries() throws FogException {
        System.out.println("allInquiries");
        Customer customer = new Customer("test1@test.dk", null, null, 0, null, 0, null, null);
        List<Inquiry> inquiries = InquiryMapper.getCustomerInquiries(customer);
        assertNotNull(inquiries);
        assertTrue(inquiries.size() == 2);
    }
    
    @Test(expected = FogException.class)
    public void getCustomerInquiriesExceptionFail() throws FogException  {
        System.out.println("customerInquiries Exception");
        Customer customer = new Customer("fail@fail.dk", null, null, 0, null, 0, null, null);
        List<Inquiry> inquiries = InquiryMapper.getCustomerInquiries(customer);
    }
            
    @Test
    public void testUpdateInquiry() throws FogException {
        System.out.println("update shack size");
        int id = 1, height = 300, length = 300, width = 300, shackLength = 200, shackWidth = 200;
        int lRes = 200, wRes = 200;
        String roofType = "fladt";
        String roofMat = "testMat";
        String angle = null;
        String comment = null;
        String status = "ny";

        Inquiry result = InquiryMapper.updateInquiry(id, height, length, width, shackLength, shackWidth, roofType, roofMat, angle, comment, status);

        assertEquals(lRes, result.getShackLength());
        assertEquals(wRes, result.getShackWidth());
    }
}
