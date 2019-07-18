package hibernate.lesson4.dao;

import hibernate.lesson4.model.Hotel;

import java.util.HashMap;
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

        String hql = "from Hotel h where h.name = :name";

        HashMap<String, Object> params = new HashMap<>(1);

        params.put("name", name);

        return getEntitiesByQuery(hql, params);
    }

    public List<Hotel> findHotelsByCity(String city) throws Exception {

        String hql = "from Hotel h where h.city = :city";

        HashMap<String, Object> params = new HashMap<>(1);

        params.put("city", city);

        return getEntitiesByQuery(hql, params);
    }
}
