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
 * @author Stanislav
 */
public class CalcTopPlate {

    /**
     * Calculate the amount of TopPlate of a given type, needed to fill corresponding dimensions
     * @param length int
     * @param width int
     * @param products list of Product
     * @return OrderLine Object
     */
    public static OrderLine getTopPlatesFlatRoof(int length, int width, List<Product> products) {

        int roofSideEaves = 70; // (NO POLES UNDER EAVES) FLATROOF = 70mm
        int whenToAddRowSize = 600; // WIDTH of carport before adding the first extra row
        int intervalToPlaceRowsForWidth = 300; // How often to place a row of poles
        int thicknessOfUsedBoards = 10; // Boards used to cover up front and back.

        //Reduce Length for endboards
        length -= thicknessOfUsedBoards;

        //TO MM TO MATCH DB MESURAMENTS
        length *= 10;
        // GET right length product
        Product p = getCorrectLengthProduct(length, products);

        int rowsOfPosts = 2;

        //REDUCE THE COMPLETE LENGTHS WITH THE ROOF EVE SIZES
        width -= roofSideEaves;

        // FIND OUT HOW MANY ROWS OF POLES ARE NECESSARY
        if (width >= whenToAddRowSize) {
            rowsOfPosts += (width / intervalToPlaceRowsForWidth) - 1;
        }

        //IF LONGER THEN AVAILABLE PIECES
        if (length % p.getLength() > 0) {
            // finds the full length of the remaining pieces, and then how many of the product there is need for to cover that length
            int extraPostsIfInsuficcientLength = ((length % p.getLength()) * rowsOfPosts) / p.getLength();
            // checks if there still is a piece remaining after, and adds one additional product
            if (((length % p.getLength()) * rowsOfPosts) % p.getLength() > 0) {
                extraPostsIfInsuficcientLength++;
            }
            rowsOfPosts += extraPostsIfInsuficcientLength;
        }

        return new OrderLine(p, p.getLength(), rowsOfPosts, "stk", "Remme i sider, sadles ned i stolper");
    }

    /**
     * Calculate the amount of TopPlate of a given type, needed to fill corresponding dimensions
     * @param length int
     * @param width int
     * @param products list of Product
     * @return OrderLine Object
     */
    public static OrderLine getTopPlatesPitchedRoof(int length, int width, List<Product> products) {
        int roofFrontBackEves = 50; // 300mm back, and chosen 200mm for front.
        int roofSideEaves = 70; // (NO POLES UNDER EAVES) FLATROOF = 700mm
        int whenToAddRowSize = 600; // WIDTH of carport before adding the first extra row
        int intervalToPlaceRowsForWidth = 300; // How often to place a row of poles

        //Reduce Length for eave
        length -= roofFrontBackEves;

        //TO MM TO MATCH DB MESURAMENTS
        length *= 10;
        // GET right length product
        Product p = getCorrectLengthProduct(length, products);

        int rowsOfPosts = 2;

        //REDUCE THE COMPLETE LENGTHS WITH THE ROOF EVE SIZES
        width -= roofSideEaves;

        // FIND OUT HOW MANY ROWS OF POLES ARE NECESSARY
        if (width >= whenToAddRowSize) {
            rowsOfPosts += (width / intervalToPlaceRowsForWidth) - 1;
        }

        //IF LONGER THEN AVAILABLE PIECES
        if (length % p.getLength() > 0 && length > p.getLength()) {
            // finds the full length of the remaining pieces, and then how many of the product there is need for to cover that length
            int extraPostsIfInsuficcientLength = ((length % p.getLength()) * rowsOfPosts) / p.getLength();
            // checks if there still is a piece remaining after, and adds one additional product
            if (((length % p.getLength()) * rowsOfPosts) % p.getLength() > 0) {
                extraPostsIfInsuficcientLength++;
            }
            rowsOfPosts += extraPostsIfInsuficcientLength;
        }

        return new OrderLine(p, p.getLength(), rowsOfPosts, "stk", "Remme i sider, sadles ned i stolper");
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
