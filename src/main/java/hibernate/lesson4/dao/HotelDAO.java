package hibernate.lesson4.dao;

import hibernate.lesson4.model.Hotel;

import java.util.ArrayList;
import java.util.List;

public class HotelDAO extends GeneralDAO<Hotel> {

    public HotelDAO() {
        super(Hotel.class);
    }

    public Hotel save(Hotel hotel) throws Exception {

        return saveEntity(hotel);
    }

    public Hotel update(Hotel hotel) throws Exception {

        return updateEntity(hotel);
    }

    public Hotel findById(long id) throws Exception{

        return findEntityById(id);
    }

    public void delete(long id) throws Exception{
        deleteEntity(id);
    }

    public List<Hotel> findHotelsByName(String name) throws Exception {

        String sql = "SELECT * FROM HOTELS WHERE NAME = ?";

        List<Object> params = new ArrayList<>();
        params.add(name);

        return getEntitiesByQuery(sql, params);
    }

    public List<Hotel> findHotelsByCity(String city) throws Exception {

        String sql = "SELECT * FROM HOTELS WHERE CITY = ?";

        List<Object> params = new ArrayList<>();
        params.add(city);

        return getEntitiesByQuery(sql, params);
    }
}
