/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alexander W. Hørsted-Andersen 
 */
public class CalcShackTransomTest {
    /**
     * Test of getTransomsForShackWidth method, of class CalcShackTransom.
     */
    @Test
    public void testGetTransomsForShackWidth() throws FogException {
        System.out.println("getTransomsForShackWidth");
        int shackWidth = 360;
        List<Product> transomList = Calculator.getChosenCategory("løsholt", DataLayer.ProductMapper.getProducts());
        OrderLine result = CalcShackTransom.getTransomsForShackWidth(shackWidth, transomList);
        int expResult = 6;  //6 * 3600     / str 3600
        System.out.println(result.length);
        assertEquals(expResult, result.quantity);
    }

    /**
     * Test of getTransomsForShackLength method, of class CalcShackTransom.
     */
    @Test
    public void testGetTransomsForShackLength() throws FogException {
        System.out.println("getTransomsForShackLength");
        int shackLength = 730;
        List<Product> transomList = Calculator.getChosenCategory("løsholt", DataLayer.ProductMapper.getProducts());
        OrderLine result = CalcShackTransom.getTransomsForShackLength(shackLength, transomList);
        int expResult = 6;  //4 * 7300     / 5400
        System.out.println(result.length);
        assertEquals(expResult, result.quantity);
    }

}
