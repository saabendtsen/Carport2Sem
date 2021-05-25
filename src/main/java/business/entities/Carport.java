package business.entities;

public class Carport {

    private final int carport_id;
    private final int order_id;
    private double length;
    private double width;
    private Material roof;

    public Carport(int carport_id, int order_id, double length, double width, Material roof) {
        this.carport_id = carport_id;
        this.order_id = order_id;
        this.length = length;
        this.width = width;
        this.roof = roof;
    }

    public Carport(int carport_id, int order_id, double length, double width) {
        this.carport_id = carport_id;
        this.order_id = order_id;
        this.length = length;
        this.width = width;
    }

    public Material getRoof() {
        return roof;
    }

    public void setRoof(Material roof) {
        this.roof = roof;
    }

    public int getCarport_id() {
        return carport_id;
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
}
