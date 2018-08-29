/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Alexander W. Hørsted-Andersen
 */
public class CalcShackCladding {

    //CLADDING = (side)BEKLÆDNINGsbræt
    //bruges monteres vertikalt på Transom/løsholt i 2 lags mønster:
    //--------------------     løsholt
    //  --|--   --|--       cladding, inner
    //      --|--           cladding, outer
    /**
     * Calculate the amount of Cladding of a given type, needed to fill
     * corresponding dimensions
     *
     * @param shackLength int
     * @param shackWidth int
     * @param carportHeight int
     * @param claddingList list of Product
     * @return OrderLine Object
     */
    public static OrderLine getCladdingForShackFlatRoof(int shackLength, int shackWidth, int carportHeight, List<Product> claddingList) {

        //CM TO MM
        shackWidth = shackWidth * 10;
        shackLength = shackLength * 10;
        carportHeight = carportHeight * 10;

        double lengthToBeCovered = shackLength * 2;  //2 sides
        double widthToBeCovered = shackWidth * 2;    //2 sides
        double bufferBetweenCladding = 60;  //mm

        Product cladding = getCorrectLengthProduct(carportHeight, claddingList);

        double innerCladdingLayerLength = lengthToBeCovered / (cladding.getWidth() + bufferBetweenCladding);
        double innerCladdingLayerWidth = widthToBeCovered / (cladding.getWidth() + bufferBetweenCladding);
        double innerCladdingLayer = innerCladdingLayerLength + innerCladdingLayerWidth;

        double res = (innerCladdingLayer * 2) - 4;  //1 on 2; 1 less each side * 4 = 4;

        int result = (int) Math.ceil(res);
        return new OrderLine(cladding, cladding.getLength(), result, "stk", "til beklædning af skur 1 på 2");

    }

    //gavle / gable / width
    /**
     * Calculate the amount of Cladding of a given type, needed to fill
     * corresponding dimensions
     *
     * @param carportWidth int
     * @param carportHeight int
     * @param shackWidth int
     * @param roofAngle int
     * @param claddingList list of Product
     * @return OrderLine Object.
     */
    public static OrderLine getCladdingForShackPitchedRoofGable(int carportWidth, int carportHeight, int shackWidth, int roofAngle, List<Product> claddingList) {

        //CM TO MM
        shackWidth = shackWidth * 10;
        carportHeight = carportHeight * 10;

        double widthToBeCovered = shackWidth * 2;    //2 sides
        double bufferBetweenCladding = 60;  //mm

        double rooffHeight = calcRoofHeight(carportWidth, roofAngle);
        int totalHeight = (int) Math.ceil(carportHeight + rooffHeight);

        Product cladding = getCorrectLengthProduct(totalHeight, claddingList);

        double innerCladdingLayerWidth = widthToBeCovered / (cladding.getWidth() + bufferBetweenCladding);

        double res = (innerCladdingLayerWidth * 2) - 2;  //1 less each side = 2;

        int result = (int) Math.ceil(res);
        return new OrderLine(cladding, cladding.getLength(), result, "stk", "beklædning af gavle 1 på 2");

    }

    //side / length
    /**
     * Calculate the amount of Cladding of a given type, needed to fill
     * corresponding dimensions.
     *
     * @param carportWidth int
     * @param carportHeight int
     * @param shackLength int
     * @param roofAngle int
     * @param claddingList list of Product
     * @return OrderLine Object
     */
    public static OrderLine getCladdingForShackPitchedRoofSide(int carportWidth, int carportHeight, int shackLength, int roofAngle, List<Product> claddingList) {

        //CM TO MM
        shackLength = shackLength * 10;
        carportHeight = carportHeight * 10;

        double lengthToBeCovered = shackLength * 2;  //2 sides
        double bufferBetweenCladding = 60;  //mm

        double rooffHeight = calcRoofHeight(carportWidth, roofAngle);
        int totalHeight = (int) Math.ceil(carportHeight + rooffHeight);

        Product cladding = getCorrectLengthProduct(totalHeight, claddingList);

        double innerCladdingLayerLength = lengthToBeCovered / (cladding.getWidth() + bufferBetweenCladding);

        double res = (innerCladdingLayerLength * 2) - 2;  // 1 less each side 

        int result = (int) Math.ceil(res);
        return new OrderLine(cladding, cladding.getLength(), result, "stk", "Beklædning af skur 1 på 2");

    }

    /**
     * Support method - Calculate the most correct Product from given dimension.
     *
     * @param length int
     * @param products list of Product
     * @return Product
     */
    private static Product getCorrectLengthProduct(int length, List<Product> products) {
        // Sorts list on product.getLength() attribute.
        products.sort(Comparator.comparing(Product::getLength));

        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            // if the products is longer than product available, the product is set to the largest in stock
            // else if the product is shorter than the shortest available product, the product is set as the smallest in stock
            // in regular cases it will chose the appropriate size product for the length
            if (p.getLength() / length >= 1 || i == products.size() - 1) {
                return p;
            }
        }
        return null;
    }

    /**
     * Calculate the roof height from given width and angle.
     *
     * @param carportWidth int
     * @param angle int
     * @return roof height as double
     */
    private static double calcRoofHeight(int carportWidth, int angle) {
        carportWidth = carportWidth * 10;   //cm to mm
        double halfWidth = carportWidth / 2;
        double radiantAngle = Math.toRadians(angle);
        return halfWidth / Math.cos(radiantAngle);
    }
}
