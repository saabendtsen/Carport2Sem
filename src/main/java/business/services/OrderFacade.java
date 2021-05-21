package business.services;

import business.entities.Order;
import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.OrderMapper;

import java.util.List;

public class OrderFacade {

    OrderMapper orderMapper;

    public OrderFacade(Database database) {
        orderMapper = new OrderMapper(database);
    }


    public int createOrder(int user_id, double carportLength, double carportWidth, double shedLength, double shedWidth) throws UserException {
        return orderMapper.createOrder(user_id, carportLength, carportWidth, shedLength, shedWidth);
    }

    public List<Order> getOrderByUserId(int user_id) throws UserException {
        return orderMapper.getOrderByUserId(user_id);
    }

    public int updateOrder(int order_id) throws UserException {
        return orderMapper.updateOrder(order_id);
    }

    public List<Order> getAllOrdersByState() throws UserException {
        return orderMapper.getAllOrdersByState();
    }

    public Order getOrderByOrderId(int order_id, int carportRoof_materialID) throws UserException {
        return orderMapper.getOrderByOrderId(order_id, carportRoof_materialID);
    }

    public Order getOrderByOrderId(int order_id) throws UserException {
        return orderMapper.getOrderByOrderId(order_id);
    }

    public void insertIntoOrderHasMaterial(Order order) throws UserException {
        orderMapper.insertIntoOrderHasMaterial(order);
    }

    public int updateOrderSale(Order order, double newPrice) throws UserException {
        return orderMapper.updateOrderSale(order, newPrice);
    }

    public int updateOrderCost(Order order, double newPrice) throws UserException {
        return orderMapper.updateOrderCost(order, newPrice);
    }
}
