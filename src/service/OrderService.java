package service;

import model.Order;
import model.Room;
import repository.OrderRepository;
import repository.RoomRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class OrderService {
    private OrderRepository orderRepository = new OrderRepository();
    private RoomRepository roomRepository = new RoomRepository();
    private RoomService roomService = new RoomService();
    private UserService userService = new UserService();

    public void cancelReservation(long roomId, long userId) {
        Date availableFrom = null;
        Room chRoom;
        for (Order el : orderRepository.readFile()) {
            if (el.getRoom().getId() == roomId && el.getUser().getId() == userId)
                availableFrom = el.getDateFrom();
        }
        chRoom = roomService.findRoomById(roomId);
        chRoom.setDateAvailableFrom(availableFrom);
        roomRepository.deleteRoom(roomId);
        roomRepository.addRoom(chRoom);
        orderRepository.cancelReservation(roomId, userId);
    }

    public void bookRoom(long roomId, long userId, Date dateFrom, int days) {
        Room chRoom;
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateFrom);
        cal.add(Calendar.DATE, days);
        Date date = cal.getTime();
        chRoom = roomService.findRoomById(roomId);
        chRoom.setDateAvailableFrom(cal.getTime());
        orderRepository.writeFile(new Order(Math.abs(Objects.hash(roomId, userId, dateFrom, date)), userService.findUserById(userId), roomService.findRoomById(roomId), dateFrom, date, roomService.findRoomById(roomId).getPrice()));
        roomRepository.deleteRoom(roomId);
        roomRepository.addRoom(chRoom);
    }
}
