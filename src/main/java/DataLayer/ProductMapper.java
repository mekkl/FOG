/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import FunctionLayer.FogException;
import FunctionLayer.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Stanislav
 */
public class ProductMapper {

    private static List<Product> productList;

    /**
     * Get a list from the database with Product objects.
     * @return List of Product
     * @throws FogException if SQLException or Exception is thrown, it is converted 
     * to this exception and added an appropriate message for the customer in some cases
     */
    public static List<Product> getProducts() throws FogException {
        String SQL = "SELECT * FROM Product;";

        if (productList == null) { 

            try (Connection conn = DBConnector.getConnection();
                    PreparedStatement pstmt = conn.prepareStatement(SQL);
                     ResultSet rs = pstmt.executeQuery();) {
                
                productList = new ArrayList<>();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String cat = rs.getString("category");
                    long price = rs.getLong("price");
                    int length = rs.getInt("length");
                    int width = rs.getInt("width");
                    int height = rs.getInt("height");
                    productList.add(new Product(id, name, cat, price, length, width, height));
                }
            } 
            catch ( SQLException ex ) {
                throw new FogException( ex.getMessage() );
            }
        }
        return productList;
    }

    /**
     * Get product from database.
     * @param category String 
     * @param productName String
     * @return Product
     * @throws FogException if SQLException or Exception is thrown, it is converted 
     * to this exception and added an appropriate message for the customer in some cases
     */
    public static Product getSingleProduct(String category, String productName) throws FogException {
        Product product = null;
        String SQL = "SELECT * FROM Product WHERE category=? AND Product.name=?";

         try (Connection conn = DBConnector.getConnection();
                    PreparedStatement pstmt = conn.prepareStatement(SQL);) {
            pstmt.setString(1, category);
            pstmt.setString(2, productName);
            try (ResultSet rs = pstmt.executeQuery();) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String cat = rs.getString("category");
                    long price = rs.getLong("price");
                    int length = rs.getInt("length");
                    int width = rs.getInt("width");
                    int height = rs.getInt("height");
                    product = new Product(id, name, cat, price, length, width, height);
                } else {
                    throw new FogException("Kunne ikke finde produkt");
                }
            } 
         }
        catch ( SQLException ex ) {
                throw new FogException( ex.getMessage() );
        }
        return product;
    }
}
