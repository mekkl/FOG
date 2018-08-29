/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

/**
 * The purpose of the SVGFromSide class is to generate a string of SVG html
 * code, by appending various sub methods returned Strings to a Stringbuilder.
 *
 * @author Orchi
 */
public class SVGFromSide {

    private StringBuilder svg = new StringBuilder();

    private Inquiry inquiry;
    private int gap;
    private int gapFromFrontToFirstPost;
    private int roofHeight;

    /**
     * If rooftype of "fladt" type, generate SVG for flat roof, if not generate
     * SVG for Pitched roof.
     *
     * @param inquiry
     */
    public SVGFromSide(Inquiry inquiry) {
        this.inquiry = inquiry;
        gap = 40;

        if (inquiry.getRoofType().equals("fladt")) {
            gapFromFrontToFirstPost = 100;
            roofHeight = 0;
            makeFlatRoofCarport();
        } else {
            gapFromFrontToFirstPost = 80;
            roofHeight = 80;
            makePitchedRoofCarport();
        }
    }

    public StringBuilder getSVG() {
        return svg;
    }

    private void makeFlatRoofCarport() {
        svg.append("<SVG width='50%' viewbox='0 0 ").append(inquiry.getCarportLength() + gap + gap).append(" ").append(inquiry.getCarportHeight() + gap + gap).append("'>");
        makePosts();
        makeTopPlate();
        makeEaves();
        if (inquiry.isWithShack()) {
            makeShack();
            makeSidePanels();
        }
        makeLineMeasurements();
        svg.append("</SVG>");
    }

    private void makePitchedRoofCarport() {
        svg.append("<SVG width='50%' viewbox='0 0 ").append(inquiry.getCarportLength() + gap + gap).append(" ").append(inquiry.getCarportHeight() + roofHeight + gap + gap).append("'>");
        makePosts();
        makeTopPlate();
        makeEaves();
        if (inquiry.isWithShack()) {
            makeShack();
            makeSidePanels();
        }
        makeLineMeasurements();
        makeRoofArea();
        makeRoofTiles();
        makeTopLath();
        makeSoffits();
        svg.append("</SVG>");
    }

    public void makePosts() {
        svg.append("<rect x='").append(gapFromFrontToFirstPost + gap).append("' y=").append(gap + roofHeight).append(" height=").append(inquiry.getCarportHeight()).append(" width='10' stroke-width='2' stroke='black' fill='#000000'/>");
        svg.append("<rect x='").append(inquiry.getCarportLength() + gap - 30 - 10).append("' y=").append(gap + roofHeight).append(" height=").append(inquiry.getCarportHeight()).append(" width='10' stroke-width='2' stroke='black' fill='#000000'/>");
        if (inquiry.getCarportLength() > 620) {
            int first = gapFromFrontToFirstPost + gap;
            int second = inquiry.getCarportLength() + gap - 30;
            int res = second - first;
            res /= 2;
            svg.append("<rect x='").append(res + gap + gapFromFrontToFirstPost).append("' y=").append(gap + roofHeight).append(" height=").append(inquiry.getCarportHeight()).append(" width='10' stroke-width='2' stroke='black' fill='#000000'/>");
        }
    }

    public void makeTopPlate() {
        svg.append("<rect x='").append(gap).append("' y=").append(gap + roofHeight).append(" height='10'").append(" width='").append(inquiry.getCarportLength()).append("' stroke-width='2' stroke='black' fill='#000000'/>");
    }

    public void makeEaves() {
        svg.append("<rect x='").append(gap + 15).append("' y=").append(gap + 13 + roofHeight).append(" height='10'").append(" width='").append(inquiry.getCarportLength() - inquiry.getShackWidth() - 15).append("' stroke-width='2' stroke='black' fill='#000000'/>");
    }

    public void makeShack() {
        svg.append("<rect x='").append(gap + inquiry.getCarportLength() - inquiry.getShackWidth() - 30).append("' y=").append(gap + 13 + roofHeight).append(" height='").append(inquiry.getCarportHeight() - 13).append("' width='").append(inquiry.getShackWidth()).append("' stroke-width='2' stroke='black' fill='#a0a2a5'/>");
    }

    public void makeSidePanels() {
        boolean oneMore = true;
        int xValue = gap + inquiry.getCarportLength() - inquiry.getShackWidth() - 30;
        while (oneMore) {
            svg.append("<rect x='").append(xValue).append("' y='").append(gap + 13 + roofHeight).append("' height='").append(inquiry.getCarportHeight() - 13).append("' width='10'").append("' stroke-width='2' stroke='black' fill='#000000'/>");
            xValue += 15;
            if (xValue + 10 >= gap + inquiry.getCarportLength() - 30) {
                oneMore = false;
            }
        }
    }

