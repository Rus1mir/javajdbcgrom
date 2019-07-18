package hibernate.lesson4.demo;

import hibernate.lesson4.controller.HotelController;
import hibernate.lesson4.controller.UserController;
import hibernate.lesson4.model.Hotel;
import hibernate.lesson4.model.Room;

import java.util.Date;

public class DemoHotel {

    private static HotelController hotelController = new HotelController();

    public static void main(String[] args) throws Exception {

        UserController userController = new UserController();
        userController.login("John", "345"); // Admin
        //userController.login("Ivan", "333"); // User

        //saveNoRooms(); //tested
        //saveWithRooms(); //tested
        //deleteHotel(); //tested
        //findHotel(); //tested
    }

    static void saveNoRooms() throws Exception {

        Hotel hotel = new Hotel("Radja", "India", "Mumbai", "Rama st.");
        Hotel hotel1 = new Hotel("Palace", "USA", "Vice-City", "Franklin st.");
        Hotel hotel2 = new Hotel("Palace", "Russia", "Moscow", "Lenin");
        Hotel hotel3 = new Hotel("Palace", "Bulgaria", "Burgas", "Kirkorova");

        hotelController.addHotel(hotel);
        hotelController.addHotel(hotel1);
        hotelController.addHotel(hotel2);
        hotelController.addHotel(hotel3);
    }

    static void saveWithRooms() throws Exception {

        Hotel hotel = new Hotel("Plaza", "China", "Dalian", "Mao st.");

        hotel.addRoom(new Room(2, 200d, false, false, new Date()));
        hotel.addRoom(new Room(4, 500d, false, false, new Date()));
        hotel.addRoom(new Room(1, 230d, false, false, new Date()));

        System.out.println(hotelController.addHotel(hotel));
    }

    static void deleteHotel() throws Exception {

        hotelController.deleteHotel(124);
    }

    static void findHotel() throws Exception {

        System.out.println(hotelController.findHotelsByCity("Mumbai"));
        System.out.println(hotelController.findHotelsByName("Palace"));
    }
}
