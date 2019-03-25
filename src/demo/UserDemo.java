package demo;

import controller.OrderController;
import controller.RoomController;
import controller.UserController;
import model.Filter;
import model.Room;
import model.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class UserDemo {
    private static UserController userController = new UserController();
    private static RoomController roomController = new RoomController();
    private static OrderController orderController = new OrderController();

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
                    register();
                    break;
                case "2":
                    login();
                    break;
                case "3":
                    logout();
                    break;
                case "4":
                    findRooms();
                    break;
                case "5":
                    bookRoom();
                    break;
                case "6":
                    cancelBooking();
                    break;
            }
        }
    }

    private static void printFunctions(){
        System.out.println("Write number of function which you want to use: ");
        System.out.println("1. Register user");
        System.out.println("2. Login");
        System.out.println("3. Logout");
        System.out.println("4. Find room");
        System.out.println("5. Book room");
        System.out.println("6. Cancel booking");
        System.out.println("0. To end program");
    }

    private static void register() throws Exception{
        Scanner sc = new Scanner(System.in);
        System.out.println("Write your user name: "); String registerUserName = sc.next();
        System.out.println("Write your user password: "); String registerPassword = sc.next();
        userController.registerUser(new User(registerUserName,registerPassword));
    }

    private static void login() throws Exception{
        Scanner sc = new Scanner(System.in);
        System.out.println("Write your user name: "); String userName = sc.next();
        System.out.println("Write your user password: "); String password = sc.next();
        userController.login(userName,password);
    }

    private static void logout(){
        userController.logout();
        System.out.println("Logout is completed.");
    }

    private static void findRooms() throws Exception{
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat format=new SimpleDateFormat("dd-mm-yyyy");
        System.out.println("Write number of guests: "); int numberOfGuests = Integer.parseInt(sc.next());
        System.out.println("Do you need breakfast? (write true or false) : "); boolean breakfast = Boolean.parseBoolean(sc.next());
        System.out.println("Do you have pet? (write true or false) : "); boolean pets = Boolean.parseBoolean(sc.next());
        System.out.println("From which date (write in dd-mm-yyyy format): "); String date = sc.next(); Date dateFrom = format.parse(date);
        System.out.println("Write country: "); String country = sc.next();
        System.out.println("Write city: "); String city = sc.next();
        for (Room el : roomController.findRooms(new Filter(numberOfGuests,breakfast,pets,dateFrom,country,city))){
            System.out.println(el.toString());
        }
    }

    private static void bookRoom() throws Exception{
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat format=new SimpleDateFormat("dd-mm-yyyy");
        System.out.println("Write room id which you want book: "); long roomId = Long.parseLong(sc.next());
        System.out.println("From which date (write in dd-mm-yyyy format): "); String dateF = sc.next(); Date dateFrom = format.parse(dateF);
        System.out.println("Write for which days you want book room: "); int days = Integer.parseInt(sc.next());
        orderController.bookRoom(roomId,dateFrom,days);
        System.out.println("You book room with "+ roomId + " id");
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