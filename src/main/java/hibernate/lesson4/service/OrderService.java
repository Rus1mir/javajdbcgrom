package hibernate.lesson4.service;

import hibernate.lesson4.dao.*;
import hibernate.lesson4.exception.AccessDeniedException;
import hibernate.lesson4.exception.BadRequestException;
import hibernate.lesson4.model.*;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

public class OrderService {

    private OrderDAO orderDAO = new OrderDAO();
    private RoomDAO roomDAO = new RoomDAO();

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

        List<Order> orders = orderDAO.findByRoomUser(roomId, userId);

        if (orders.size() == 0)
            throw new BadRequestException("Order with requested room id: " + roomId + " or (and) with requested user id:" +
                    userId + " was not found, cancel reservation filed");

        room.setDateAvailableFrom(new Date());

        orderDAO.cancelOrder(orders.get(0), room);
    }

    private User validateUser(Long userId) throws AccessDeniedException {
        User user = UserDAO.getLoginUser();

        if (user == null || user.getId() != userId)
            throw new AccessDeniedException("Action not permitted for user id " + userId + ", cause this user is not login");

        return user;
    }
}
