package hibernate.lesson4.demo;

import hibernate.lesson4.dao.RoomDAO;
import hibernate.lesson4.model.Hotel;
import hibernate.lesson4.dao.HotelDAO;
import hibernate.lesson4.model.Room;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class demo {

    public static void main(String[] args) throws Exception {

        HotelDAO hotelDAO = new HotelDAO();
        RoomDAO roomDAO = new RoomDAO();

        List<Room> rooms = new ArrayList<>();

        Hotel hotel = new Hotel("Palace", "USA", "NewYork", "Presley", rooms);

        Room room = new Room(2, 545.34, true, false, new Date(), hotel);
        Room room1 = new Room(5, 344.87, true, false, new Date(), hotel);
        Room room2 = new Room(2, 555.6, true, false, new Date(), hotel);

        rooms.add(room);
        rooms.add(room1);
        rooms.add(room2);

        //roomDAO.save(room);

        hotelDAO.save(hotel);

        System.out.println("hello");
    }
}
