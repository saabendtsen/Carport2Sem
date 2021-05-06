package business.services;

import business.entities.User;
import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.OrderMapper;
import business.persistence.UserMapper;

public class OrderFacade {

    OrderMapper orderMapper;

    public OrderFacade(Database database) {
        orderMapper = new OrderMapper(database);
    }


    public void createOrder(int user_id, double carportLength, double carportWidth, double shedLength, double shedWidth) throws UserException{
     orderMapper.createOrder(user_id, carportLength, carportWidth, shedLength, shedWidth);
    }



}
