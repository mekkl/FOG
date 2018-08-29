/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

/**
 * The purpose of the SVGFromTop class is to generate a string of SVG html code, by appending various sub methods returned Strings to a Stringbuilder. 
 * @author Orchi
 */
public class SVGFromTop {
    private StringBuilder svg = new StringBuilder();
    private Inquiry inquiry;
    private int gap;
    private int gapFromEdgePosts;
    private int gapFromEdge;
    
    public SVGFromTop(Inquiry inquiry) {
        this.inquiry = inquiry;
        gap = 40;
        
        if(this.inquiry.getRoofType().equals("fladt")) {
            gapFromEdgePosts = 27;
            gapFromEdge = 30;
            makeFlatRoofCarport();
        } else {
            gapFromEdgePosts = 17;
            gapFromEdge = 20;
            makePitchedRoofCarport();
        }
    }
    
    public StringBuilder getSVG() {
        return svg;
    }
    
    public int getMaxShackLength() {
        return inquiry.getCarportWidth()- (gapFromEdge * 2);
    }
    
    public double minusRight() {
        double widthC = this.inquiry.getCarportWidth();
        double shackL = this.inquiry.getCarportWidth();
        double res = shackL / widthC;
        return gapFromEdge * res;
    }

    private void makeFlatRoofCarport() {
        svg.append("<SVG width='50%' viewbox='0 0 ").append(inquiry.getCarportLength()+gap).append(" ").append(inquiry.getCarportWidth()+gap).append("'>");
        generateSVGForLineMeasurements();
        generateSVGForCarportArea();
        if(inquiry.isWithShack())
            generateSVGForShack();
        if(inquiry.isWithShack())
            generateSVGForShackPosts();
        generateSVGForEndRafters();
        generateSVGForTopPlate();
        generateSVGForPosts();
        generateSVGForRafters();
        generateSVGForCross();
        svg.append("</SVG>");
    }

    private void makePitchedRoofCarport() {
        svg.append("<SVG width='50%' viewbox='0 0 ").append(inquiry.getCarportLength()+gap).append(" ").append(inquiry.getCarportWidth()+gap).append("'>");
        generateSVGForLineMeasurements();
        generateSVGForCarportArea();
        if(inquiry.isWithShack())
            generateSVGForShack();
        if(inquiry.isWithShack())
            generateSVGForShackPosts();
        generateSVGForPosts();
        generateSVGForTopPlate();
        generateSVGForRafters();
        generateSVGForLaths();
        generateSVGForTopLath();
        generateSVGForSoffits();
        generateSVGForEndRafterCombiningLines();
        svg.append("</SVG>");
    }
    
    private void generateSVGForTopLath() {
        int middle = inquiry.getCarportWidth() / 2;
            svg.append("<rect x=").append(gap).append(" y='").append(middle+gap-5+2).append("' height='6' width='").append(inquiry.getCarportLength()).append("' stroke-width='2' stroke='black' fill='#cece9f'/>");
    }
    
    private void generateSVGForLaths() {
          int spaceBetweenLaths = 30;
          int half = inquiry.getCarportWidth() / 2 + gap;
          boolean oneMore = true;
          int yValue = 80;
          while(oneMore) {
              svg.append("<rect x=").append(gap).append(" y='").append(+yValue).append("' height='6' width='").append(inquiry.getCarportLength()).append("' stroke-width='2' stroke='black' fill='#cece9f'/>");
              yValue += spaceBetweenLaths;
              if(yValue > half - spaceBetweenLaths)
                  oneMore = false;
          }
          yValue -= 30 - 6; // the value gets incremented in the last loop even though oneMore is set to false; - 6 for width
          int difference = half - yValue; //staringpoint for second side of roof
          yValue += difference * 2;
          oneMore = true;
          while(oneMore) {
              svg.append("<rect x=").append(gap).append(" y='").append(+yValue).append("' height='6' width='").append(inquiry.getCarportLength()).append("' stroke-width='2' stroke='black' fill='#cece9f'/>");
              yValue += spaceBetweenLaths;
              if(yValue > (inquiry.getCarportWidth()+gap) - 40)
                  oneMore = false;
          }
    }
    