    public void makeRoofArea() {
        svg.append("<rect x='").append(gap - 3).append("' y='").append(gap).append("' height='").append(roofHeight).append("' width='").append(inquiry.getCarportLength() + 5).append("' stroke-width='2' stroke='black' fill='#ffffff'/>");
    }

    public void makeTopLath() {
        svg.append("<rect x='").append(gap - 3).append("' y='").append(gap).append("' height='").append(10).append("' width='").append(inquiry.getCarportLength() + 5).append("' stroke-width='2' stroke='black' fill='#ffffff'/>");
    }

    public void makeSoffits() {
        svg.append("<rect x='").append(gap - 3).append("' y='").append(gap - 3).append("' height='").append(roofHeight - 5).append("' width='").append(10).append("' stroke-width='2' stroke='black' fill='#ffffff'/>");
        svg.append("<rect x='").append(gap + inquiry.getCarportLength() + 5 - 13).append("' y='").append(gap - 3).append("' height='").append(roofHeight - 5).append("' width='").append(10).append("' stroke-width='2' stroke='black' fill='#ffffff'/>");
    }

    public void makeRoofTiles() {
        boolean oneMore = true;
        int xValue = gap + 10 - 3;
        while (oneMore) {
            svg.append("<rect x='").append(xValue).append("' y='").append(gap + 3).append("' height='").append(roofHeight - 5).append("' width='").append(10).append("' stroke-width='2' stroke='black' fill='#ffffff'/>");
            xValue += 10;
            if (xValue + 10 > gap + inquiry.getCarportLength() + 2 - 10) {
                oneMore = false;
            }
        }
    }

