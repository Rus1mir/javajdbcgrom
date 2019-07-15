package hibernate.lesson4.demo;

import hibernate.lesson4.dao.OrderDAO;
import hibernate.lesson4.dao.RoomDAO;
import hibernate.lesson4.dao.UserDAO;
import hibernate.lesson4.model.*;
import hibernate.lesson4.dao.HotelDAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class demo {

    public static void main(String[] args) throws Exception {

        HotelDAO hotelDAO = new HotelDAO();
        RoomDAO roomDAO = new RoomDAO();
        UserDAO userDAO = new UserDAO();
        OrderDAO orderDAO = new OrderDAO();

        //User user = new User("Ivan", "123", "Ukraine", UserType.USER, new ArrayList<Order>());
        //userDAO.save(user);

        //Room room = roomDAO.findById(110);
        //User user = userDAO.findById(1);
        //Order order = new Order(user, room, new Date(), new Date(), 333D);
        //orderDAO.save(order);

        //userDAO.getUserByNameAndPass("Ivan", "123");
        //List<Room> rooms = new ArrayList<>();
        roomDAO.getRoomByFilter();
        //Hotel hotel =  hotelDAO.findById(86);
        //System.out.println(hotel);
        //List<Room> rooms = hotel.getRooms();

        //Hotel hotel1 = new Hotel("Savoy", null, "Berlin", "Shtrasse1", rooms);
        //System.out.println(hotelDAO.save(hotel1));

        //rooms.get(0).setPrice(567);
        //System.out.println(hotelDAO.update(hotel));

        //System.out.println(hotel);
        //Room room = new Room(5, 789.25, false, true, new Date(), null);
        //roomDAO.save(room);
        //Room room1 = new Room(5, 344.87, true, false, new Date(), hotel);
        //Room room2 = new Room(2, 555.6, true, false, new Date(), hotel);
        //hotelDAO.delete(91);

    }
}
