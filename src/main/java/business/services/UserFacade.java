package business.services;

import business.entities.User;
import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.UserMapper;

import java.util.List;

public class UserFacade {
    UserMapper userMapper;

    public UserFacade(Database database) {
        userMapper = new UserMapper(database);
    }

    public User login(String email, String password) throws UserException {
        return userMapper.login(email, password);
    }

    public User createUser(String email, String password) throws UserException {
        User user = new User(email, password, "customer");
        userMapper.createUser(user);
        return user;
    }

    public int UpdateUserPassword(String newPassword, User user) throws UserException{
        return userMapper.UpdateUserPassword(newPassword,user);
    }

    public int UpdateUserEmail(String newEmail, User user) throws UserException{
        return userMapper.UpdateUserEmail(newEmail, user);
    }

    public boolean CheckUserEmail(String email) throws UserException{
        return userMapper.CheckUserEmail(email);
    }

    public List<User> getAllUsers() throws UserException {
        return userMapper.getAllUsers();
    }

}
