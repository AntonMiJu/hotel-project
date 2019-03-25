package service;

import model.Order;
import model.Room;
import repository.OrderRepository;
import repository.RoomRepository;

import java.util.Calendar;
import java.util.Date;

public class OrderService {
    private OrderRepository orderRepository = new OrderRepository();
    private RoomRepository roomRepository = new RoomRepository();
    private RoomService roomService = new RoomService();
    private UserService userService = new UserService();

    public void cancelReservation(long roomId, long userId) throws Exception{
        Date availableFrom = null;
        Room chRoom;
        for (Order el : orderRepository.readFile()){
            if (el.getRoom().getId() == roomId && el.getUser().getId()==userId)
                availableFrom = el.getDateFrom();
        }
        chRoom = roomService.findRoomById(roomId);
        chRoom.setDateAvailableFrom(availableFrom);
        roomRepository.deleteRoom(roomId);
        roomRepository.addRoom(chRoom);
        orderRepository.cancelReservation(roomId, userId);
    }

    public void bookRoom(long roomId, long userId,Date dateFrom, int days) throws Exception{
        Room chRoom;
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateFrom);
        cal.add(Calendar.DATE, days);
        chRoom = roomService.findRoomById(roomId);
        chRoom.setDateAvailableFrom(cal.getTime());
        roomRepository.deleteRoom(roomId);
        roomRepository.addRoom(chRoom);
        orderRepository.writeFile(new Order(roomService.findRoomById(roomId).hashCode(),userService.findUserById(userId),roomService.findRoomById(roomId),roomService.findRoomById(roomId).getDateAvailableFrom(),cal.getTime(),roomService.findRoomById(roomId).getPrice()));
    }
}
