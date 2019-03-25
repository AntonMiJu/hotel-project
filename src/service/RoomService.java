package service;

import model.Filter;
import model.Room;
import repository.RoomRepository;

import java.util.ArrayList;

public class RoomService {
    private RoomRepository roomRepository = new RoomRepository();

    public void addRoom(Room room){
        roomRepository.addRoom(room);
    }

    public void deleteRoom(long id) throws Exception{
        roomRepository.deleteRoom(id);
    }

    public Room findRoomById(long id) throws Exception{
        Room[] rooms = (Room[]) roomRepository.readFile().toArray();
        for (Room el : rooms){
            if (el.getId() == id)
                return el;
        }
        return null;
    }

    public ArrayList<Room> findRooms(Filter filter) throws Exception{
        ArrayList<Room> res = new ArrayList<>();
        Room[] rooms = (Room[]) roomRepository.readFile().toArray();
        for (Room el : rooms){
            if (el.getNumberOfGuests() == filter.getNumberOfGuests() && el.isBreakfastIncluded() == filter.isBreakfastIncluded() && el.isPetsAllowed() == filter.isPetsAllowed() && el.getDateAvailableFrom().before(filter.getDateAvailableFrom()) && el.getHotel().getCountry().equals(filter.getCountry()) && el.getHotel().getCity().equals(filter.getCity()))
                res.add(el);
        }
        return res;
    }
}
