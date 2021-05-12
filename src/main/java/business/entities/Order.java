package business.entities;

import java.sql.Timestamp;
import java.util.List;

public class Order {

    private int order_id;
    private int user_id;
    private Timestamp orderDate;
    private boolean order_state;
    private Carport carport;
    private Shed shed;
    private double costprice;
    List<Material> stkListe;

    public Order(int order_id, int user_id, Timestamp orderDate, boolean order_state, Carport carport, Shed shed) {
        this.order_id = order_id;
        this.user_id = user_id;
        this.orderDate = orderDate;
        this.order_state = order_state;
        this.carport = carport;
        this.shed = shed;
    }

    public int getOrder_id() {
        return order_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public boolean isOrder_state() {
        return order_state;
    }

    public Carport getCarport() {
        return carport;
    }

    public Shed getShed() {
        return shed;
    }

    public List<Material> getStkListe() {
        return stkListe;
    }

    public void setStkListe(List<Material> stkListe) {
        this.stkListe = stkListe;
    }

    public double getCostprice() {
        for (Material m : this.stkListe) {
            costprice += m.getCostPrice() * m.getQuantity();
        }
        return costprice;
    }
}
