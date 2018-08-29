package DataLayer;

import FunctionLayer.Customer;
import FunctionLayer.FogException;
import FunctionLayer.LoginException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alexander W. Hørsted-Andersen
 */
public class CustomerMapper {

    /**
     * Checks if user with given credentials exist in database.
     *
     * @param email String with user email
     * @param password String with user password
     * @param ipAddress String with ipAddress
     * @return Customer object if exist.
     * @throws LoginException with ipAddress info if not.
     * @throws FogException if SQLException or Exception is thrown, it is converted 
     * to this exception and added an appropriate message for the customer in some cases
     */
    public static Customer login(String email, String password, String ipAddress) throws LoginException, FogException {
        String SQL = "SELECT * from Customer INNER JOIN Zipcode ON Customer.zipcode = Zipcode.zipcode WHERE email=? AND password=?;";
        try (Connection conn = DBConnector.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL);) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            try (ResultSet rs = pstmt.executeQuery();) {
                if (rs.next()) {
                    String mail = rs.getString("email");
                    String name = rs.getString("name");
                    String surname = rs.getString("surname");
                    int phonenumber = rs.getInt("phonenumber");
                    String address = rs.getString("address");
                    int zipcode = rs.getInt("zipcode");
                    String pass = rs.getString("password");
                    String city = rs.getString("city");

                    Customer customer = new Customer(mail, name, surname, phonenumber, address, zipcode, pass, city);
                    return customer;
                } else {
                    throw new LoginException("Forsøg på Login for bruger med email: " + email + ", adgangskode: " + password + ", IP Addresse: " + ipAddress);
                }
            }
        } catch (SQLException ex) {
            throw new FogException(ex.getMessage());
        } 
    }

    /**
     * Get corresponding city from database to given zipcode.
     *
     * @param zipcode
     * @return City as String.
     * @throws FogException if SQLException or Exception is thrown, it is converted 
     * to this exception and added an appropriate message for the customer in some cases
     */
    public static String getCity(int zipcode) throws FogException {
        String SQL = "SELECT city from Zipcode WHERE zipcode=?";
        try (Connection conn = DBConnector.getConnection();
               PreparedStatement pstmt = conn.prepareStatement(SQL);) {
            pstmt.setString(1, Integer.toString(zipcode));
            try(ResultSet rs = pstmt.executeQuery();) {
                if (rs.next()) {
                    return rs.getString(1);
                } else {
                    throw new FogException(" Ingen by med dette postnummer ");
                }
            }
        } catch (SQLException ex) {
            throw new FogException(ex.getMessage());
        } 
    }
    
    /**
     * Insert a customer in database.
     * @param c Customer object
     * @return Customer object
     * @throws FogException if SQLException or Exception is thrown, it is converted 
     * to this exception and added an appropriate message for the customer in some cases
     */
    public static Customer createCustomer(Customer c) throws FogException {
        String SQL = "INSERT INTO Customer (email, name, surname, phonenumber, address, password, zipcode) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnector.getConnection();
               PreparedStatement ps = conn.prepareStatement(SQL);) {
            ps.setString(1, c.getEmail());
            ps.setString(2, c.getName());
            ps.setString(3, c.getSurname());
            ps.setString(4, Integer.toString(c.getPhonenumber()));
            ps.setString(5, c.getAddress());
            ps.setString(6, c.getPassword());
            ps.setString(7, Integer.toString(c.getZipcode()));
            ps.executeUpdate();

            String email = c.getEmail();
            String passwd = c.getPassword();
            String name = c.getName();
            String surname = c.getSurname();
            int phonenumber = c.getPhonenumber();
            String address = c.getAddress();
            int zipcode = c.getZipcode();
            String city = getCity(zipcode);

            return new Customer(email, name, surname, phonenumber, address, zipcode, passwd, city);

        } catch (SQLException ex) {
            if (ex.getMessage().contains("Duplicate")) {
                throw new FogException("Denne email adresse eksisterer allerede i systemet.");
            } else {
                throw new FogException(ex.getMessage());
            }

        } 
    }

    /**
     * Get list of customer objects with inquiry from database.
     *
     * @return list of Customer objects.
     * @throws FogException if SQLException or Exception is thrown, it is converted 
     * to this exception and added an appropriate message for the customer in some cases
     */
    public static List<Customer> customersWithInquiry() throws FogException {
        List<Customer> customers = new ArrayList<>();
        Customer c;
        String SQL = "SELECT * FROM Customer c INNER JOIN Inquiry i ON c.email = i.email INNER JOIN Zipcode z ON c.zipcode = z.zipcode";
        try (Connection conn = DBConnector.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL);
             ResultSet rs = pstmt.executeQuery();) {
             while (rs.next()) {
                 c = new Customer(
                         rs.getString(1),
                         rs.getString(2),
                         rs.getString(3),
                         rs.getInt(4),
                         rs.getString(5),
                         rs.getInt(6),
                         rs.getString(7),
                         rs.getString(23));
                 customers.add(c);
             }
             return customers;
        } catch (SQLException ex) {
            throw new FogException(ex.getMessage());
        } 
    }

    /**
     * Get list of customers from database with inquiries of a given status.
     *
     * @param status String ogject
     * @return List of Customer Objects
     * @throws FogException if SQLException or Exception is thrown, it is converted 
     * to this exception and added an appropriate message for the customer in some cases
     */
    public static List<Customer> customersByInquiryStatus(String status) throws FogException {
        List<Customer> customers = new ArrayList<>();
        Customer c;
        String SQL = "SELECT DISTINCT(c.email), c.name, c.surname, c.phonenumber, c.address, c.zipcode, c.password, z.city FROM Customer c INNER JOIN Zipcode z ON c.zipcode = z.zipcode INNER JOIN Inquiry i on c.email = i.email WHERE i.status = ?";

        try (Connection conn = DBConnector.getConnection();
               PreparedStatement pstmt = conn.prepareStatement(SQL);) {
            pstmt.setString(1, status);
            try (ResultSet rs = pstmt.executeQuery();) {
                while (rs.next()) {
                    c = new Customer(
                            rs.getString(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getInt(4),
                            rs.getString(5),
                            rs.getInt(6),
                            rs.getString(7),
                            rs.getString(8));
                    customers.add(c);
                }
                return customers;
            }
        } catch (SQLException ex) {
            throw new FogException(ex.getMessage());
        }
    }

    /**
     * Get lst of all customers from database.
     *
     * @return list of Customer Objects.
     * @throws FogException if SQLException or Exception is thrown, it is converted 
     * to this exception and added an appropriate message for the customer in some cases
     */
    public static List<Customer> allCustomers() throws FogException {
        List<Customer> customers = new ArrayList<>();
        Customer c;
        String SQL = "SELECT * FROM Customer c INNER JOIN Zipcode z ON c.zipcode = z.zipcode";

        try (Connection conn = DBConnector.getConnection();
               PreparedStatement pstmt = conn.prepareStatement(SQL);
                ResultSet rs = pstmt.executeQuery();) {
            while (rs.next()) {
                c = new Customer(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getString(8));
                customers.add(c);
            }
            return customers;

        } catch (SQLException ex) {
            throw new FogException(ex.getMessage());
        } 
    }

    /**
     * Get customer from database with a given email adress.
     *
     * @param email String object
     * @return Customer Object.
     * @throws FogException if SQLException or Exception is thrown, it is converted 
     * to this exception and added an appropriate message for the customer in some cases
     */
    public static Customer customerByEmail(String email) throws FogException {
        Customer customer;
        String SQL = "SELECT * FROM Customer c INNER JOIN Zipcode z ON c.zipcode = z.zipcode WHERE c.email = ?";

        try (Connection conn = DBConnector.getConnection();
               PreparedStatement pstmt = conn.prepareStatement(SQL);) {
            pstmt.setString(1, email);
            try (ResultSet rs = pstmt.executeQuery();) {
                if (rs.next()) {
                    customer = new Customer(
                            rs.getString(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getInt(4),
                            rs.getString(5),
                            rs.getInt(6),
                            rs.getString(7),
                            rs.getString(8));

                    return customer;
                } else {
                    throw new FogException(" Ingen kunde med fundet med denne email. ");
                }
            }
        } catch (SQLException ex) {
            throw new FogException(ex.getMessage());
        } 
    }
}