    private void generateSVGForEndRafterCombiningLines() {
        svg.append("<line x1=").append(gap).append(" y1=").append(inquiry.getCarportWidth()/2+gap).append(" x2=").append(gap+6).append(" y2=").append(inquiry.getCarportWidth()/2+gap).append(" style='stroke:rgb(0,0,0);stroke-width:5'/>");
        svg.append("<line x1=").append(inquiry.getCarportLength()-6+gap).append(" y1=").append(inquiry.getCarportWidth()/2+gap).append(" x2=").append(inquiry.getCarportLength()+gap).append(" y2=").append(inquiry.getCarportWidth()/2+gap).append(" style='stroke:rgb(0,0,0);stroke-width:5'/>");
    }
    
    private void generateSVGForEndRafters() {
        svg.append("<rect x=").append(gap).append(" y='").append(gap).append("' height=").append(inquiry.getCarportWidth()).append(" width='10' stroke-width='2' stroke='black' fill='#cece9f'/>");
        svg.append("<rect x='").append(inquiry.getCarportLength()-10+gap).append("' y=").append(gap).append(" height=").append(inquiry.getCarportWidth()).append(" width='10' stroke-width='2' stroke='black' fill='#cece9f'/>");
    }
    
    private void generateSVGForSoffits() {
        svg.append("<rect x=").append(gap).append(" y='").append(gap).append("' height=").append(inquiry.getCarportWidth()).append(" width='6' stroke-width='2' stroke='black' fill='#cece9f'/>");
        svg.append("<rect x='").append(inquiry.getCarportLength()-6+gap).append("' y=").append(gap).append(" height=").append(inquiry.getCarportWidth()).append(" width='6' stroke-width='2' stroke='black' fill='#cece9f'/>");
    }
    
    private void generateSVGForTopPlate() {
        int topPlateDifference = 0;
        if(inquiry.getRoofType().equals("rejsning"))
            topPlateDifference = 30;
        svg.append("<rect x=").append(topPlateDifference+gap).append(" y='").append(gapFromEdge+gap).append("' height='10' width='").append(inquiry.getCarportLength()-(topPlateDifference*2)).append("' stroke-width='2' stroke='black' fill='#cece9f'/>");
        svg.append("<rect x=").append(topPlateDifference+gap).append(" y='").append(inquiry.getCarportWidth()-gapFromEdge+gap-10).append("' height='10' width='").append(inquiry.getCarportLength()-(topPlateDifference*2)).append("' stroke-width='2' stroke='black' fill='#cece9f'/>");
        if(inquiry.getCarportWidth() > 600) {
            int middle = inquiry.getCarportWidth() / 2;
            svg.append("<rect x=").append(topPlateDifference+gap).append(" y='").append(middle+gap-5).append("' height='10' width='").append(inquiry.getCarportLength()-(topPlateDifference*2)).append("' stroke-width='2' stroke='black' fill='#cece9f'/>");
        }
    }
    
    private void generateSVGForCarportArea() {
        svg.append("<rect x=").append(gap).append(" y='").append(gap).append("' height=").append(inquiry.getCarportWidth()).append(" width=").append(inquiry.getCarportLength()).append(" stroke='black' fill='#cece9f'/>");
    }
    
    private void generateSVGForShack() {
        svg.append("<rect x='").append(inquiry.getCarportLength()-inquiry.getShackWidth()-30+gap).append("' y='").append(gapFromEdge+gap).append("' height='").append(inquiry.getShackLength()-(minusRight()*2)).append("' width='").append(inquiry.getShackWidth()).append("' stroke-width='2' stroke='black' fill='#ffe100'/>");
    }
    
    private void generateSVGForShackPosts() {
        svg.append("<rect x='").append(inquiry.getCarportLength()-inquiry.getShackWidth()-30+gap).append("' y='").append(gapFromEdgePosts+gap).append("' height='").append(16).append("' width='").append(16).append("' stroke-width='2' stroke='black' fill='#ffe100'/>");
        if(inquiry.getShackLength() ==  inquiry.getCarportWidth()) 
            svg.append("<rect x='").append(inquiry.getCarportLength()-inquiry.getShackWidth()-30+gap).append("' y='").append(inquiry.getCarportWidth()-gapFromEdgePosts-5+gap-10).append("' height='16' width='16' stroke-width='2' stroke='black' fill='#cece9f'/>");
        else {
            svg.append("<rect x='").append(inquiry.getCarportLength()-inquiry.getShackWidth()-30+gap).append("' y='").append(inquiry.getShackLength()+gapFromEdge+gap-(minusRight()*2)-8).append("' height='16' width='16' stroke-width='2' stroke='black' fill='#cece9f'/>");
            svg.append("<rect x='").append(inquiry.getCarportLength()+gap-30-16).append("' y='").append(inquiry.getShackLength()+gapFromEdge+gap-(minusRight()*2)-8).append("' height='16' width='16' stroke-width='2' stroke='black' fill='#cece9f'/>");
        }
        if(inquiry.getCarportWidth() > 600 && inquiry.getShackLength() > inquiry.getCarportWidth() / 2) {
            int yValue = inquiry.getCarportWidth() / 2;
            yValue += gap - 8;
            svg.append("<rect x='").append(inquiry.getCarportLength()-inquiry.getShackWidth()-30+gap).append("' y='").append(yValue).append("' height='16' width='16' stroke-width='2' stroke='black' fill='#cece9f'/>");
        }
    }
    
