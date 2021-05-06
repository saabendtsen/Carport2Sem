package business.services;

import business.entities.User;
import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.UserMapper;

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

    public void UpdateUserPassword(String newPassword, User user) throws UserException{
        userMapper.UpdateUserPassword(newPassword,user);
    }

    public void UpdateUserEmail(String newEmail, User user) throws UserException{
        userMapper.UpdateUserEmail(newEmail, user);
    }

}
