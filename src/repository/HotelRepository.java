package repository;

import model.Hotel;

import java.util.ArrayList;

public class HotelRepository extends GeneralRepository<Hotel> {
    private String path = "/home/anton/HotelDB.txt";
    public ArrayList<Hotel> readFile() throws Exception{
        return readFromFile();
    }

    public void addHotel(Hotel hotel) {
        addObject(hotel);
    }

    public void deleteHotel(long id) throws Exception{
        deleteObject(id);
    }

    @Override
    public Hotel map(String str) throws Exception{
        String[] array = str.split(",");
        return new Hotel(Long.parseLong(array[0].trim()), array[1].trim(), array[2].trim(), array[3].trim(), array[4].trim());
    }

    public HotelRepository() {
        setPath(path);
    }
}
