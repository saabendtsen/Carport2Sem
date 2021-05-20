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
    private final String rectTemplateNuMedFarve = "<rect x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:#ff0000; fill: #ffffff\" />";
    private final String line = "<line x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" style=\"stroke:#000000; \" />";
    private final String dashArray = "<line x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" style=\"stroke:#000000; stroke-dasharray=\"4 1\" />";


    private final String arrowsMoreShizeTemplate =
            "<defs> \n" + "<marker \n" + "id=\"beginArrow\" \n" + "markerWidth=\"12\" \n" + "markerHeight=\"12\" \n"+ "refX=\"0\" \n" + "refY=\"6\" \n" + "orient=\"auto\"> \n" +
            "<path d=\"M0,6 L12,0 L12,12 L0,6\" style=\"fill: black;\" /> \n" + "</marker> \n" + "<marker \n" + "id=\"endArrow\" \n" + "markerWidth=\"12\" \n" +
            "markerHeight=\"12\" \n" + "refX=\"12\" \n" + "refY=\"6\" \n" + "orient=\"auto\"> \n" + "<path d=\"M0,0 L12,6 L0,12 L0,0\" style=\"fill: black;\" /> \n" + "</marker> \n" + "</defs>";

    private final String arrowTemplate = "<line x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\"\n" +
            "style=\"stroke: black; marker-start:url(#beginArrow); marker-end:url(#endArrow);\" />";

    private final String textTemplate = "<text x=\"%f\" y=\"%f\" text-anchor=\"middle\" class=\"%s\" transform=\"%s\">%s</text>";

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

    public void addSvg(business.services.SVG innerSVG) {
        svg.append(innerSVG.toString());

    }

    public void arrowsMoreShizeTemplate(){
        svg.append(arrowsMoreShizeTemplate);
    }

    public void addArrow(double x1, double y1, double x2, double y2) {
        svg.append(String.format(Locale.US, arrowTemplate,x1,y1,x2,y2));
    }

    public void addText(double x, double y, String tclass, String transform, String text) {
        svg.append(String.format(Locale.US,textTemplate, x, y, tclass, transform, text));
    }


    @Override
    public String toString() {
        return svg.toString() + "</svg>";
    }
}


