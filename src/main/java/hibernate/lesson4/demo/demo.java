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

        Hotel hotel =  hotelDAO.findById(86);
        System.out.println(hotel);
        System.out.println(hotel.getRooms());

        //Hotel hotel1 = new Hotel("Savoy", "Germany", "Berlin", "Shtrasse1", rooms);

        //System.out.println(hotel);
        //Room room = new Room(5, 789.25, false, true, new Date(), hotel);
        //roomDAO.save(room);
        //Room room1 = new Room(5, 344.87, true, false, new Date(), hotel);
        //Room room2 = new Room(2, 555.6, true, false, new Date(), hotel);


    }
}
