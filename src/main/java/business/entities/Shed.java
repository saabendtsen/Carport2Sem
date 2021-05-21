package business.entities;

public class Shed {

    private final int shed_id;
    private final int order_id;
    private double length;
    private double width;
    private Material clothing;

    public Shed(int shed_id, int order_id, double length, double width, Material clothing) {
        this.shed_id = shed_id;
        this.order_id = order_id;
        this.length = length;
        this.width = width;
        this.clothing = clothing;
    }

    public Shed(int shed_id, int order_id, double length, double width) {
        this.shed_id = shed_id;
        this.order_id = order_id;
        this.length = length;
        this.width = width;
    }


    public int getShed_id() {
        return shed_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public Material getClothing() {
        return clothing;
    }

    public void setClothing(Material clothing) {
        this.clothing = clothing;
    }
}
