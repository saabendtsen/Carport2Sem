package business.entities;

import java.sql.Timestamp;
import java.util.List;

public class Order {

    List<Material> stkListe;
    private final int order_id;
    private final int user_id;
    private final Timestamp orderDate;
    private boolean order_state;
    private Carport carport;
    private Shed shed;
    private double saleprice;
    private double costprice;

    public Order(int order_id, int user_id, Timestamp orderDate, boolean order_state, double saleprice, double costprice, Carport carport, Shed shed) {
        this.order_id = order_id;
        this.user_id = user_id;
        this.orderDate = orderDate;
        this.order_state = order_state;
        this.carport = carport;
        this.shed = shed;
        this.saleprice = saleprice;
        this.costprice = costprice;
    }


    public int getOrder_id() {
        return order_id;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public boolean isOrder_state() {
        return order_state;
    }

    public void setOrder_state(boolean order_state) {
        this.order_state = order_state;
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
        return costprice;
    }

    public void setCostprice(double costprice) {
        this.costprice = costprice;
    }

    public double getSaleprice() {
        return saleprice;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setSaleprice(double saleprice) {
        this.saleprice = saleprice;
    }
}
