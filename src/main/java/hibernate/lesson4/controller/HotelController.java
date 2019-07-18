package hibernate.lesson4.controller;

import hibernate.lesson4.model.Hotel;
import hibernate.lesson4.model.UserType;
import hibernate.lesson4.service.HotelService;
import hibernate.lesson4.service.UserService;

import java.util.List;

public class HotelController {

    private HotelService hotelService = new HotelService();

    public Hotel addHotel(Hotel hotel) throws Exception {

        return hotelService.addHotel(hotel);
    }

    public void deleteHotel(long hotelId) throws Exception {

        hotelService.deleteHotel(hotelId);
    }

    public List<Hotel> findHotelsByName(String name) throws Exception {

        return hotelService.findHotelsByName(name);
    }

    public List<Hotel> findHotelsByCity(String city) throws Exception {

        return hotelService.findHotelsByCity(city);
    }
}
