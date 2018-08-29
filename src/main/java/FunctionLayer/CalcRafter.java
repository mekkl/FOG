/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import java.util.List;

/**
 *
 * @author Mellem
 */
public class CalcRafter {
    
    static int RAFTERSPACEFLAT = 60;
    static int RAFTERLENGTHFLAT = 7200;
    static int RAFTERSPACEPITCHED = 60;
    static int RAFTERLENGTHPITCHED = 0;
    
    /**
     * Calculate the amount of Rafter of a given type, needed to fill corresponding dimensions.
     * @param length int
     * @param width int
     * @param rafter list of Product
     * @return OrderLine Object
     * @throws FogException
     */
    public static OrderLine getRafterFlatRoof(int length, int width, List<Product> rafter) throws FogException {
        Product raft = null;
        
        for (int i = 0; i < rafter.size(); i++) {
            if (rafter.get(i).getLength() == RAFTERLENGTHFLAT) raft = rafter.get(i);
        }

        int qty = length / RAFTERSPACEFLAT;
        if (qty % RAFTERSPACEFLAT != 0) qty++;

        if (raft == null) throw new FogException( "Intet spær fundet med længde: "+RAFTERLENGTHFLAT );
        return new OrderLine(raft, length, qty, "stk.", "Spærtræ");
    }

    /**
     * Calculate the amount of Rafter of a given type, needed to fill corresponding dimensions
     * @param length int
     * @param width int 
     * @param rafter list of Product
     * @return Orderline Object
     * @throws FogException
     */
    public static OrderLine getRafterPitchedRoof(int length, int width, List<Product> rafter) throws FogException {
        Product raft = null;
        
        for (int i = 0; i < rafter.size(); i++) {
            if (rafter.get(i).getLength() == RAFTERLENGTHPITCHED) raft = rafter.get(i);
        }
        
        int qty = length / RAFTERSPACEPITCHED;
        if (qty % RAFTERSPACEPITCHED != 0) qty++;
        
        if (raft == null) throw new FogException( "Intet spær fundet med længde: "+RAFTERLENGTHPITCHED );
        return new OrderLine(raft, length, qty, "stk.", "Spær sæt");
    }
    
    public static void setRAFTERSPACEFLAT(int RAFTERSPACEFLAT) {
        CalcRafter.RAFTERSPACEFLAT = RAFTERSPACEFLAT;
    }

    public static void setRAFTERSPACEPITCHED(int RAFTERSPACEPITCHED) {
        CalcRafter.RAFTERSPACEPITCHED = RAFTERSPACEPITCHED;
    }
    
}
