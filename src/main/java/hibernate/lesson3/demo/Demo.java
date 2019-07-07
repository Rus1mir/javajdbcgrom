package hibernate.lesson3.demo;

import hibernate.lesson3.dao.HotelDAO;
import hibernate.lesson3.dao.RoomDAO;
import hibernate.lesson3.model.Hotel;
import hibernate.lesson3.model.Room;

import java.util.Date;

public class Demo {
    public static void main(String[] args) throws Exception {

        HotelDAO hotelDAO = new HotelDAO();
        RoomDAO roomDAO = new RoomDAO();

        Hotel hotel = new Hotel("Palace", "USA", "Denver", "Independence st.");
        System.out.println(hotelDAO.save(hotel));

        Room room = new Room(2, 333, 1, 0, new Date(), hotel);
        System.out.println(roomDAO.save(room));

        Room room1 =  roomDAO.findById(room.getId());
        System.out.println(room1);

        room1.getHotel().setName("Renamed");
        System.out.println(hotelDAO.update(room1.getHotel()));

        room1.setBreakfastIncluded(0);
        System.out.println(roomDAO.update(room1));

        roomDAO.delete(room1.getId());
        hotelDAO.delete(hotel.getId());
    }
}
