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
 * @author Stanislav
 */
public class CalcPost {

    /**
     * Calculate the amount of Post of a given type, needed to fill corresponding roof area.
     * @param length int
     * @param width int
     * @param height int
     * @param posts list of Product
     * @return OrderLine Object
     */
    public static OrderLine getPostsFlatRoof(int length, int width, int height, List<Product> posts) {
        //UNCERTAIN VARIABLES (for product owner to decide)
        int roofFrontBackEaves = 130; // (NO POLES UNDER EAVES) FLATROOF = 1300mm
        int roofSideEaves = 70; // (NO POLES UNDER EAVES) FLATROOF = 700mm
        int whenToAddRowSize = 600; // WIDTH of carport before adding the first extra row
        int intervalToPlaceRowsForWidth = 300; // How often to place a row of poles
        int whenToAddExtraPostSize = 620; // Length of carport before adding an extra post to a row
        int intervalToPlacePostsForLength = 310; // how often to place a pole in a row

        // +90cm to burry the post
        height += 90;
        //TO MM TO MATCH DB MESURAMENTS
        height *= 10;

        Product post = getCorrectLengthProduct(height, posts);

        //QUANTITY OF END POST FOR ONE ROW
        int quantity = 2;
        int rowsOfPosts = 2;

        //REDUCE THE COMPLETE LENGTHS WITH THE ROOF EVE SIZES
        length -= roofFrontBackEaves;
        width -= roofSideEaves;

        // FIND OUT HOW MANY ROWS OF POLES ARE NECESSARY
        if (width >= whenToAddRowSize) {
            rowsOfPosts += (width / intervalToPlaceRowsForWidth) - 1;
        }
        // HOW MANY POLES PR. ROW 
        if (length >= whenToAddExtraPostSize) {
            // Counts how many there can be between the existing ones and removes one.
            quantity += (length / intervalToPlacePostsForLength) - 1;
        }

        // TIMES UP THE QUANTITY WITH THE NUMBER OF ROWS
        quantity *= rowsOfPosts;

        // ADD 90CM TO THE HEIGHT, FOR DIG-IN OF POLE
        return new OrderLine(post, post.getLength(), quantity, "stk", "Stolper nedgraves 90 cm. i jord");
    }

    /**
     * Calculate the amount of Post of a given type, needed to fill corresponding roof area.
     * @param length int
     * @param width int
     * @param height int
     * @param posts list of Product
     * @return OrderLine Object
     */
    public static OrderLine getPostsPitchedRoof(int length, int width, int height, List<Product> posts) {
        //UNCERTAIN VARIABLES (for product owner to decide)
        int roofFrontBackEaves = 110; // (NO POLES UNDER EAVES) FLATROOF = 1100mm
        int roofSideEaves = 40; // (NO POLES UNDER EAVES) FLATROOF = 400mm
        int whenToAddRowSize = 600; // WIDTH of carport before adding the first extra row
        int intervalToPlaceRowsForWidth = 300; // How often to place a row of poles
        int whenToAddExtraPostSize = 550; // Length of carport before adding an extra post to a row
        int intervalToPlacePostsForLength = 275; // how often to place a pole in a row

        // +90cm to burry the post
        height += 90;
        //TO MM TO MATCH DB MESURAMENTS
        height *= 10;

        Product post = getCorrectLengthProduct(height, posts);

        //QUANTITY OF END POST FOR ONE ROW
        int quantity = 2;
        int rowsOfPosts = 2;

        //REDUCE THE COMPLETE LENGTHS WITH THE ROOF EVE SIZES
        length -= roofFrontBackEaves;
        width -= roofSideEaves;

        // FIND OUT HOW MANY ROWS OF POLES ARE NECESSARY
        if (width >= whenToAddRowSize) {
            rowsOfPosts += (width / intervalToPlaceRowsForWidth) - 1;
        }
        // HOW MANY POLES PR. ROW 
        if (length >= whenToAddExtraPostSize) {
            // Counts how many there can be between the existing ones and removes one.
            quantity += (length / intervalToPlacePostsForLength) - 1;
        }

        // TIMES UP THE QUANTITY WITH THE NUMBER OF ROWS
        quantity *= rowsOfPosts;

        return new OrderLine(post, post.getLength(), quantity, "stk", "Stolper nedgraves 90 cm. i jord");
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
