package service;

import controller.UserController;
import model.User;
import repository.UserRepository;

public class UserService {
    private UserRepository userRepository = new UserRepository();
//    private UserController userController = new UserController();

    public User registerUser(User user) throws Exception{
        User[] users = (User[]) userRepository.readFile().toArray();
        for (User el : users){
            if (el.getUserName().equals(user.getUserName())){
                System.out.println("User name is already exist.");
                return null;
            }
        }
        return userRepository.registerUser(user);
    }

    public void login(String userName, String password) throws Exception{
        if (UserController.currentUser!=null){
            throw new Exception("User with id "+UserController.currentUser.getId()+" already signed");
        }
        for (User el : userRepository.readFile()){
            if (el.getUserName().equals(userName) && el.getPassword().equals(password)){
                UserController.currentUser = el;
                System.out.println("Login completed successfully");
            }
        }
    }

    public User findUserById(long id) throws Exception{
        User[] users = (User[]) userRepository.readFile().toArray();
        for (User el : users){
            if (el.getId() == id)
                return el;
        }
        return null;
    }
}
