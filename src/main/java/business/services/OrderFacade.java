package business.services;

import business.entities.Material;
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


    public int createOrder(int user_id, double carportLength, double carportWidth, double shedLength, double shedWidth, int carportRoof_materialID, int shedClothing_materialID) throws UserException {
        return orderMapper.createOrder(user_id, carportLength, carportWidth, shedLength, shedWidth, carportRoof_materialID, shedClothing_materialID);
    }

        public List<Order> getOrderByUserId(int user_id) throws UserException {
        return orderMapper.getOrderByUserId(user_id);
    }

    public int updateOrder(int order_id) throws UserException {
        return orderMapper.updateOrder(order_id);
    }

    public int updateCarportHasMaterial(int carport_id, int material_id, int quantity) throws UserException {
        return orderMapper.updateCarportHasMaterial(carport_id, material_id, quantity);
    }

    public int updateShedHasMaterial(int shed_id, int material_id, int quantity) throws UserException {
        return orderMapper.updateShedHasMaterial(shed_id, material_id, quantity);
    }


    public Order getOrderByOrderId(int order_id, int carportRoof_materialID, int shedClothing_materialID) throws UserException {
        return orderMapper.getOrderByOrderId(order_id, carportRoof_materialID, shedClothing_materialID);
    }

}
