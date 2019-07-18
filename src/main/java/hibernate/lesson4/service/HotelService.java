package hibernate.lesson4.service;

import hibernate.lesson4.dao.HotelDAO;
import hibernate.lesson4.model.Hotel;
import hibernate.lesson4.model.UserType;

import java.util.List;

public class HotelService {

    private HotelDAO hotelDAO = new HotelDAO();

    public Hotel addHotel(Hotel hotel) throws Exception {

        UserService.validateUser(UserType.ADMIN);
        return hotelDAO.save(hotel);
    }

    public void deleteHotel(long id) throws Exception {

        UserService.validateUser(UserType.ADMIN);
        hotelDAO.delete(id);
    }

    public List<Hotel> findHotelsByName(String name) throws Exception {

        UserService.validateUser(UserType.USER);
        return hotelDAO.findHotelsByName(name);
    }

    public List<Hotel> findHotelsByCity(String city) throws Exception {

        UserService.validateUser(UserType.USER);
        return hotelDAO.findHotelsByCity(city);
    }
}
