package service;

import model.Filter;
import model.Hotel;
import model.Room;
import repository.HotelRepository;
import repository.RoomRepository;

import java.util.ArrayList;

public class RoomService {
    private RoomRepository roomRepository = new RoomRepository();
    private HotelRepository hotelRepository = new HotelRepository();

    public void addRoom(Room room) {
        try {
            if (roomInHotelDb(room))
                roomRepository.addRoom(room);
            else
                throw new Exception("Hotel with "+room.getHotelId()+" id not found.");
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public void deleteRoom(long id) {
        roomRepository.deleteRoom(id);
    }

    public Room findRoomById(long id) {
        return roomRepository.findRoomById(id);
    }

    public ArrayList<Room> findRooms(Filter filter) {
        ArrayList<Room> res = new ArrayList<>();
        for (Room el : roomRepository.readFile()) {
            if (el.getNumberOfGuests() == filter.getNumberOfGuests() && el.isBreakfastIncluded() == filter.isBreakfastIncluded() && el.isPetsAllowed() == filter.isPetsAllowed() && el.getDateAvailableFrom().before(filter.getDateAvailableFrom()))
                res.add(el);
        }
        return res;
    }

    private boolean roomInHotelDb(Room room){
        for (Hotel el : hotelRepository.readFile()){
            if (room.getHotelId() == el.getId())
                return true;
        }
        return false;
    }
}
