/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Alexander W. Hørsted-Andersen 
 */
public class CalcShackTransom {

    //TRANSOM = LØSHOLT
    //bruges til horisontalt vægskelet til redskabsrum/skur/shack.
    //shack has 3x transom layers;
    //- bottomLayer: transoms on 4 sides, ~20cm above ground
    //- middleLayer: transoms on 4 sides , 90cm above bottomLayer
    //- topLayer: transoms on 2 sides (ends, not sides) at the top between "remme" - 2 other uses sides connected to "remme" already in place. 
    //gavle

    /**
     * Calculate the amount of Transom of a given type, needed to fill corresponding area
     * @param shackWidth int
     * @param transoms list of Product
     * @return OrderLine Object
     */
    public static OrderLine getTransomsForShackWidth(int shackWidth, List<Product> transoms) {

        shackWidth = shackWidth * 10;

        double bottomLayer = shackWidth * 2;  //2 sides
        double middleLayer = shackWidth * 2;  //2 sides
        double topLayer = shackWidth * 2;   //2 sides
        double totalTransomLength = bottomLayer + middleLayer + topLayer;

        Product transom = getCorrectLengthProduct(shackWidth, transoms);
        int productLength = transom.getLength();

        double res = totalTransomLength / productLength;
        int result = (int) Math.ceil(res);
        return new OrderLine(transom, transom.getLength(), result, "stk", "løsholter i gavle af skur");
    }

    //sides

    /**
     * Calculate the amount of Transom of a given type, needed to fill corresponding area
     * @param shackLength int
     * @param transoms list of Product
     * @return OrderLine Object
     */
    public static OrderLine getTransomsForShackLength(int shackLength, List<Product> transoms) {

        shackLength = shackLength * 10;

        double bottomLayer = shackLength * 2;  //2 sides
        double middleLayer = shackLength * 2;  //2 sides
        double topLayer = shackLength * 0;   //0 sides
        double totalTransomLength = bottomLayer + middleLayer + topLayer;

        Product transom = getCorrectLengthProduct(shackLength, transoms);
        double productLength = transom.getLength();

        double res = totalTransomLength / productLength;
        int result = (int) Math.ceil(res);
        return new OrderLine(transom, transom.getLength(), result, "stk", "løsholter i siderne af skur");
    }

    /**
     * Support method - Calculate the most correct Product from given dimension.
     * @param length int
     * @param products list of Product
     * @return Product
     */
    private static Product getCorrectLengthProduct(int length, List<Product> products) {
        // Sorts list on product.getLength() attribute.
        products.sort(Comparator.comparing(Product::getLength));
        // We need the list in descending order, so we reverse order it, so we start with the longest length.
        Collections.reverse(products);

        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            // if the products is longer than product available, the product is set to the largest in stock
            // else if the product is shorter than the shortest available product, the product is set as the smallest in stock
            // in regular cases it will chose the appropriate size product for the length
            if (length / p.getLength() >= 1 || i == products.size() - 1) {
                return p;
            }
        }
        return null;
    }
}
