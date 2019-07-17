package hibernate.lesson4.controller;

import hibernate.lesson4.model.Hotel;
import hibernate.lesson4.service.HotelService;

import java.util.List;

public class HotelController {
    private HotelService hotelService = new HotelService();

    public List<Hotel> findHotelsByName(String name) throws Exception {

        return hotelService.findHotelsByName(name);
    }

    public List<Hotel> findHotelsByCity(String city) throws Exception {

        return hotelService.findHotelsByCity(city);
    }
}
