package controller;

import model.Filter;
import model.Room;
import service.RoomService;

import java.util.ArrayList;

public class RoomController {
    private UserController userController = new UserController();
    private RoomService roomService = new RoomService();

    public void addRoom(Room room) throws Exception {
        userController.validateLoginAdmin();
        roomService.addRoom(room);
    }

    public void deleteRoom(long id) throws Exception {
        userController.validateLoginAdmin();
        roomService.deleteRoom(id);
    }

    public ArrayList<Room> findRooms(Filter filter) throws Exception {
        userController.validateLogin();
        return roomService.findRooms(filter);
    }
}
