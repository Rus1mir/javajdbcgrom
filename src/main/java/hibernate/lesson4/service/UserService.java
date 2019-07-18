package hibernate.lesson4.service;

import hibernate.lesson4.dao.UserDAO;
import hibernate.lesson4.exception.AccessDeniedException;
import hibernate.lesson4.model.User;
import hibernate.lesson4.model.UserType;
import jdbc.lesson4.hw.exception.BadRequestException;

public class UserService {

    private UserDAO userDAO = new UserDAO();

    public User registerUser(User user) throws Exception {

        if (user.getOrders() != null)
            throw new BadRequestException("Users field Orders must be empty or null during save");

        if (userDAO.getUserByNameAndPass(user.getUserName(), user.getPassword()) != null)
            throw new BadRequestException("User with name: " + user.getUserName() +
                    " and same password was already registered");

        return userDAO.save(user);
    }

    public void login(String userName, String password) throws Exception {

        User user = userDAO.getUserByNameAndPass(userName, password);

        if (user == null)
            throw new BadRequestException("User with this name and password was no found. Please register first");

        userDAO.login(user);
    }

    public void logout() {

        userDAO.logout();
    }

    public static void validateUser(UserType userType) throws Exception {

        if (!UserDAO.isLogined())
            throw new AccessDeniedException("Operation is not permitted without login, please login");

        if (userType == UserType.ADMIN && !UserDAO.isAdmin())
            throw new AccessDeniedException("Operation is not permitted admin rights required");
    }
}
