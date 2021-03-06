package repository;

import model.Hotel;

import java.util.ArrayList;

public class HotelRepository extends GeneralRepository<Hotel> {
    private String path = "/home/anton/HotelDB.txt";

    public ArrayList<Hotel> readFile() {
        return readFromFile();
    }

    public void addHotel(Hotel hotel) {
        addObject(hotel);
    }

    public void deleteHotel(long id) {
        deleteObject(id);
    }

    @Override
    public Hotel map(String str) {
        String[] array = str.split(",");
        return new Hotel(Long.parseLong(array[0].trim()), array[1].trim(), array[2].trim(), array[3].trim(), array[4].trim());
    }

    @Override
    public String reverseMap(Hotel hotel) {
        return hotel.getId() + "," + hotel.getName() + "," + hotel.getCountry() + "," + hotel.getCity() + "," + hotel.getStreet();
    }

    public HotelRepository() {
        setPath(path);
    }
}
