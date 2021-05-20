package business.services;

import java.util.Locale;

public class SVG {

    StringBuilder svg = new StringBuilder();

    private int x;
    private int y;
    private String viewBox;
    private int width;
    private int height;

    private final String headerTemplate = "<svg height=\"%d%%\" " +
            "width=\"%d%%\" " +
            "viewBox=\"%s\" " +
            "x=\"%d\"   " +
            "y=\"%d\"   " +
            " preserveAspectRatio=\"xMinYMin\">";

    private final String rectTemplate = "<rect x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: #ffffff\" />";
    final String rectTemplateNuMedFarve = "<rect x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke-width:2; stroke:#000000; fill: #ffffff\" />";
    private final String line = "<line x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" style=\"stroke:#000000;  />";
    private final String dashArray = "<line x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" style=\"stroke:#000000 stroke-dasharray=\"4 1\" />";


    public SVG(int x, int y, String viewBox, int width, int height) {
        this.x = x;
        this.y = y;
        this.viewBox = viewBox;
        this.width = width;
        this.height = height;
        svg.append(String.format(headerTemplate, height, width, viewBox, x, y));
    }

    public void addRect(double x, double y, double height, double width) {
        svg.append(String.format(Locale.US,rectTemplate, x, y, height, width));
    }

    public void addRectColor(double x, double y, double height, double width) {
        svg.append(String.format(Locale.US,rectTemplateNuMedFarve, x, y, height, width));
    }


    public void addLine(double x1,double y1,double x2, double y2,boolean dasharray){
        if(dasharray){
            svg.append(String.format(dashArray,x1,y1,x2,y2));
        } else {
            svg.append(String.format(line,x1,y1,x2,y2));

        }
    }

    //todo: lave Arrow template
    public void addArrow(){

    }

    //todo: lave Text template
    public void addText(){

    }


    public void addSvg(business.services.SVG innerSVG) {
        svg.append(innerSVG.toString());

    }


    @Override
    public String toString() {
        return svg.toString() + "</svg>";
    }
}


