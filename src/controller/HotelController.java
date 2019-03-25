package controller;

import model.Hotel;
import service.HotelService;

public class HotelController {
    private UserController userController = new UserController();
    private HotelService hotelService = new HotelService();

    public void addHotel(Hotel hotel) throws Exception{
        userController.validateLoginAdmin();
        hotelService.addHotel(hotel);
    }

    public void deleteHotel(long id) throws Exception {
        userController.validateLoginAdmin();
        hotelService.deleteHotel(id);
    }
}
