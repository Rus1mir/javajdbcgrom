package hibernate.lesson4.controller;

import hibernate.lesson4.model.Hotel;
import hibernate.lesson4.service.HotelService;

import java.util.List;

public class HotelController {
    private HotelService hotelService = new HotelService();

    //CRUD
    public Hotel save(Hotel hotel) throws Exception {

        return hotelService.save(hotel);
    }

    public Hotel update(Hotel hotel) throws Exception {

        return hotelService.update(hotel);
    }

    public Hotel findById(long id) throws Exception{

        return hotelService.findById(id);
    }

    public void delete(long id) throws Exception{
        hotelService.delete(id);
    }

    //Other
    public List<Hotel> findHotelsByName(String name) throws Exception {

        return hotelService.findHotelsByName(name);
    }

    public List<Hotel> findHotelsByCity(String city) throws Exception {

        return hotelService.findHotelsByCity(city);
    }
}
