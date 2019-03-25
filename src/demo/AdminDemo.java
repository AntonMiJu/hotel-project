package demo;

import controller.HotelController;
import controller.OrderController;
import controller.RoomController;
import controller.UserController;
import model.Hotel;
import model.Room;
import repository.HotelRepository;
import repository.RoomRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class AdminDemo {
    private static UserController userController = new UserController();
    private static RoomRepository roomRepository = new RoomRepository();
    private static RoomController roomController = new RoomController();
    private static OrderController orderController = new OrderController();
    private static HotelRepository hotelRepository = new HotelRepository();
    private static HotelController hotelController = new HotelController();

    public static void main(String[] args) throws Exception {
        menu();
    }

    private static void menu() throws Exception{
        while (true) {
            printFunctions();
            Scanner sc = new Scanner(System.in);
            String n = sc.next();
            switch (n){
                case "0":
                    endProgram();
                    break;
                case "1":
                    login();
                    break;
                case "2":
                    logout();
                    break;
                case "3":
                    findRooms();
                    break;
                case "4":
                    findHotels();
                    break;
                case "5":
                    addRoom();
                    break;
                case "6":
                    deleteRoom();
                    break;
                case "7":
                    addHotel();
                    break;
                case "8":
                    deleteHotel();
                    break;
                case "9":
                    cancelBooking();
                    break;
            }
        }
    }

    private static void printFunctions(){
        System.out.println("Write number of function which you want to use: ");
        System.out.println("1. Login");
        System.out.println("2. Logout");
        System.out.println("3. Find all rooms");
        System.out.println("4. Find all hotels");
        System.out.println("5. Add room");
        System.out.println("6. Delete room");
        System.out.println("7. Add hotel");
        System.out.println("8. Delete hotel");
        System.out.println("9. Cancel booking");
        System.out.println("0. To end program");
    }

    private static void login() throws Exception{
        Scanner sc = new Scanner(System.in);
        System.out.println("Write your user name: "); String userName = sc.next();
        System.out.println("Write your user password: "); String password = sc.next();
        userController.validateLoginAdmin();
        userController.login(userName,password);
    }

    private static void logout(){
        userController.logout();
        System.out.println("Logout is completed.");
    }

    private static void findRooms() throws Exception{
        for (Room el : roomRepository.readFile()){
            System.out.println(el.toString());
        }
    }

    private static void findHotels() throws Exception{
        for (Hotel el : hotelRepository.readFile()){
            System.out.println(el.toString());
        }
    }

    private static void addRoom() throws Exception{
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat format=new SimpleDateFormat("dd-mm-yyyy");
        System.out.println("Write number of guests: "); int numberOfGuests = Integer.parseInt(sc.next());
        System.out.println("Write price: "); double price = Double.parseDouble(sc.next());
        System.out.println("Do you need breakfast? (write true or false) : "); boolean breakfast = Boolean.parseBoolean(sc.next());
        System.out.println("Do you have pet? (write true or false) : "); boolean pets = Boolean.parseBoolean(sc.next());
        System.out.println("From which date (write in dd-mm-yyyy format): "); String date = sc.next(); Date dateFrom = format.parse(date);
        System.out.println("Write name of Hotel: "); String name = sc.next();
        System.out.println("Write country: "); String country = sc.next();
        System.out.println("Write city: "); String city = sc.next();
        System.out.println("Write street: "); String street = sc.next();
        roomController.addRoom(new Room(numberOfGuests,price,breakfast,pets,dateFrom,new Hotel(name,country,city,street)));
    }

    private static void deleteRoom() throws Exception{
        Scanner sc = new Scanner(System.in);
        System.out.println("Write room id which you want delete: "); long roomId = Long.parseLong(sc.next());
        roomController.deleteRoom(roomId);
    }

    private static void addHotel() throws Exception{
        Scanner sc = new Scanner(System.in);
        System.out.println("Write name of Hotel: "); String name = sc.next();
        System.out.println("Write country: "); String country = sc.next();
        System.out.println("Write city: "); String city = sc.next();
        System.out.println("Write street: "); String street = sc.next();
        hotelController.addHotel(new Hotel(name,country,city,street));
    }

    private static void deleteHotel() throws Exception{
        Scanner sc = new Scanner(System.in);
        System.out.println("Write hotel id which you want delete: "); long hotelId = Long.parseLong(sc.next());
        hotelController.deleteHotel(hotelId);
    }

    private static void cancelBooking() throws Exception{
        Scanner sc = new Scanner(System.in);
        System.out.println("Write room id for which you want cancel booking: "); long roomId = Long.parseLong(sc.next());
        orderController.cancelReservation(roomId);
        System.out.println("You cancel booking room with "+ roomId + " id");
    }

    private static void endProgram(){
        System.exit(0);
    }
}