    public void makeLineMeasurements() {
        svg.append("<line x1='30' y1=").append(gap + 13 + roofHeight).append(" x2='30' y2=").append(gap + inquiry.getCarportHeight() + roofHeight).append(" style='stroke:rgb(0,0,0);stroke-width:2'/>");
        svg.append("<text x='25' y=").append(inquiry.getCarportHeight() / 2 + gap + roofHeight).append(" style='writing-mode: tb;' fill='black'>").append(inquiry.getCarportHeight() - 13).append("</text>"); // height minus Eave

        svg.append("<line x1=").append(gap + inquiry.getCarportLength() + 20).append(" y1=").append(gap).append(" x2=").append(gap + inquiry.getCarportLength() + 20).append(" y2=").append(inquiry.getCarportHeight() + gap + roofHeight).append(" style='stroke:rgb(0,0,0);stroke-width:2'/>");
        if (inquiry.getRoofType().equals("fladt")) {
            svg.append("<text x=").append(gap + inquiry.getCarportLength() + 20 - 10).append(" y=").append(((roofHeight + inquiry.getCarportHeight()) / 2) + gap).append(" style='writing-mode: tb;' fill='black'>").append(inquiry.getCarportHeight() + roofHeight - 10).append("</text>"); // end of carport
        } else {
            svg.append("<text x=").append(gap + inquiry.getCarportLength() + 20 - 10).append(" y=").append(((roofHeight + inquiry.getCarportHeight()) / 2) + gap).append(" style='writing-mode: tb;' fill='black'>").append(inquiry.getCarportHeight() + roofHeight).append("</text>"); // end of carport
        }
        svg.append("<line x1='10' y1=").append(gap).append(" x2='10' y2=").append(inquiry.getCarportHeight() + gap + roofHeight).append(" style='stroke:rgb(0,0,0);stroke-width:2'/>");
        svg.append("<text x='5' y=").append(inquiry.getCarportHeight() / 2 + gap + roofHeight).append(" style='writing-mode: tb;' fill='black'>").append(inquiry.getCarportHeight() + roofHeight).append("</text>"); // height of carport

        svg.append("<line x1=").append(gap).append(" y1=").append(inquiry.getCarportHeight() + gap + 20 + roofHeight).append(" x2=").append(gapFromFrontToFirstPost + gap).append(" y2=").append(inquiry.getCarportHeight() + gap + 20 + roofHeight).append(" style='stroke:rgb(0,0,0);stroke-width:2'/>"); // 1 meter
        svg.append("<text x=").append(gap + (gapFromFrontToFirstPost / 2)).append(" y=").append(inquiry.getCarportHeight() + gap + 15 + roofHeight).append(">").append(gapFromFrontToFirstPost).append("</text>");
        svg.append("<line x1=").append(gap).append(" y1=").append(inquiry.getCarportHeight() + gap + 5 + roofHeight).append(" x2=").append(gap).append(" y2=").append(inquiry.getCarportHeight() + gap + 30 + roofHeight).append(" style='stroke:rgb(0,0,0);stroke-width:2'/>");
        svg.append("<line x1=").append(gapFromFrontToFirstPost + gap).append(" y1=").append(inquiry.getCarportHeight() + gap + 5 + roofHeight).append(" x2=").append(gapFromFrontToFirstPost + gap).append(" y2=").append(inquiry.getCarportHeight() + gap + 30 + roofHeight).append(" style='stroke:rgb(0,0,0);stroke-width:2'/>");

        if (inquiry.isWithShack()) {
            int half = inquiry.getShackWidth() / 2;
            svg.append("<line x1=").append(gap + inquiry.getCarportLength() - inquiry.getShackWidth() - 30).append(" y1=").append(inquiry.getCarportHeight() + gap + 20 + roofHeight).append(" x2=").append(gap + inquiry.getCarportLength() - 30).append(" y2=").append(inquiry.getCarportHeight() + gap + 20 + roofHeight).append(" style='stroke:rgb(0,0,0);stroke-width:2'/>");
            svg.append("<line x1=").append(gap + inquiry.getCarportLength() - 30).append(" y1=").append(inquiry.getCarportHeight() + gap + 5 + roofHeight).append(" x2=").append(gap + inquiry.getCarportLength() - 30).append(" y2=").append(inquiry.getCarportHeight() + gap + 30 + roofHeight).append(" style='stroke:rgb(0,0,0);stroke-width:2'/>");
            svg.append("<text x=").append(gap + inquiry.getCarportLength() - half - 30).append(" y=").append(inquiry.getCarportHeight() + gap + 15 + roofHeight).append(">").append(inquiry.getShackWidth()).append("</text>");
        }

        if (inquiry.getCarportLength() > 620) { // with 3 posts
            int first = gapFromFrontToFirstPost + gap;
            int second = inquiry.getCarportLength() + gap - 30;
            int centerPost = second - first;
            centerPost /= 2;
            centerPost += gapFromFrontToFirstPost + gap;

            svg.append("<line x1=").append(gapFromFrontToFirstPost + gap).append(" y1=").append(inquiry.getCarportHeight() + gap + 20 + roofHeight).append(" x2=").append(centerPost).append(" y2=").append(inquiry.getCarportHeight() + gap + 20 + roofHeight).append(" style='stroke:rgb(0,0,0);stroke-width:2'/>"); // 
            svg.append("<text x=").append((centerPost + gapFromFrontToFirstPost + gap) / 2).append(" y=").append(inquiry.getCarportHeight() + gap + 15 + roofHeight).append(">").append(centerPost - gapFromFrontToFirstPost - gap).append("</text>");
            svg.append("<line x1=").append(centerPost).append(" y1=").append(inquiry.getCarportHeight() + gap + 5 + roofHeight).append(" x2=").append(centerPost).append(" y2=").append(inquiry.getCarportHeight() + gap + 30 + roofHeight).append(" style='stroke:rgb(0,0,0);stroke-width:2'/>");

            if (inquiry.isWithShack()) { // with shack with 3 posts
                svg.append("<line x1=").append(centerPost).append(" y1=").append(inquiry.getCarportHeight() + gap + 20 + roofHeight).append(" x2=").append(gap + inquiry.getCarportLength() - inquiry.getShackWidth() - 30).append(" y2=").append(inquiry.getCarportHeight() + gap + 20 + roofHeight).append(" style='stroke:rgb(0,0,0);stroke-width:2'/>"); // 
                svg.append("<line x1=").append(gap + inquiry.getCarportLength() - inquiry.getShackWidth() - 30).append(" y1=").append(inquiry.getCarportHeight() + gap + 5 + roofHeight).append(" x2=").append(gap + inquiry.getCarportLength() - inquiry.getShackWidth() - 30).append(" y2=").append(inquiry.getCarportHeight() + gap + 30 + roofHeight).append(" style='stroke:rgb(0,0,0);stroke-width:2'/>"); // 
                svg.append("<text x=").append((gap + inquiry.getCarportLength() - inquiry.getShackWidth() - 30 + centerPost - 8) / 2).append(" y=").append(inquiry.getCarportHeight() + gap + 15 + roofHeight).append(">").append((gap + inquiry.getCarportLength() - inquiry.getShackWidth() - 30) - centerPost).append("</text>");
            } else { // with 3 posts
                svg.append("<line x1=").append(centerPost).append(" y1=").append(inquiry.getCarportHeight() + gap + 20 + roofHeight).append(" x2=").append(gap + inquiry.getCarportLength() - 30).append(" y2=").append(inquiry.getCarportHeight() + gap + 20 + roofHeight).append(" style='stroke:rgb(0,0,0);stroke-width:2'/>"); // 
                svg.append("<line x1=").append(gap + inquiry.getCarportLength() - 30).append(" y1=").append(inquiry.getCarportHeight() + gap + 5 + roofHeight).append(" x2=").append(gap + inquiry.getCarportLength() - 30).append(" y2=").append(inquiry.getCarportHeight() + gap + 30 + roofHeight).append(" style='stroke:rgb(0,0,0);stroke-width:2'/>"); // 
                svg.append("<text x=").append((gap + inquiry.getCarportLength() - 30 + centerPost - 8) / 2).append(" y=").append(inquiry.getCarportHeight() + gap + 15 + roofHeight).append(">").append((gap + inquiry.getCarportLength() - 30) - centerPost).append("</text>");
            }
        } else {
            if (!inquiry.isWithShack()) { // without shack with 2 posts
                svg.append("<line x1=").append(gapFromFrontToFirstPost + gap).append(" y1=").append(inquiry.getCarportHeight() + gap + 20 + roofHeight).append(" x2=").append(gap + inquiry.getCarportLength() - 30).append(" y2=").append(inquiry.getCarportHeight() + gap + 20 + roofHeight).append(" style='stroke:rgb(0,0,0);stroke-width:2'/>"); // 
                svg.append("<text x=").append((gap + inquiry.getCarportLength() - 30 + gapFromFrontToFirstPost + gap) / 2).append(" y=").append(inquiry.getCarportHeight() + gap + 15 + roofHeight).append(">").append((gap + inquiry.getCarportLength() - 30) - (gapFromFrontToFirstPost + gap)).append("</text>");
                svg.append("<line x1=").append(gap + inquiry.getCarportLength() - 30).append(" y1=").append(inquiry.getCarportHeight() + gap + 5 + roofHeight).append(" x2=").append(gap + inquiry.getCarportLength() - 30).append(" y2=").append(inquiry.getCarportHeight() + gap + 30 + roofHeight).append(" style='stroke:rgb(0,0,0);stroke-width:2'/>");
            } else { // with shack with 2 posts
                svg.append("<line x1=").append(gapFromFrontToFirstPost + gap).append(" y1=").append(inquiry.getCarportHeight() + gap + 20 + roofHeight).append(" x2=").append(gap + inquiry.getCarportLength() - inquiry.getShackWidth() - 30).append(" y2=").append(inquiry.getCarportHeight() + gap + 20 + roofHeight).append(" style='stroke:rgb(0,0,0);stroke-width:2'/>"); // 
                svg.append("<text x=").append((gap + inquiry.getCarportLength() - inquiry.getShackWidth() - 30 + gapFromFrontToFirstPost + gap) / 2).append(" y=").append(inquiry.getCarportHeight() + gap + 15 + roofHeight).append(">").append((gap + inquiry.getCarportLength() - 30 - inquiry.getShackWidth()) - (gapFromFrontToFirstPost + gap)).append("</text>");
                svg.append("<line x1=").append(gap + inquiry.getCarportLength() - inquiry.getShackWidth() - 30).append(" y1=").append(inquiry.getCarportHeight() + gap + 5 + roofHeight).append(" x2=").append(gap + inquiry.getCarportLength() - inquiry.getShackWidth() - 30).append(" y2=").append(inquiry.getCarportHeight() + gap + 30 + roofHeight).append(" style='stroke:rgb(0,0,0);stroke-width:2'/>");
            }
        }

        // gap at the end
        svg.append("<line x1=").append(gap + inquiry.getCarportLength() - 30).append(" y1=").append(inquiry.getCarportHeight() + gap + 20 + roofHeight).append(" x2=").append(gap + inquiry.getCarportLength()).append(" y2=").append(inquiry.getCarportHeight() + gap + 20 + roofHeight).append(" style='stroke:rgb(0,0,0);stroke-width:2'/>"); // 30 cm to the end
        svg.append("<line x1=").append(gap + inquiry.getCarportLength()).append(" y1=").append(inquiry.getCarportHeight() + gap + 5 + roofHeight).append(" x2=").append(gap + inquiry.getCarportLength()).append(" y2=").append(inquiry.getCarportHeight() + gap + 30 + roofHeight).append(" style='stroke:rgb(0,0,0);stroke-width:2'/>"); // 
        svg.append("<text x=").append(gap + inquiry.getCarportLength() - 25).append(" y=").append(inquiry.getCarportHeight() + gap + 15 + roofHeight).append(">").append(30).append("</text>");
    }
}