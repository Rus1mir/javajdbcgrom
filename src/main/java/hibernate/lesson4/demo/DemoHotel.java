package hibernate.lesson4.demo;

import hibernate.lesson4.controller.UserController;
import hibernate.lesson4.dao.RoomDAO;
import hibernate.lesson4.model.Filter;
import hibernate.lesson4.service.HotelService;

public class DemoHotel {

    private static HotelService hotelService = new HotelService();
    private static UserController userController = new UserController();

    public static void main(String[] args) throws Exception {

        Filter filter = new Filter(null, 789.25d, null,
                null, null, "USA", null);

        RoomDAO roomDAO = new RoomDAO();

        System.out.println(roomDAO.findRooms(filter));
    }
}
