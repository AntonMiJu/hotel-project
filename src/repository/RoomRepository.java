package repository;

import model.Room;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class RoomRepository extends GeneralRepository<Room>{
    private String path = "/home/anton/RoomDB.txt";
    private HotelRepository hotelRepository = new HotelRepository();

    public ArrayList<Room> readFile() throws Exception {
        return readFromFile();
    }

    public void addRoom(Room room) {
        addObject(room);
    }

    public void deleteRoom(long id) throws Exception{
        deleteObject(id);
    }

    public void deleteRoomsByHotel(long id) throws Exception{
        for (Room el : readFile()){
            if (el.getHotel().getId() == id)
                deleteRoom(el.getId());
        }
    }

    public Room findRoomById(long id) throws Exception{
        for (Room el : readFile()){
            if (el.getId() == id)
                return el;
        }
        return null;
    }

    @Override
    public Room map(String str) throws Exception{
        SimpleDateFormat format=new SimpleDateFormat("dd-mm-yyyy");
        String[] array = str.split(",");
        return new Room(Long.parseLong(array[0].trim()), Integer.parseInt(array[1].trim()),Double.parseDouble(array[2].trim()),Boolean.parseBoolean(array[3].trim()),Boolean.parseBoolean(array[4].trim()), format.parse(array[5].trim()),hotelRepository.findObjectById(Long.parseLong(array[6].trim())));
    }

    public RoomRepository() {
        setPath(path);
    }
}
