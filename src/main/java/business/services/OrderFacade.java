package business.services;

import business.entities.Order;
import business.entities.User;
import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.OrderMapper;
import business.persistence.UserMapper;

import java.util.List;

public class OrderFacade {

    OrderMapper orderMapper;

    public OrderFacade(Database database) {
        orderMapper = new OrderMapper(database);
    }


    public void createOrder(int user_id, double carportLength, double carportWidth, double shedLength, double shedWidth) throws UserException{
        orderMapper.createOrder(user_id, carportLength, carportWidth, shedLength, shedWidth);
    }

    public List<Order> getOrderByUserId(int user_id) throws UserException {
        return orderMapper.getOrderByUserId(user_id);
    }

}
