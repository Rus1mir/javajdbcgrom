package hibernate.lesson4.dao;

import hibernate.lesson4.model.Hotel;

public class HotelDAO extends GeneralDAO<Hotel> {

    public HotelDAO() {
        super(Hotel.class);
    }

    public Hotel save(Hotel hotel) throws Exception {

        return saveEntity(hotel);
    }

    public Hotel findById(long id) {

        return findEntityById(id);
    }
}
