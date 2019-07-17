package hibernate.lesson4.service;

import hibernate.lesson4.dao.HotelDAO;
import hibernate.lesson4.dao.UserDAO;
import hibernate.lesson4.exception.AccessDeniedException;
import hibernate.lesson4.model.Hotel;

import java.util.List;

public class HotelService {

    private HotelDAO hotelDAO = new HotelDAO();

    public List<Hotel> findHotelsByName(String name) throws Exception {

        validateUser();
        return hotelDAO.findHotelsByName(name);
    }

    public List<Hotel> findHotelsByCity(String city) throws Exception {

        validateUser();
        return hotelDAO.findHotelsByCity(city);
    }

    private void validateUser() throws Exception {
        if (UserDAO.isLogined())
            throw new AccessDeniedException("Operation is not permitted without login, please login");
    }
}
