package business.persistence;

import business.entities.User;
import business.exceptions.UserException;

import java.sql.*;

public class OrderMapper {

    private final Database database;

    public OrderMapper(Database database) {
        this.database = database;
    }


    public void insertIntoCarport(double carportLength, double carportWidth) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO carport (lenght, height) VALUES (?, ?)";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setDouble(1, carportLength);
                ps.setDouble(2, carportWidth);
                ps.executeUpdate();

            }
            catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        }
        catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }

}
