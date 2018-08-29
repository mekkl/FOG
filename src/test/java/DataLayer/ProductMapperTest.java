/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import FunctionLayer.FogException;
import FunctionLayer.Product;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Stanislav
 */
public class ProductMapperTest {
//-- tagsten
//('ROEDE VINGETAGSTEN GL. DANSK FORBRUG: 14,6 STK/M2','tagsten', 1495, 404, 236, 0),
//('ROEDE RYGSTEN MODEL VOLSTRUP DANSKTAG- FORBRUG: 3,5 STK/LBM','tagsten', 8995, 0, 0, 0),  -- ingen mål defineret
    

    @Test
    public void testGetProducts() throws FogException {
        System.out.println("getProducts");
        List<Product> product = ProductMapper.getProducts();
        assertNotNull(product);
        assertTrue(product.size() > 2);
    }

    @Test
    public void testGetSingleProduct() throws FogException {
        System.out.println("getSingleProduct");
        String category = "tagsten";
        String productName = "ROEDE RYGSTEN MODEL VOLSTRUP DANSKTAG- FORBRUG: 3,5 STK/LBM";
        Product result = ProductMapper.getSingleProduct(category, productName);
        assertTrue(result.getPrice() == 8995);
    }
    
}
