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

    public void UpdateUserPassword(String newPassword, String email) throws UserException{
        userMapper.UpdateUserPassword(newPassword,email);
    }

    public void UpdateUserEmail(String newEmail, String oldEmail) throws UserException{
        userMapper.UpdateUserEmail(newEmail, oldEmail);
    }

}
