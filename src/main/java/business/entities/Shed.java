package business.entities;

public class Shed {

    private int shed_id;
    private int order_id;
    private double total;
    private double length;
    private double width;

    public Shed(int shed_id, int order_id, double total, double length, double width) {
        this.shed_id = shed_id;
        this.order_id = order_id;
        this.total = total;
        this.length = length;
        this.width = width;
    }

    public int getShed_id() {
        return shed_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public double getTotal() {
        return total;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }
}