    private void generateSVGForRafters() { 
        
        boolean oneMoreNeeded = true;
        int xValue = 60;
        int amountOfRafters;
        int newXValue = 60; // sets default to 60 if the space between the raftes fits to 60cm.
        if(inquiry.getCarportLength() % 60 > 0) {
            amountOfRafters = inquiry.getCarportLength() / 60;
            amountOfRafters++; // amount of rafters needed
            xValue = inquiry.getCarportLength() / amountOfRafters;// calculates the space between each raft
            newXValue = xValue; // Assigns the new xValue to another variable, since the variable xValue 
            //gets incremented in the loop, and the space between 
            //each raft gets added to xValue each loop -> therefore xValue += newXValue;
        }
        
        while(oneMoreNeeded) {
            svg.append("<rect x='").append(xValue+gap).append("' y='").append(gap).append("' height='").append(inquiry.getCarportWidth()-gapFromEdge+gap).append("' width='10' stroke-width='2' stroke='black' fill='#cece9f'/>");
            xValue += newXValue;
            if(xValue > inquiry.getCarportLength() - newXValue + 15) // - newXValue because we don't want to place the last raft as an 'end raft'. plus 15 to allow for a not exact fit.
                oneMoreNeeded = false;
        }
    }
    
    private void generateSVGForCross() {
        if (inquiry.isWithShack()) {
            svg.append("<line x1=").append(55+gap).append(" y1='").append(gapFromEdge+gap).append("' x2='").append(inquiry.getCarportLength()-inquiry.getShackWidth()-30+gap).append("' y2='").append(inquiry.getCarportWidth()-gapFromEdge+gap).append("' style='stroke:rgb(255,255,255);stroke-width:5'<path stroke-dasharray='5,5' d='M5 20 1215 0'/>/>");
            svg.append("<line x1='").append(inquiry.getCarportLength()-inquiry.getShackWidth()-30+gap).append("' y1='").append(gapFromEdge+gap).append("' x2=").append(55+gap).append(" y2='").append(inquiry.getCarportWidth()-gapFromEdge+gap).append("' style='stroke:rgb(255,255,255);stroke-width:5'<path stroke-dasharray='5,5' d='M5 20 1215 0'/>/>");
        } else {
            svg.append("<line x1=").append(55+gap).append(" y1='").append(gapFromEdge+gap).append("' x2='").append(inquiry.getCarportLength()-30+gap).append("' y2='").append(inquiry.getCarportWidth()-gapFromEdge+gap).append("' style='stroke:rgb(255,255,255);stroke-width:5'<path stroke-dasharray='5,5' d='M5 20 1215 0'/>/>");
            svg.append("<line x1='").append(inquiry.getCarportLength()-30+gap).append("' y1='").append(gapFromEdge+gap).append("' x2=").append(55+gap).append(" y2='").append(inquiry.getCarportWidth()-gapFromEdge+gap).append("' style='stroke:rgb(255,255,255);stroke-width:5'<path stroke-dasharray='5,5' d='M5 20 1215 0'/>/>");
        }
    }
    
