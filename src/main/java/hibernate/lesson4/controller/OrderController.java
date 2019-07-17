package hibernate.lesson4.controller;

import hibernate.lesson4.service.OrderService;

import java.util.Date;

public class OrderController {

    private OrderService orderService = new OrderService();

    public void bookRoom(long roomId, long userId, Date dateFrom, Date dateTo) throws Exception {

        orderService.bookRoom(roomId, userId, dateFrom, dateTo);
    }

    public void cancelReservation(long roomId, long userId) throws Exception {

        orderService.cancelReservation(roomId, userId);
    }
}
