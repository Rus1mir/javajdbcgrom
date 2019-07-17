package hibernate.lesson4.service;

import hibernate.lesson4.dao.*;
import hibernate.lesson4.exception.AccessDeniedException;
import hibernate.lesson4.model.Filter;
import hibernate.lesson4.model.Room;

import java.util.List;

public class RoomService {

    RoomDAO roomDAO = new RoomDAO();

    public List<Room> findRooms(Filter filter) throws Exception {

        validateUser();
        return roomDAO.findRooms(filter);
    }

    private void validateUser() throws Exception {
        if (UserDAO.isLogined())
            throw new AccessDeniedException("Operation is not permitted without login, please login");
    }
}
