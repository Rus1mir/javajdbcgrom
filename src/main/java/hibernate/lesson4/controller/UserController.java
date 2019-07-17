package hibernate.lesson4.controller;

import hibernate.lesson4.model.User;
import hibernate.lesson4.service.UserService;

public class UserController {

    private UserService userService = new UserService();

    public User registerUser(User user) throws Exception {

        return userService.registerUser(user);
    }

    public void login(String userName, String password) throws Exception {

        userService.login(userName, password);
    }

    public void logout() {

        userService.logout();
    }
}
