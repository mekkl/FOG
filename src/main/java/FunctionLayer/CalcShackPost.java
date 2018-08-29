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
public class CalcShackPost {

    /**
     * Takes carport dimensions and shack dimensions as input, and list of poles. 
     * If shack width is less than that of carport, add 2 extra posts, else add 1.
     * @param carportLength int
     * @param carportWidth int
     * @param height int
     * @param shackLength int
     * @param shackWidth int
     * @param poles list of Product
     * @return Orderline Object
     */
    public static OrderLine getPostsShack(int carportLength, int carportWidth, int height, int shackLength, int shackWidth, List<Product> poles) {

        // +90cm to burry the post
        height += 90;
        //TO MM TO MATCH DB MESURAMENTS
        height *= 10;

        //post to use
        Product post = getCorrectLengthProduct(height, poles);

        //shack initially connected to existing carport corner pole (1), so need 1  for corner (2), and one extra for door.
        int numberOfPost = 2;

        //if shack does not take up full width of carport width; use an extra pole for each corner (3,4). 
        if (carportWidth > shackWidth) {
            numberOfPost += 2;
            //if same width as carport, other corner is existing carport pole (3), and add 1 pole for last corner (4).
        } else {
            numberOfPost += 1;
        }
        return new OrderLine(post, post.getLength(), numberOfPost, "stk", "Stolper nedgraves 90 cm. i jord - til skurbrug");
    }

    /**
     * Support method - Calculate the most correct Product from given dimension.
     * @param height int 
     * @param products list of Product
     * @return Product
     */
    private static Product getCorrectLengthProduct(int height, List<Product> products) {
        // Sorts list on product.getLength() attribute.
        products.sort(Comparator.comparing(Product::getLength));
        // We need the list in descending order, so we reverse order it, so we start with the longest length.
        Collections.reverse(products);

        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            // if the products is longer than product available, the product is set to the largest in stock
            // else if the product is shorter than the shortest available product, the product is set as the smallest in stock
            // in regular cases it will chose the appropriate size product for the length
            if (height / p.getLength() >= 1 || i == products.size() - 1) {
                return p;
            }
        }
        return null;
    }

}
