package hibernate.lesson4.demo;

import hibernate.lesson4.dao.*;
import hibernate.lesson4.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class demo {

    public static void main(String[] args) throws Exception {

        HotelDAO hotelDAO = new HotelDAO();
        RoomDAO roomDAO = new RoomDAO();
        UserDAO userDAO = new UserDAO();
        OrderDAO orderDAO = new OrderDAO();

        //User user = new User("Piter", "1234", "Ukraine", UserType.ADMIN, new ArrayList<Order>());
       // userDAO.save(user);

        //Room room = roomDAO.findById(110);
        //User user = userDAO.findById(1);
        //Order order = new Order(user, room, new Date(), new Date(), 333D);
        //orderDAO.save(order);

        //userDAO.getUserByNameAndPass("Ivan", "123");
        //List<Room> rooms = new ArrayList<>();
        //roomDAO.findRoomsByFilter();
        //Hotel hotel =  hotelDAO.findById(86);
        //System.out.println(hotel);
        //List<Room> rooms = hotel.getRooms();

        //Hotel hotel = new Hotel("Savoy", "Germany", "Berlin", "Shtrasse1", rooms);
        //Hotel hotel = hotelDAO.findById(102);
        //Hotel hotel1 = new Hotel("Palace", "USA", "Denver", "Franklin", rooms);
        //System.out.println(hotelDAO.save(hotel));
        //System.out.println(hotelDAO.save(hotel1));

        //rooms.get(0).setPrice(567);
        //System.out.println(hotelDAO.update(hotel));

        //System.out.println(hotel);
        //Room room = new Room(5, 789.25, false, true, new Date(), hotel);
        //roomDAO.save(room);
        //Room room1 = new Room(5, 344.87, true, false, new Date(), hotel);
        //Room room2 = new Room(2, 555.6, true, false, new Date(), hotel);
        //System.out.println(roomDAO.save(room));
        //System.out.println(roomDAO.save(room1));
        //System.out.println(roomDAO.save(room2));
        //hotelDAO.delete(91);
        //Filter f = new Filter(null,null, null, null, new Date(), "USA", null);
        //System.out.println(roomDAO.findRoomsByFilter(f));
        System.out.println(userDAO.getUserByNameAndPass("Piter", "1234"));
        System.out.println(hotelDAO.findHotelsByCity("Berlin"));
        System.out.println(hotelDAO.findHotelsByName("Palace"));
    }
}
