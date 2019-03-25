package controller;

import service.OrderService;

import java.util.Date;

public class OrderController {
    private OrderService orderService = new OrderService();
    private UserController userController = new UserController();

    public void cancelReservation(long roomId) throws Exception {
        userController.validateLogin();
        orderService.cancelReservation(roomId, UserController.currentUser.getId());
    }

    public void bookRoom(long roomId, Date dateFrom, int days) throws Exception {
        userController.validateLogin();
        orderService.bookRoom(roomId, UserController.currentUser.getId(),dateFrom, days);
    }
}
