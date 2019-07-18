package hibernate.lesson4.demo;

import hibernate.lesson4.controller.UserController;
import hibernate.lesson4.dao.UserDAO;
import hibernate.lesson4.model.Order;
import hibernate.lesson4.model.User;
import hibernate.lesson4.model.UserType;

import java.util.ArrayList;

public class DemoUser {

    private static UserController userController = new UserController();
    private static UserDAO userDAO = new UserDAO();

    public static void main(String[] args) throws Exception {

        //registerUsers(); //tested
        //saveEqual(); //tested
        //saveWithOrders(); //tested
        //normallyLogin(); //tested
        //loginWrongName(); //tested
        loginWrongPass(); //tested
    }

    private static void registerUsers() throws Exception {

        User user = new User("Ivan", "333", "Ukraine", UserType.USER);
        User user1 = new User("John", "345", "USA", UserType.ADMIN);

        userController.registerUser(user);
        userController.registerUser(user1);
    }

    private static void saveEqual() throws Exception {

        User duplicate = new User("John", "345", "test", UserType.ADMIN);
        userController.registerUser(duplicate);

    }

    private static void saveWithOrders() throws Exception {

        ArrayList<Order> orders = new ArrayList<>();
        orders.add(new Order());
        User withOrders = new User("Assam", "345", "India", UserType.USER);
        withOrders.setOrders(orders);

        userController.registerUser(withOrders);
    }

    private static void normallyLogin() throws Exception {

        userController.login("Ivan", "333");

        System.out.println(UserDAO.getLoginUser());
        System.out.println(UserDAO.isLogined());
        System.out.println(UserDAO.isAdmin());

        userController.login("John", "345");

        System.out.println(UserDAO.getLoginUser());
        System.out.println(UserDAO.isLogined());
        System.out.println(UserDAO.isAdmin());

        userController.logout();

        System.out.println(UserDAO.getLoginUser());
        System.out.println(UserDAO.isLogined());
        System.out.println(UserDAO.isAdmin());
    }

    private static void loginWrongName() throws Exception {

        userController.login("no Ivan", "123");
    }

    private static void loginWrongPass() throws Exception {

        userController.login("Ivan", "1234");
    }
}
