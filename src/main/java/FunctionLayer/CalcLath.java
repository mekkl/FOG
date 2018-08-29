/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import java.util.List;

/**
 *
 * @author Orchi
 */
public class CalcLath {
    
    /**
     * Calculate the amount of Lath needed to fill corresponding length X width area.
     * @param length int
     * @param width int
     * @param products List of Product
     * @return Orderline Object
     */
    public static OrderLine calculateRegularLath(int length, int width, List<Product> products) {
        int quantity = 0;
        
        // bredden skal fodres til denne metode fra en anden algoritme metode.
        Product p = Calculator.getCorrectLengthProduct(length, products);
        // this is the length for each row of lath
        // lægtelængden
        //øverste taglægte placeres 30mm fra spidsen af spæret og herefter skal der væres 307mm til næste lægte.
        width -= 30;
        //int teglafstand
        // get lægte product from db
        //nederste taglægte placeres nederst på spærhoved og næste lægte placeres 350 mm fra den.
        width -= 350;
        quantity += 2;
        //resterende lægter tilføjes
        quantity += width / 307;
        //1 tilføjes til tilpasning
        // hvis længden på carport er mindre end 5400 mm, hva så?? bare giv dem en hel 5400mm til hver lægte?? nemmere for savning
        
        //if more than one lath is required for each row -> we calculate how many is required per row except the 'rest' which we deal with next 
        if(length / p.getLength() >= 2)
            quantity *= length / p.getLength();
        
        if (length % p.getLength() > 0 && length > p.getLength()) {
            // finds the full length of the remaining pieces, and then how many of the product there is need for to cover that length
            int extraPostsIfInsuficcientLength = ((length % p.getLength()) * quantity) / p.getLength();
            // checks if there still is a piece remaining after, and adds one additional product
            if (((length % p.getLength()) * quantity) % p.getLength() > 0) {
                extraPostsIfInsuficcientLength++;
            }
            quantity += extraPostsIfInsuficcientLength;
        }
        
        quantity *= 2; // for both sides
        
        return new OrderLine(p, length, quantity, "stk", "Til montering på spær");
    }
    
    /**
     *Calculate the amount of Lath of a given type, needed to fill corresponding length X width area.
     * @param length int
     * @param width int
     * @param products List of Product
     * @return Orderline Object.
     */
    public static OrderLine calculateTopLath(int length, int width, List<Product> products) {
        int quantity = 1;
        Product p = Calculator.getCorrectLengthProduct(length, products);
        // varierer længden alt efter længden på carport???? Eller er toplægten 420 cm som standard??
        // 2 toplægter til hver side af taget på carport
        // Længden af toplægten er kortere end normallægterne
        
        if(length / p.getLength() >= 2)
            quantity *= length / p.getLength();
        
        if (length % p.getLength() > 0 && length > p.getLength()) {
            int extraPostsIfInsuficcientLength = ((length % p.getLength()) * quantity) / p.getLength();
            if (((length % p.getLength()) * quantity) % p.getLength() > 0) {
                extraPostsIfInsuficcientLength++;
            }
            quantity += extraPostsIfInsuficcientLength;
        }
        
        return new OrderLine(p, length, quantity, "stk", "Toplægte til montering af rygsten lægges i toplægteholder");
    }
    
    /**
     * Calculate the amount of Lath of a given type, needed to fill corresponding length X width area.
     * @param length int
     * @param width int
     * @param products list of Product
     * @return OrderLine Object
     */
    public static OrderLine calculateDoorZLath(int length, int width, List<Product> products) {
        int quantity = 1;
        
        Product p = Calculator.getCorrectLengthProduct(length, products);
        
        if(length / p.getLength() >= 2)
            quantity *= length / p.getLength();
        
        if (length % p.getLength() > 0 && length > p.getLength()) {
            // finds the full length of the remaining pieces, and then how many of the product there is need for to cover that length
            int extraPostsIfInsuficcientLength = ((length % p.getLength()) * quantity) / p.getLength();
            // checks if there still is a piece remaining after, and adds one additional product
            if (((length % p.getLength()) * quantity) % p.getLength() > 0) {
                extraPostsIfInsuficcientLength++;
            }
            quantity += extraPostsIfInsuficcientLength;
        } //TODO FIX THIS SHIT
        
        // hvis dør, på skur??
        return new OrderLine(p, length, quantity, "stk", "til z på bagside af dær");
    }
    
}
