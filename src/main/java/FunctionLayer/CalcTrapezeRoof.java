/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

/**
 *
 * @author Alexander
 */
public class CalcTrapezeRoof {

    /**
     * Calculate the amount of TrapezRoof needed to fill corresponding area
     * @param length int
     * @param width int
     * @param trapezeRoof Product
     * @return OrderLine Object
     */
    public static OrderLine calculateAmountOfTrapezeRoof(int length, int width, Product trapezeRoof) {
        int newWidth = trapezeRoof.getWidth() - 200;
        int newLength = trapezeRoof.getLength();

        int rows = width / newWidth;
        rows++;

        int columns = length / newLength;
        columns++;

        int quantity = rows * columns;

        return new OrderLine(trapezeRoof, trapezeRoof.getLength(), quantity, "stk", "Tagplader monteres på spær");
    }
}
