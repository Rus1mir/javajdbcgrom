package hibernate.lesson4.demo;

import hibernate.lesson4.controller.HotelController;
import hibernate.lesson4.controller.RoomController;
import hibernate.lesson4.controller.UserController;
import hibernate.lesson4.dao.HotelDAO;
import hibernate.lesson4.model.Filter;
import hibernate.lesson4.model.Hotel;
import hibernate.lesson4.model.Room;
import hibernate.lesson4.service.HotelService;

import java.util.Date;

public class DemoRoom {

    private static RoomController roomController = new RoomController();
    private static HotelDAO hotelDAO = new HotelDAO();

    public static void main(String[] args) throws Exception {

        UserController userController = new UserController();
        userController.login("John", "345"); // Admin
        //userController.login("Ivan", "333"); // User

        //addNormal(); //tested
        //delete(); //tested
        //find(); //tested
    }

    static void addNormal() throws Exception {

        roomController.addRoom(
                new Room(9, 400d, false, false, new Date()), 121);
        roomController.addRoom(
                new Room(8, 600d, false, false, new Date()), 121);
        roomController.addRoom(
                new Room(7, 230d, false, false, new Date()), 121);
    }

    static void delete() throws Exception {

        roomController.deleteRoom(150);
    }

    static void find() throws Exception {

        Filter filter = new Filter(5, null, true, null,
                null, "USA", null);

        System.out.println(roomController.findRooms(filter));
    }
}
