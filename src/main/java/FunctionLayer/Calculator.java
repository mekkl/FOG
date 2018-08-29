/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import DataLayer.ProductMapper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Orchi
 */
public class Calculator {

    /**
     * Get Bill of Materials for a given Inquiry Object.
     *
     * @param inquiry Inquiry object
     * @return BillOfMaterials Object
     * @throws FogException
     */
    public static BillOfMaterials getBillOfMaterials(Inquiry inquiry) throws FogException {
        BillOfMaterials bom = new BillOfMaterials();
        List<Product> products = ProductMapper.getProducts();
        int length = inquiry.getCarportLength();
        int width = inquiry.getCarportWidth();
        Product roofMaterial = getChosenProduct(inquiry.getRoofMaterial(), products);
        if (roofMaterial == null) {
            throw new FogException("Intet tag materiale fundet!");
        }

        // FLAT ROOF ALGORITHM
        if (inquiry.getRoofType().equals("fladt")) {
            //post / stolpe
            bom.addOrderLine(CalcPost.getPostsFlatRoof(length, width, inquiry.getCarportHeight(), getChosenCategory("stolpe", products)));
            //topplate / rem
            bom.addOrderLine(CalcTopPlate.getTopPlatesFlatRoof(length, width, getChosenCategory("rem", products)));
            //raft / spær
            bom.addOrderLine(CalcRafter.getRafterFlatRoof(length, width, getChosenCategory("spær", products)));

            //roof material
            if (roofMaterial.getCategory().equals("tagpap")) {
                //tarPaper / tagpap
                bom.addOrderLine(CalcTarPaper.getTarPaperFlatRoof(length, width, roofMaterial));
            } else {
                //trapeztag / trapezeRoof
                bom.addOrderLine(CalcTrapezeRoof.calculateAmountOfTrapezeRoof(length, width, roofMaterial));
            };

            //Shack
            if (inquiry.getShackLength() > 0) {
                int shackLength = inquiry.getShackLength();
                int shackWidth = inquiry.getShackWidth();
                //Shack Posts
                bom.addOrderLine(CalcShackPost.getPostsShack(length, width, inquiry.getCarportHeight(), shackLength, shackWidth, getChosenCategory("stolpe", products)));
                //Shack Transom
                bom.addOrderLine(CalcShackTransom.getTransomsForShackLength(shackLength, getChosenCategory("løsholt", products)));
                bom.addOrderLine(CalcShackTransom.getTransomsForShackWidth(shackWidth, getChosenCategory("løsholt", products)));
                //Shack Cladding
                bom.addOrderLine(CalcShackCladding.getCladdingForShackFlatRoof(shackLength, shackWidth, inquiry.getCarportHeight(), getChosenCategory("beklædning", products)));
                //Shack Door
                bom.addOrderLine(CalcLath.calculateDoorZLath(length, width, getChosenCategory("beklædning", products)));
            }
            // PITCHED ROOF ALHORITHM
        } else {
            //post / stolpe
            bom.addOrderLine(CalcPost.getPostsPitchedRoof(length, width, inquiry.getCarportHeight(), getChosenCategory("stolpe", products)));
            //topplate / rem
            bom.addOrderLine(CalcTopPlate.getTopPlatesPitchedRoof(length, width, getChosenCategory("rem", products)));
            //raft / spær
            bom.addOrderLine(CalcRafter.getRafterPitchedRoof(length, width, getChosenCategory("spær", products)));
            //lath / lægte
            bom.addOrderLine(CalcLath.calculateRegularLath(length, (int) calcRoofWidth(width, Integer.parseInt(inquiry.getAngle())), getChosenCategory("lægte", products)));
            bom.addOrderLine(CalcLath.calculateTopLath(length, width, getChosenCategory("lægte", products)));

            //roof material
            if (roofMaterial.getCategory().equals("tagpap")) {
                //tarPaper / tagpap
                bom.addOrderLine(CalcTarPaper.getTarPaperFlatRoof(length, width, roofMaterial));
            } else {
                //Bricks/rooftiles /tagsten
                bom.addOrderLine(CalcBricks.calculateAmountOfBricks(length, (int) calcRoofWidth(width, Integer.parseInt(inquiry.getAngle())), roofMaterial));
            }

            //Shack
            if (inquiry.getShackLength() > 0) {
                int shackLength = inquiry.getShackLength();
                int shackWidth = inquiry.getShackWidth();
                //Shack Posts
                bom.addOrderLine(CalcShackPost.getPostsShack(length, width, inquiry.getCarportHeight(), shackLength, shackWidth, getChosenCategory("stolpe", products)));
                //Shack Transom
                bom.addOrderLine(CalcShackTransom.getTransomsForShackLength(shackLength, getChosenCategory("løsholt", products)));
                bom.addOrderLine(CalcShackTransom.getTransomsForShackWidth(shackWidth, getChosenCategory("løsholt", products)));
                //Shack Cladding
                bom.addOrderLine(CalcShackCladding.getCladdingForShackPitchedRoofGable(width, inquiry.getCarportHeight(), shackWidth, Integer.parseInt(inquiry.getAngle()), getChosenCategory("beklædning", products)));
                bom.addOrderLine(CalcShackCladding.getCladdingForShackPitchedRoofSide(width, inquiry.getCarportHeight(), shackLength, Integer.parseInt(inquiry.getAngle()), getChosenCategory("beklædning", products)));
                //Shack Door
                bom.addOrderLine(CalcLath.calculateDoorZLath(length, width, getChosenCategory("beklædning", products)));
            }
        }
        return bom;
    }

    /**
     * Get Product from list of Product by its String name
     *
     * @param productName String
     * @param products list of Product
     * @return Product
     */
    private static Product getChosenProduct(String productName, List<Product> products) {
        Product product = null;
        for (Product p : products) {
            if (p.getName().equalsIgnoreCase(productName)) {
                product = p;
            }
        }
        return product;
    }

    /**
     * get list of Product by given String catergory name
     *
     * @param category String
     * @param products list of Product
     * @return List of Product object
     */
    public static List<Product> getChosenCategory(String category, List<Product> products) {
        List<Product> chosenCategoryList = new ArrayList<>();
        for (Product p : products) {
            if (p.getCategory().equalsIgnoreCase(category)) {
                chosenCategoryList.add(p);
            }
        }
        return chosenCategoryList;
    }

    /**
     * Calculate the roof width from given width and angle.
     *
     * @param carportWidth int
     * @param angle int
     * @return roofWidth as double
     */
    private static double calcRoofWidth(int carportWidth, int angle) {
        double halfWidth = carportWidth / 2;
        double radiantAngle = Math.toRadians(angle);
        double carportHeight = halfWidth / Math.cos(radiantAngle);
        carportWidth += 10;
        return calcHypotenuse(carportWidth, carportHeight);
    }

    /**
     * * Calculate the size of the roof hypothenus from given dimensions
     *
     * @param a doubld
     * @param b double
     * @return hypothenus as double
     */
    private static double calcHypotenuse(double a, double b) {
        double aPow = Math.pow(a, 2);
        double bPow = Math.pow(b, 2);
        return Math.sqrt(aPow + bPow);
    }

    /**
     * Support method - Calculate the most correct Product from given dimension.
     *
     * @param length int
     * @param products list of Product
     * @return Product
     */
    public static Product getCorrectLengthProduct(int length, List<Product> products) {
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
