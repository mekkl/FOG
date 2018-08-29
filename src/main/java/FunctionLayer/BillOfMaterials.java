/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Stanislav
 */
public class BillOfMaterials {

    List<OrderLine> materials;

    public BillOfMaterials() {
        this.materials = new ArrayList<>();
    }

    public void addOrderLine(OrderLine o) {
        this.materials.add(o);
    }

    public long getTotalPrice() {
        long totalPrice = 0;
        for (OrderLine o : materials) {
            totalPrice += o.getOrderLinePrice();
        }
        return totalPrice;
    }

    public List<OrderLine> getMaterials() {
        return materials;
    }
    
    
}
