/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

/**
 *
 * @author Alexander W. Hørsted-Andersen 
 */
public class CalcTarPaper {

    /**
     * 	 Calculate the amount of TarPaper of a given type, needed to fill corresponding dimensions
     * @param length int 
     * @param width int
     * @param tarPaper Product
     * @return OrderLine Object
     */
    public static OrderLine getTarPaperFlatRoof(int length, int width, Product tarPaper) {

        double newWidth = tarPaper.getWidth() - 100;  //overlay
        double newLength = tarPaper.getLength();
        double rows = width / newWidth;
        double columns = length / newLength;
        double res = rows * columns;
        int result = (int) Math.ceil(res);

        return new OrderLine(tarPaper, 0, result, "roll", "Tagpap rulle");
    }

    /**
     * Calculate the amount of TarPaper of a given type, needed to fill corresponding dimensions
     * @param length int
     * @param width int
     * @param tarPaper Product
     * @return OrderLine Object
     */
    public static OrderLine getTarPaperPitchedRoof(int length, int width, Product tarPaper) {

        double newWidth = tarPaper.getWidth() - 100;  //overlay
        double newLength = tarPaper.getLength();
        double rows = (width + 200) / newWidth;   //20cm udhæng
        double columns = length / newLength;
        double res = (rows * columns) *2;
        int result = (int) Math.ceil(res);
        return new OrderLine(tarPaper, 0, result, "roll", "Tagpap rulle");
    }


}
