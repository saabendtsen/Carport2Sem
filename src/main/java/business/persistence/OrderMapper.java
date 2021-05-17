package business.persistence;

import business.entities.Carport;
import business.entities.Material;
import business.entities.Order;
import business.entities.Shed;
import business.exceptions.UserException;
import business.services.MaterialFacade;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderMapper {

    private final Database database;
    public OrderMapper(Database database) {
        this.database = database;

    }

    public int createOrder(int user_id, double carportLength, double carportWidth, double shedLength, double shedWidth ) throws UserException {
        

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
                return id;

            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }

    public void insertIntoOrderHasMaterial(Order order) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO `carport_has_material_list` (carport_id,material_id,quantity) VALUES (?, ?, ?)";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                for (Material m : order.getStkListe()) {
                    //Check if Shed clothing
                    if (m.getCategory() == 1) {
                        insertIntoShedHasMaterial(order.getShed().getShed_id(),m.getMaterial_id(),m.getQuantity());

                    } else {
                        ps.setInt(1, order.getCarport().getCarport_id());
                        ps.setInt(2, m.getMaterial_id());
                        ps.setFloat(3, m.getQuantity());
                        ps.executeUpdate();
                    }
                }

            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
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

    public void insertIntoShedHasMaterial(int shed_id, int material_id, int quantity) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO `shed_has_material_list` (shed_id,material_id,quantity) VALUES (?, ?, ?)";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, shed_id);
                ps.setInt(2, material_id);
                ps.setFloat(3, quantity);
                ps.executeUpdate();

            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }

    public Order getOrderByOrderId(int order_id, int carportRoof_materialID) throws UserException {
        try (Connection connection = database.connect()) {
            MaterialFacade mf = new MaterialFacade(database);
            String sql = "SELECT * FROM carport.carport\n" +
                    "LEFT OUTER JOIN carport.shed\n" +
                    "ON carport.order_id = shed.order_id JOIN `order` o on o.order_id = carport.order_id WHERE o.order_id = ?";
            Order newOrder = null;

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, order_id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int user_id = rs.getInt("user_id");
                    Timestamp orderdate = rs.getTimestamp("orderdate");
                    boolean order_state = rs.getBoolean("order_state");

                    int shed_id = rs.getInt("shed_id");
                    double s_length = rs.getDouble("shed.length");
                    double s_width = rs.getDouble("shed.width");

                    int carport_id = rs.getInt("carport_id");
                    double c_length = rs.getDouble("carport.length");
                    double c_width = rs.getDouble("carport.width");

                    newOrder = new Order(order_id, user_id, orderdate, order_state,
                               new Carport(carport_id, order_id, c_length, c_width, mf.getMaterialByMaterialId(carportRoof_materialID)),
                               new Shed(shed_id, order_id, s_length, s_width, selectFromShedHasMaterial(shed_id)));

                    newOrder.setStkListe(selectFromCarportHasMaterial(carport_id));
                }

            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
            return newOrder;

        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }

    public List<Order> getOrderByUserId(int user_id) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM carport.carport LEFT OUTER JOIN carport.shed\n" +
                    "ON carport.order_id = shed.order_id JOIN `order` o on o.order_id = carport.order_id WHERE user_id = ?";

            List<Order> orderList = new ArrayList<>();

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, user_id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int order_id = rs.getInt("o.order_id");
                    Timestamp orderdate = rs.getTimestamp("orderdate");
                    boolean order_state = rs.getBoolean("order_state");

                    int shed_id = rs.getInt("shed_id");
                    double s_length = rs.getDouble("shed.length");
                    double s_width = rs.getDouble("shed.width");

                    int carport_id = rs.getInt("carport_id");
                    double c_length = rs.getDouble("carport.length");
                    double c_width = rs.getDouble("carport.width");

                    orderList.add(new Order(order_id, user_id, orderdate, order_state,
                                  new Carport(carport_id, order_id, c_length, c_width),
                                  new Shed(shed_id, order_id, s_length, s_width)));

                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
            return orderList;
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }

    public List<Order> getAllOrdersByState() throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM carport.carport LEFT OUTER JOIN carport.shed\n" +
                    "ON carport.order_id = shed.order_id JOIN `order` o on o.order_id = carport.order_id WHERE order_state = 0";

            List<Order> orderList = new ArrayList<>();

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int user_id = rs.getInt("user_id");
                    int order_id = rs.getInt("o.order_id");
                    Timestamp orderdate = rs.getTimestamp("orderdate");
                    boolean order_state = rs.getBoolean("order_state");

                    int shed_id = rs.getInt("shed_id");
                    double s_length = rs.getDouble("shed.length");
                    double s_width = rs.getDouble("shed.width");

                    int carport_id = rs.getInt("carport_id");
                    double c_length = rs.getDouble("carport.length");
                    double c_width = rs.getDouble("carport.width");

                    orderList.add(new Order(order_id, user_id, orderdate, order_state,
                                  new Carport(carport_id, order_id, c_length, c_width),
                                  new Shed(shed_id, order_id, s_length, s_width)));

                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
            return orderList;
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }

    public List<Material> selectFromCarportHasMaterial(int carport_id) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM `carport_has_material_list` WHERE carport_id=?";
            List<Material> stkListe = null;
            MaterialFacade mf = new MaterialFacade(database);


            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1,carport_id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {

                    int material_id = rs.getInt("material_id");
                    Material material = mf.getMaterialByMaterialId(material_id);
                    int quantity = (int) rs.getFloat("quantity");
                    material.setQuantity(quantity);
                    stkListe.add(material);
                }

            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
            return stkListe;
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }

    public Material selectFromShedHasMaterial(int shed_id) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM `shed_has_material_list` WHERE shed_id=?";
            Material clothing = null;
            MaterialFacade mf = new MaterialFacade(database);

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1,shed_id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {

                    int material_id = rs.getInt("material_id");
                    clothing = mf.getMaterialByMaterialId(material_id);
                    int quantity = (int) rs.getFloat("quantity");
                    clothing.setQuantity(quantity);

                }

            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
            return clothing;
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }

    public int updateOrder(int order_id) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "UPDATE `order` SET order_state = 1 WHERE order_id = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                ps.setInt(1, order_id);

                return ps.executeUpdate();

            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }
}
