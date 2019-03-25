package service;

import model.Hotel;
import repository.HotelRepository;
import repository.RoomRepository;

public class HotelService {
    private HotelRepository hotelRepository = new HotelRepository();
    private RoomRepository roomRepository = new RoomRepository();

    public void addHotel(Hotel hotel) {
        hotelRepository.addHotel(hotel);
    }

    public void deleteHotel(long id) throws Exception{
        roomRepository.deleteRoomsByHotel(id);
        hotelRepository.deleteHotel(id);
    }

    public Hotel findHotelByName(String name) throws Exception{
        Hotel[] hotels = (Hotel[]) hotelRepository.readFile().toArray();
        for (Hotel el : hotels){
            if (el.getName().equals(name))
                return el;
        }
        return null;
    }

    public Hotel findHotelByCity(String city) throws Exception{
        Hotel[] hotels = (Hotel[]) hotelRepository.readFile().toArray();
        for (Hotel el : hotels){
            if (el.getCity().equals(city))
                return el;
        }
        return null;
    }

    public Hotel findHotelById(long id) throws Exception{
        Hotel[] hotels = (Hotel[]) hotelRepository.readFile().toArray();
        for (Hotel el : hotels){
            if (el.getId() == id)
                return el;
        }
        return null;
    }
}
