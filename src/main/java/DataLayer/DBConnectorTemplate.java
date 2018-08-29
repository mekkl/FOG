/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import FunctionLayer.FogException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Stanislav
 */
public class DBConnectorTemplate {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://207.154.222.88:3306/fog";
    private static final String USER = "";
    private static final String PASSWORD = "";
    private static Connection conn = null;

    public static void setConn(Connection conn) {
        DBConnectorTemplate.conn = conn;
    }

    public static Connection getConnection() throws FogException {
        try {
            if (conn == null || conn.isClosed()) {
                Class.forName(DRIVER).newInstance();
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex) {
            throw new FogException(ex.getMessage());
        }
        return conn;
    }

    /* USED AFTER A MAPPER METHEOD IS EXECUTED TO CLOSE STANDING CONNECTIONS !!!
    try {
    
    //// MAPPER METHEOD HERE 
    
    finally
    {
        // Always make sure result sets and statements are closed,
        // and the connection is returned to the pool
        
            if (rs != null)
                rs.close ();
            if (stmt != null)
                stmt.close ();
            if (pstmt != null)
                pstmt.close ();
            if (conn != null)
                conn.close ();
        }
        */
}
