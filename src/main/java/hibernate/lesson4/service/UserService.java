package hibernate.lesson4.service;

import hibernate.lesson4.dao.UserDAO;
import hibernate.lesson4.model.User;
import jdbc.lesson4.hw.exception.BadRequestException;

public class UserService {

    private UserDAO userDAO = new UserDAO();

    //CRUD
    public User save(User user) throws Exception {
        return userDAO.save(user);
    }

    public User update(User user) throws Exception {
        return userDAO.update(user);
    }

    public User findById(long id) throws Exception {
        return userDAO.findById(id);
    }

    public void delete(long id) throws Exception {
        userDAO.delete(id);
    }

    //Other
    public User registerUser(User user) throws Exception {

        if (userDAO.getUserByNameAndPass(user.getUserName(), user.getPassword()) != null)
            throw new BadRequestException("User with name: " + user.getUserName() + " and password was already registered");

        return userDAO.save(user);
    }

    public void login(String userName, String password) throws Exception {

        User user = userDAO.getUserByNameAndPass(userName, password);

        if (user == null)
            throw new BadRequestException("User with name: " + userName + " does no exists. Please register first");

        userDAO.login(user);
    }
}
