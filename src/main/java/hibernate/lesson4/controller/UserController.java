package hibernate.lesson4.controller;

import hibernate.lesson4.model.User;
import hibernate.lesson4.service.UserService;

public class UserController {

    private UserService userService = new UserService();

    //CRUD
    public User save(User user) throws Exception {
        return userService.save(user);
    }

    public User update(User user) throws Exception {
        return userService.update(user);
    }

    public User findById(long id) throws Exception {
        return userService.findById(id);
    }

    public void delete(long id) throws Exception {
        userService.delete(id);
    }

    //Other
    public User registerUser(User user) throws Exception {

        return userService.registerUser(user);
    }

    public void login(String userName, String password) throws Exception {

        userService.login(userName, password);
    }
}
