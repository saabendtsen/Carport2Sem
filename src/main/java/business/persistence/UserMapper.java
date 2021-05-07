package business.persistence;

import business.exceptions.UserException;
import business.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserMapper {
    private final Database database;

    public UserMapper(Database database) {
        this.database = database;
    }

    public void createUser(User user) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO user (email, password, role) VALUES (?, ?, ?)";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, user.getEmail());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getRole());
                ps.executeUpdate();
                ResultSet ids = ps.getGeneratedKeys();
                ids.next();
                int id = ids.getInt(1);
                user.setUser_id(id);
            }
            catch (SQLException ex) {
                /// TODO: 06-05-2021 gør det mere brugervenligt at få en (unik username) error
                throw new UserException(ex.getMessage());
            }
        }
        catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }

    public User login(String email, String password) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT user_id, role FROM user WHERE email=? AND password=?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, email);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String role = rs.getString("role");
                    int id = rs.getInt("user_id");
                    User user = new User(email, password, role);
                    user.setUser_id(id);
                    return user;
                } else {
                    throw new UserException("Could not validate user");
                }
            }
            catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        }
        catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }

    public List<User> getAllUsers() throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT user_id, email, password, role FROM user";

            List<User> userList = new ArrayList<>();

            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int user_id = rs.getInt("user_id");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    String role = rs.getString("role");

                    userList.add(new User(user_id, email, password, role));
                }

            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
            return userList;
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }

    public boolean CheckUserEmail(String email) throws UserException {
        boolean userExist = false;
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM user WHERE email=?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, email);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    userExist = true;
                }

            }
            catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        }
        catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
        return userExist;
    }

    public int UpdateUserEmail(String newEmail, User user) throws UserException {
        int rowsAffeted;
        try (Connection connection = database.connect()) {
            String sql = "UPDATE user SET email = ? WHERE user_id = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, newEmail);
                ps.setInt(2, user.getUser_id());
                rowsAffeted = ps.executeUpdate();

            }
            catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        }
        catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
        return rowsAffeted;
    }

    public int UpdateUserPassword(String newPassword, User user) throws UserException {
        int rowsAffeted;
        try (Connection connection = database.connect()) {
            String sql = "UPDATE user SET password = ? WHERE user_id = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, newPassword);
                ps.setInt(2, user.getUser_id());
                rowsAffeted = ps.executeUpdate();

            }
            catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        }
        catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
        return rowsAffeted;
    }
}
