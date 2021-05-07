package business.persistence;

import business.entities.Carport;
import business.entities.Order;
import business.entities.Shed;
import business.exceptions.UserException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderMapper {

    private final Database database;

    public OrderMapper(Database database) {
        this.database = database;
    }


    public void insertIntoCarport(double carportLength, double carportWidth, int order_id) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO `carport` (order_id, length, width) VALUES (?, ?, ?)";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, order_id);
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

    public void createOrder(int user_id, double carportLength, double carportWidth, double shedLength, double shedWidth) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO `order` (user_id) VALUES (?)";
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, user_id);
                ps.executeUpdate();

                ResultSet order_id = ps.getGeneratedKeys();
                order_id.next();
                int id = order_id.getInt(1);

                insertIntoCarport(carportLength, carportWidth, id);

                //check if the value of shed is 0, if so dont add a shed to the order
                if (shedLength != 0 && shedWidth != 0) {
                    insertIntoShed(shedLength, shedWidth, id);
                }

            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }

    public void insertIntoShed(double shedLength, double shedWidth, int order_id) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO `shed` (order_id, length, width) VALUES (?, ?, ?)";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, order_id);
                ps.setDouble(2, shedLength);
                ps.setDouble(3, shedWidth);

                ps.executeUpdate();

            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }

    public List<Order> getOrderByUserId(int user_id) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "select `order`.order_id, `order`.orderdate, s.shed_id, s.total, s.length, s.width, c.carport_id, c.total, c.length, c.width from `order`\n" +
                    "join carport c on `order`.order_id = c.order_id\n" +
                    "join shed s on `order`.order_id = s.order_id where `order`.user_id = ?";

            List<Order> orderList = new ArrayList<>();

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, user_id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int order_id = rs.getInt("order_id");
                    Timestamp orderdate = rs.getTimestamp("orderdate");
                    int shed_id = rs.getInt("s.shed_id");
                    double s_total = rs.getDouble("s.total");
                    double s_length = rs.getDouble("s.length");
                    double s_width = rs.getDouble("s.width");
                    int carport_id = rs.getInt("c.carport_id");
                    double c_total = rs.getDouble("c.total");
                    double c_length = rs.getDouble("c.length");
                    double c_width = rs.getDouble("c.width");

                    orderList.add(new Order(order_id, user_id, orderdate, new Carport(carport_id, order_id, c_total, c_length, c_width), new Shed(shed_id, order_id, s_total, s_length, s_width)));

                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
            return orderList;
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }


}
