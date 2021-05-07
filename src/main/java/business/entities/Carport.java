package business.entities;

public class Carport {

    private int carport_id;
    private int order_id;
    private double total;
    private double length;
    private double width;

    public Carport(int carport_id, int order_id, double total, double length, double width) {
        this.carport_id = carport_id;
        this.order_id = order_id;
        this.total = total;
        this.length = length;
        this.width = width;
    }

    public int getCarport_id() {
        return carport_id;
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
