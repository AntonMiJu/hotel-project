package repository;

import model.Order;
import model.Room;
import model.User;
import service.RoomService;
import service.UserService;

import java.sql.Date;
import java.util.ArrayList;

public class OrderRepository extends GeneralRepository<Order> {
    private String path = "/home/anton/OrderDB.txt";
    UserService userService = new UserService();
    RoomService roomService = new RoomService();

    public ArrayList<Order> readFile() throws Exception{
        return readFromFile();
    }

    public void writeFile(Order order) {
        addObject(order);
    }

    public void cancelReservation(long roomId, long userId) {
        deleteObject(roomId, userId);
    }

    @Override
    public Order map(String str) throws Exception{
        String[] array = str.split(",");
        User user = userService.findUserById(Long.parseLong(array[1].trim()));
        Room room = roomService.findRoomById(Long.parseLong(array[2].trim()));
        return new Order(Long.parseLong(array[0].trim()), user, room, Date.valueOf(array[3].trim()), Date.valueOf(array[4].trim()), Double.parseDouble(array[5].trim()));
    }

    public OrderRepository() {
        setPath(path);
    }
}