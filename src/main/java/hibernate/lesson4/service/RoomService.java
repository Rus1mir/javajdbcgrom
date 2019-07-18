package hibernate.lesson4.service;

import hibernate.lesson4.dao.*;
import hibernate.lesson4.model.*;
import jdbc.lesson4.hw.exception.BadRequestException;

import java.util.List;

public class RoomService {

    private RoomDAO roomDAO = new RoomDAO();
    private HotelDAO hotelDAO = new HotelDAO();

    public Room addRoom(Room room, long hotelId) throws Exception {

        UserService.validateUser(UserType.ADMIN);

        Hotel hotel = hotelDAO.findById(hotelId);

        if (hotel != null) {

            hotel.addRoom(room);
            hotelDAO.update(hotel);
            return room;
        }
        throw new BadRequestException("Hotel with id: " + hotelId + " was not found. Adding room was filed");
    }

    public void deleteRoom(long id) throws Exception {

        UserService.validateUser(UserType.ADMIN);

        roomDAO.delete(id);
    }

    public List<Room> findRooms(Filter filter) throws Exception {

        UserService.validateUser(UserType.USER);

        return roomDAO.findRooms(filter);
    }
}
