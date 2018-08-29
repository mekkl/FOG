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
public class CalcShackCladdingTest {


    /**
     * Test of getCladdingForShack method, of class CalcShackCladding.
     * @throws FunctionLayer.FogException if connection problems
     */
    @Test
    public void testGetCladdingForShackFlatRoof() throws FogException {
        System.out.println("getCladdingForShack");
        int shackLength = 730;
        int shackWidth = 360;
        int carportHeight = 240;
        List<Product> claddingList = Calculator.getChosenCategory("beklædning", DataLayer.ProductMapper.getProducts());
        OrderLine result = CalcShackCladding.getCladdingForShackFlatRoof(shackLength, shackWidth, carportHeight, claddingList);
        int expResult = 269;
        assertEquals(expResult, result.quantity);
    }
    @Test
    public void testGetCladdingForShackPitchedRoofGable() throws FogException {
        System.out.println("getCladdingForShackPitchedRoofGable");
        int carportWidth = 360;
        int carportHeight = 240;
        int shackWidth = 360;
        int roofAngle = 15;
        List<Product> claddingList = Calculator.getChosenCategory("beklædning", DataLayer.ProductMapper.getProducts());
        OrderLine result = CalcShackCladding.getCladdingForShackPitchedRoofGable(carportWidth, carportHeight, shackWidth, roofAngle, claddingList);
        int expResult = 88;
        assertEquals(expResult, result.quantity);
    }

    @Test
    public void testGetCladdingForShackPitchedRoofSide() throws FogException {
        System.out.println("getCladdingForShackPitchedRoofSide");
        int carportWidth = 360;
        int carportHeight = 240;
        int shackLength = 730;
        int roofAngle = 15;
        List<Product> claddingList = Calculator.getChosenCategory("beklædning", DataLayer.ProductMapper.getProducts());
        OrderLine result = CalcShackCladding.getCladdingForShackPitchedRoofSide(carportWidth, carportHeight, shackLength, roofAngle, claddingList);
        int expResult = 181;        
        assertEquals(expResult, result.quantity);
    }

}
