package hibernate.lesson4.service;

import hibernate.lesson4.dao.OrderDAO;
import hibernate.lesson4.dao.RoomDAO;
import hibernate.lesson4.dao.UserDAO;
import hibernate.lesson4.exception.AccessDeniedException;
import hibernate.lesson4.exception.BadRequestException;
import hibernate.lesson4.model.Order;
import hibernate.lesson4.model.Room;
import hibernate.lesson4.model.User;

import java.time.temporal.ChronoUnit;
import java.util.Date;


public class OrderService {

    private OrderDAO orderDAO = new OrderDAO();
    private RoomDAO roomDAO = new RoomDAO();
    private UserDAO userDAO = new UserDAO();

    public Order save(Order order) throws Exception {

        return orderDAO.save(order);
    }

    public Order update(Order order) throws Exception {

        return orderDAO.update(order);
    }

    public Order findById(long id) throws Exception {

        return orderDAO.findById(id);
    }

    public void delete(long id) throws Exception {

        orderDAO.delete(id);
    }

    public void bookRoom(long roomId, long userId, Date dateFrom, Date dateTo) throws Exception {

        User user = validateUser(userId);
        Room room = roomDAO.findById(roomId);

        if (room == null)
            throw new BadRequestException("Room id: " + roomId + " was not found in database, booking filed");

        if (room.getDateAvailableFrom().after(dateFrom))
            throw new BadRequestException("Requested room id: " + roomId + " is already booked for requested date!");

        long daysAmount = ChronoUnit.DAYS.between(dateFrom.toInstant(), dateTo.toInstant());

        room.setDateAvailableFrom(dateTo);

        Order order = new Order(user, room,
                dateFrom, dateTo, room.getPrice() * daysAmount);

        orderDAO.update(order);
    }

    public void cancelReservation(long roomId, long userId) throws Exception {

        validateUser(userId);
        Room room = roomDAO.findById(roomId);

        if (room == null)
            throw new BadRequestException("Room id: " + roomId + " was not found in database, cancel reservation filed");

        //TODO
        Order order = orderDAO.findByRoomUser(roomId, userId);

        throw new BadRequestException("Order with requested room id: " + roomId + " or (and) with requested user id:" +
                userId + " was not found, cancel reservation filed");
    }

    private User validateUser(Long userId) throws AccessDeniedException {
        User user = UserDAO.getLoginUser();

        if (user == null || user.getId() != userId)
            throw new AccessDeniedException("Action not permitted for user id " + userId + ", cause this user is not login");

        return user;
    }
}
