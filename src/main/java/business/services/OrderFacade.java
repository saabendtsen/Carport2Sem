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


    public void insertIntoCarport(double carportLength, double carportWidth) throws UserException {
     orderMapper.insertIntoCarport(carportLength,carportWidth);
    }



}
