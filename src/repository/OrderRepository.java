package repository;

import model.Order;
import model.Room;
import model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class OrderRepository extends GeneralRepository<Order> {
    private String path = "/home/anton/OrderDB.txt";
    private UserRepository userRepository = new UserRepository();
    private RoomRepository roomRepository = new RoomRepository();
    private SimpleDateFormat format=new SimpleDateFormat("dd-mm-yyyy");

    public ArrayList<Order> readFile() {
        return readFromFile();
    }

    public void writeFile(Order order) {
        addObject(order);
    }

    public void cancelReservation(long roomId, long userId) {
        deleteObject(roomId, userId);
    }

    @Override
    public Order map(String str) {
        try {
            SimpleDateFormat format=new SimpleDateFormat("dd-mm-yyyy");
            String[] array = str.split(",");
            User user = userRepository.findUserById(Long.parseLong(array[1].trim()));
            Room room = roomRepository.findRoomById(Long.parseLong(array[2].trim()));
            return new Order(Long.parseLong(array[0].trim()), user, room, format.parse(array[3].trim()), format.parse(array[4].trim()), Double.parseDouble(array[5].trim()));
        } catch (ParseException e){
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String reverseMap(Order order) {
        return order.getId() + "," + order.getUser().getId() + "," + order.getRoom().getId() + "," + format.format(order.getDateFrom()) + "," + format.format(order.getDateTo()) + "," + order.getMoneyPaid();
    }

    public OrderRepository() {
        setPath(path);
    }
}
