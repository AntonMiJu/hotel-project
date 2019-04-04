package demo;

import controller.HotelController;
import controller.OrderController;
import controller.RoomController;
import controller.UserController;
import model.Hotel;
import model.Order;
import model.Room;
import repository.HotelRepository;
import repository.OrderRepository;
import repository.RoomRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class AdminDemo {
    private static UserController userController = new UserController();
    private static RoomRepository roomRepository = new RoomRepository();
    private static RoomController roomController = new RoomController();
    private static OrderRepository orderRepository = new OrderRepository();
    private static OrderController orderController = new OrderController();
    private static HotelRepository hotelRepository = new HotelRepository();
    private static HotelController hotelController = new HotelController();

    public static void main(String[] args) throws Exception {
        menu();
    }

    private static void menu() throws Exception {
        while (true) {
            printFunctions();
            Scanner sc = new Scanner(System.in);
            String n = sc.next();
            switch (n) {
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
                    printFunctionsHotel();
                    String nHotel = sc.next();
                    switch (nHotel){
                        case "1":
                            findHotels();
                            break;
                        case "2":
                            addHotel();
                            break;
                        case "3":
                            deleteHotel();
                            break;
                        case "0":
                            break;
                    }
                    break;
                case "4":
                    printFunctionsRoom();
                    String nRoom = sc.next();
                    switch (nRoom){
                        case "1":
                            findRooms();
                            break;
                        case "2":
                            addRoom();
                            break;
                        case "3":
                            deleteRoom();
                            break;
                        case "0":
                            break;
                    }
                    break;
                case "5":
                    printFunctionsOrder();
                    String nOrder = sc.next();
                    switch (nOrder){
                        case "1":
                            findOrders();
                            break;
                        case "2":
                            cancelBooking();
                            break;
                        case "0":
                            break;
                    }
                    break;
            }
        }
    }

    private static void printFunctions() {
        System.out.println("To use any function you must login and have ADMIN user type.");
        System.out.println("Write number of function which you want to use: ");
        System.out.println("1. Login");
        System.out.println("2. Logout");
        System.out.println("3. Work with Hotels");
        System.out.println("4. Work with Rooms");
        System.out.println("5. Work with Orders");
        System.out.println("0. To end program");
        System.out.println();
    }

    private static void printFunctionsRoom() {
        System.out.println("Write number of function which you want to use: ");
        System.out.println("1. Find all rooms");
        System.out.println("2. Add room");
        System.out.println("3. Delete room");
        System.out.println("0. To close this menu");
        System.out.println();
    }

    private static void printFunctionsHotel() {
        System.out.println("Write number of function which you want to use: ");
        System.out.println("1. Find all hotels");
        System.out.println("2. Add hotel");
        System.out.println("3. Delete hotel");
        System.out.println("0. To close this menu");
        System.out.println();
    }

    private static void printFunctionsOrder() {
        System.out.println("Write number of function which you want to use: ");
        System.out.println("1. Find all orders");
        System.out.println("2. Cancel booking");
        System.out.println("0. To close this menu");
        System.out.println();
    }


    private static void login() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Write your user name: ");
        String userName = sc.next();
        System.out.println("Write your user password: ");
        String password = sc.next();
        userController.login(userName, password);
        userController.validateLoginAdmin();
        System.out.println();
    }

    private static void logout() {
        userController.logout();
        System.out.println("Logout is completed.");
        System.out.println();
    }

    private static void findRooms() {
        System.out.println("List of rooms: ");
        for (Room el : roomRepository.readFile()) {
            System.out.println(el.toString());
        }
        System.out.println();
    }

    private static void findHotels() {
        System.out.println("List of all hotels: ");
        for (Hotel el : hotelRepository.readFile()) {
            System.out.println(el.toString());
        }
        System.out.println();
    }

    private static void findOrders() {
        System.out.println("List of all orders: ");
        for (Order el : orderRepository.readFile()) {
            System.out.println(el.toString());
        }
        System.out.println();
    }

    private static void addRoom() throws Exception {
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy");
        System.out.println("Write number of guests: ");
        int numberOfGuests = Integer.parseInt(sc.next());
        System.out.println("Write price: ");
        double price = Double.parseDouble(sc.next());
        System.out.println("Do you have breakfast included in this room? (write true or false) : ");
        boolean breakfast = Boolean.parseBoolean(sc.next());
        System.out.println("Do you allow pets? (write true or false) : ");
        boolean pets = Boolean.parseBoolean(sc.next());
        System.out.println("From which date (write in dd-mm-yyyy format): ");
        String date = sc.next();
        Date dateFrom = format.parse(date);
        System.out.println("Write id of Hotel: ");
        int id = Integer.parseInt(sc.next());
        roomController.addRoom(new Room(numberOfGuests, price, breakfast, pets, dateFrom, id));
        System.out.println("Room added.");
        System.out.println();
    }

    private static void deleteRoom() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Write room id which you want delete: ");
        long roomId = Long.parseLong(sc.next());
        roomController.deleteRoom(roomId);
        System.out.println("Room deleted.");
        System.out.println();
    }

    private static void addHotel() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Write name of Hotel: ");
        String name = sc.next();
        System.out.println("Write country: ");
        String country = sc.next();
        System.out.println("Write city: ");
        String city = sc.next();
        System.out.println("Write street: ");
        String street = sc.next();
        hotelController.addHotel(new Hotel(name, country, city, street));
        System.out.println("Hotel added");
        System.out.println();
    }

    private static void deleteHotel() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Write hotel id which you want delete: ");
        long hotelId = Long.parseLong(sc.next());
        hotelController.deleteHotel(hotelId);
        System.out.println("Hotel deleted.");
        System.out.println();
    }

    private static void cancelBooking() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Write room id for which you want cancel booking: ");
        long roomId = Long.parseLong(sc.next());
        orderController.cancelReservation(roomId);
        System.out.println("You cancel booking room with " + roomId + " id");
        System.out.println();
    }

    private static void endProgram() {
        System.exit(0);
    }
}
