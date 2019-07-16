package hibernate.lesson4.service;

import hibernate.lesson4.dao.HotelDAO;
import hibernate.lesson4.model.Hotel;

import java.util.List;

public class HotelService {

    private HotelDAO hotelDAO = new HotelDAO();

    //CRUD
    public Hotel save(Hotel hotel) throws Exception {

        return hotelDAO.save(hotel);
    }

    public Hotel update(Hotel hotel) throws Exception {

        return hotelDAO.update(hotel);
    }

    public Hotel findById(long id) throws Exception{

        return hotelDAO.findById(id);
    }

    public void delete(long id) throws Exception{
        hotelDAO.delete(id);
    }

    //Other
    public List<Hotel> findHotelsByName(String name) throws Exception {

        return hotelDAO.findHotelsByName(name);
    }

    public List<Hotel> findHotelsByCity(String city) throws Exception {

        return hotelDAO.findHotelsByCity(city);
    }
}
