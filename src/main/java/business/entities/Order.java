package business.entities;

import java.sql.Timestamp;

public class Order {

    private int order_id;
    private int user_id;
    private Timestamp orderDate;
    Carport carport;
    Shed shed;

    public Order(int order_id, int user_id,Timestamp orderDate, Carport carport, Shed shed) {
        this.order_id = order_id;
        this.user_id = user_id;
        this.orderDate = orderDate;
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

    public Carport getCarport() {
        return carport;
    }


    public Shed getShed() {
        return shed;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }
}
