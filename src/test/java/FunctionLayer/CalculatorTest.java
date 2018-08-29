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
 * @author Stanislav
 */
public class CalculatorTest {
        
    //TODO create test
    @Test
    public void testGetBillOfMaterials() throws FogException {
        System.out.println("getBillOfMaterials");
        Inquiry i = new Inquiry(0, 210, 420, 420, 0, 0, "fladt","CEMBRIT OVENLYSPLADE B7 PVC GLASKLAR 1100X610X1MM" , null, null, null, null, null, null, 0, null);
        BillOfMaterials bom = Calculator.getBillOfMaterials(i);
        List<OrderLine> orderlines = bom.getMaterials();
        assertNotNull(bom);
        
        int expRes = 4;
        //Stolper
        int res = orderlines.get(0).getQuantity();
        assertEquals(expRes, res);
        //Spær
        expRes = 8;
        res = orderlines.get(2).getQuantity();
        assertEquals(expRes, res);

    }

    
}
