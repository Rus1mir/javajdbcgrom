package hibernate.lesson4.demo;

import hibernate.lesson4.dao.RoomDAO;
import hibernate.lesson4.dao.UserDAO;
import hibernate.lesson4.model.Room;
import hibernate.lesson4.model.User;

import java.util.Date;

public class Demo {
    public static void main(String[] args) throws Exception {

        RoomDAO roomDAO = new RoomDAO();

        Room room = roomDAO.findById(121);
        room.setDateAvailableFrom(new Date());
        roomDAO.update(room);
    }
}
