/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author awha8
 */
public class calcTrapezeRoofTest {

    @Test
    public void testCalculateAmountOfTrapezeRoof() throws FogException {
        System.out.println("calculateAmountOfTrapezeRoof");
        int length = 5000;
        int width = 1000;
        int expQty = 15;   
        Product trapezeRoof = DataLayer.ProductMapper.getSingleProduct("trapeztag", "CEMBRIT OVENLYSPLADE B7 PVC GLASKLAR 1100X610X1MM");
        OrderLine result = CalcTrapezeRoof.calculateAmountOfTrapezeRoof(length, width, trapezeRoof);
        assertEquals(expQty, result.quantity);
    }

}
