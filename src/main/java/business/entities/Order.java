package business.entities;

import java.sql.Timestamp;

public class Order {

    private int order_id;
    private int user_id;
    private Timestamp orderDate;
    private int shed_id;

    public Order(int order_id, int user_id,Timestamp orderDate, int shed_id) {
        this.order_id = order_id;
        this.user_id = user_id;
        this.shed_id = shed_id;
        this.orderDate = orderDate;
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

    public int getShed_id() {
        return shed_id;
    }
}
