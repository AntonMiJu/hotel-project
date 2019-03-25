package service;


import controller.UserController;
import model.User;
import repository.UserRepository;

public class UserService {
    private UserRepository userRepository = new UserRepository();

    public User registerUser(User user) throws Exception {
        for (User el : userRepository.readFile()) {
            if (el != null && el.getUserName().equals(user.getUserName())) {
                throw new Exception("User name is already exist.");
            }
        }
        return userRepository.registerUser(user);
    }

    public void login(String userName, String password) throws Exception {
        if (UserController.currentUser != null) {
            throw new Exception("User with id " + UserController.currentUser.getId() + " already signed");
        }
        for (User el : userRepository.readFile()) {
            if (el != null && el.getUserName().equals(userName) && el.getPassword().equals(password)) {
                UserController.currentUser = el;
                System.out.println("Login completed successfully");
            }
        }
    }

    public User findUserById(long id) {
        return userRepository.findUserById(id);
    }
}
