package hibernate.lesson4.controller;

import hibernate.lesson4.dao.Filter;
import hibernate.lesson4.model.Room;
import hibernate.lesson4.service.RoomService;

import java.util.List;

public class RoomController {

    private RoomService roomService = new RoomService();

    //CRUD
    public Room save(Room room) throws Exception {

        return roomService.save(room);
    }

    public Room update(Room room) throws Exception {

        return roomService.update(room);
    }

    public Room findById(long id) throws Exception {

        return roomService.findById(id);
    }

    public void delete(long id) throws Exception {

        roomService.delete(id);
    }

    //Other
    public List<Room> findRooms(Filter filter) throws Exception {

        return roomService.findRooms(filter);
    }
}
