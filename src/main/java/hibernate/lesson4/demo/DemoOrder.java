package hibernate.lesson4.demo;

import hibernate.lesson4.controller.OrderController;
import hibernate.lesson4.controller.UserController;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DemoOrder {

    private static OrderController orderController = new OrderController();

    public static void main(String[] args) throws Exception {

        UserController userController = new UserController();
        userController.login("Ivan", "333"); // User

        bookRoom(); //tested
        //unbookRoom(); //tested
    }

    static void bookRoom() throws Exception {

        Date dateFrom = new SimpleDateFormat("dd-MM-yyyy").parse("01-08-2019");
        Date dateTo = new SimpleDateFormat("dd-MM-yyyy").parse("10-08-2019");

        orderController.bookRoom(152L,41L, dateFrom, dateTo);
    }

    static void unbookRoom() throws Exception {

        orderController.cancelReservation(152, 41);
    }
}
