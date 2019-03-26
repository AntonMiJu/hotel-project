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

    public void deleteHotel(long id) {
        roomRepository.deleteRoomsByHotel(id);
        hotelRepository.deleteHotel(id);
    }

    public Hotel findHotelById(long id) {
        for (Hotel el : hotelRepository.readFile()) {
            if (el.getId() == id)
                return el;
        }
        return null;
    }
}
