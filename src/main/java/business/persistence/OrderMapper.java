package business.persistence;

import business.entities.Order;
import business.entities.User;
import business.exceptions.UserException;

import java.sql.*;

public class OrderMapper {

    private final Database database;

    public OrderMapper(Database database) {
        this.database = database;
    }


    public void insertIntoCarport(double carportLength, double carportWidth, int order_id) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO carport (order_id, length, width) VALUES (?, ?, ?)";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1,order_id);
                ps.setDouble(2, carportLength);
                ps.setDouble(3, carportWidth);
                ps.executeUpdate();

            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }


    public void createOrder(int user_id, double carportLength, double carportWidth) throws UserException{
        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO `order` (user_id) VALUES (?)";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, user_id);
                ps.executeUpdate();
                ResultSet order_id = ps.getGeneratedKeys();
                order_id.next();
                int id = order_id.getInt(1);

                insertIntoCarport(carportLength,carportWidth,id);

            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }


    }
}