    private void generateSVGForLineMeasurements() {
            svg.append("<text x='25' y=").append(inquiry.getCarportWidth()/2+gap).append(" style='writing-mode: tb;' fill='black'>").append(inquiry.getCarportWidth()-(gapFromEdge*2)).append("</text>");
            svg.append("<text x='5' y=").append(inquiry.getCarportWidth()/2+gap).append(" style='writing-mode: tb;' fill='black'>").append(inquiry.getCarportWidth()).append("</text>");
            svg.append("<line x1='30' y1=").append(gap+gapFromEdge).append(" x2='30' y2=").append(inquiry.getCarportWidth()+gap-gapFromEdge).append(" style='stroke:rgb(0,0,0);stroke-width:2'/>");
            svg.append("<line x1='10' y1=").append(gap).append(" x2='10' y2=").append(inquiry.getCarportWidth()+gap).append(" style='stroke:rgb(0,0,0);stroke-width:2'/>");
            
            if(!inquiry.isWithShack()) {
                svg.append("<line x1=").append(gap).append(" y1='30' x2='").append(inquiry.getCarportLength()+gap).append("' y2='30' style='stroke:rgb(0,0,0);stroke-width:2'/>");
                svg.append("<text x=").append(inquiry.getCarportLength()/2+gap).append(" y='15' fill='black'>").append(inquiry.getCarportLength()).append("</text>");
            }
            else {
                svg.append("<text x=").append((gap+inquiry.getCarportLength()-inquiry.getShackWidth()-30+gap)/2).append(" y='15' fill='black'>").append(inquiry.getCarportLength()-inquiry.getShackWidth()-30).append("</text>");
                svg.append("<text x=").append(gap+inquiry.getCarportLength()-(inquiry.getShackWidth()/2)-30).append(" y='15' fill='black'>").append(inquiry.getShackWidth()).append("</text>");
                svg.append("<line x1=").append(gap+inquiry.getCarportLength()-inquiry.getShackWidth()-30).append(" y1='30' x2='").append(inquiry.getCarportLength()+gap-30).append("' y2='30' style='stroke:rgb(0,0,0);stroke-width:2'/>");
                svg.append("<line x1=").append(gap).append(" y1='30' x2='").append(inquiry.getCarportLength()+gap-inquiry.getShackWidth()-30).append("' y2='30' style='stroke:rgb(0,0,0);stroke-width:2'/>");
                svg.append("<line x1=").append(gap+inquiry.getCarportLength()-inquiry.getShackWidth()-30).append(" y1='10' x2='").append(inquiry.getCarportLength()+gap-inquiry.getShackWidth()-30).append("' y2='35' style='stroke:rgb(0,0,0);stroke-width:2'/>");
            }
    }
    
    private void generateSVGForPosts() {
        svg.append("<rect x='").append(100+gap).append("' y='").append(gapFromEdgePosts+gap).append("' height='16' width='16' stroke-width='2' stroke='black' fill='#cece9f'/>");
        svg.append("<rect x='").append(100+gap).append("' y='").append(inquiry.getCarportWidth()-gapFromEdgePosts-5+gap-10).append("' height='16' width='16' stroke-width='2' stroke='black' fill='#cece9f'/>");
        svg.append("<rect x='").append(inquiry.getCarportLength()+gap-30-16).append("' y='").append(gapFromEdgePosts+gap).append("' height='16' width='16' stroke-width='2' stroke='black' fill='#cece9f'/>");
        svg.append("<rect x='").append(inquiry.getCarportLength()+gap-30-16).append("' y='").append(inquiry.getCarportWidth()-gapFromEdgePosts-5+gap-10).append("' height='16' width='16' stroke-width='2' stroke='black' fill='#cece9f'/>");
        
        if(inquiry.getCarportLength() >= 620) {
            int res = (inquiry.getCarportLength() - 130);
            res = res / 2;
            int xValue = res + gap + 100;
            svg.append("<rect x='").append(xValue-8).append("' y='").append(gapFromEdgePosts+gap).append("' height='16' width='16' stroke-width='2' stroke='black' fill='#cece9f'/>");
            svg.append("<rect x='").append(xValue-8).append("' y='").append(inquiry.getCarportWidth()-gapFromEdgePosts-5+gap-10).append("' height='16' width='16' stroke-width='2' stroke='black' fill='#cece9f'/>");
        }
        
        if(inquiry.getCarportWidth() > 600) {
            int res = (inquiry.getCarportLength() - 130);
            res = res / 2;
            int xValue = res + gap + 100;
            int yValue = inquiry.getCarportWidth() / 2;
            yValue += gap - 8;
            
            svg.append("<rect x='").append(100+gap).append("' y='").append(yValue).append("' height='16' width='16' stroke-width='2' stroke='black' fill='#cece9f'/>");
            svg.append("<rect x='").append(inquiry.getCarportLength()+gap-30-16).append("' y='").append(yValue).append("' height='16' width='16' stroke-width='2' stroke='black' fill='#cece9f'/>");
            svg.append("<rect x='").append(xValue-8).append("' y='").append(yValue).append("' height='16' width='16' stroke-width='2' stroke='black' fill='#cece9f'/>");
        }
    }
}
