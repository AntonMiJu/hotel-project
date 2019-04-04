package repository;

import model.Room;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class RoomRepository extends GeneralRepository<Room> {
    private String path = "/home/anton/RoomDB.txt";
    private SimpleDateFormat format=new SimpleDateFormat("dd-mm-yyyy");

    public ArrayList<Room> readFile() {
        return readFromFile();
    }

    public void addRoom(Room room) {
        addObject(room);
    }

    public void deleteRoom(long id) {
        deleteObject(id);
    }

    public void deleteRoomsByHotel(long id) {
        for (Room el : readFile()) {
            if (el.getHotelId() == id)
                deleteRoom(el.getId());
        }
    }

    public Room findRoomById(long id) {
        for (Room el : readFile()) {
            if (el.getId() == id)
                return el;
        }
        return null;
    }

    @Override
    public Room map(String str) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy");
            String[] array = str.split(",");
            return new Room(Long.parseLong(array[0].trim()), Integer.parseInt(array[1].trim()), Double.parseDouble(array[2].trim()), Boolean.parseBoolean(array[3].trim()), Boolean.parseBoolean(array[4].trim()), format.parse(array[5].trim()), Integer.parseInt(array[6].trim()));
        }catch (ParseException e){
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String reverseMap(Room room) {
        return room.getId() + "," + room.getNumberOfGuests() + "," + room.getPrice() + "," + room.isBreakfastIncluded() + "," + room.isPetsAllowed() + "," + format.format(room.getDateAvailableFrom()) + "," + room.getHotelId();
    }

    public RoomRepository() {
        setPath(path);
    }
}
