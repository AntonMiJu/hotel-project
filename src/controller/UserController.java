package controller;

import model.*;
import service.UserService;

public class UserController {
    private UserService userService = new UserService();

    public static User currentUser = null;

    public User registerUser(User user) throws Exception{
        return userService.registerUser(user);
    }

    public void login(String userName, String password) throws Exception {
        userService.login(userName, password);
    }

    public void logout() {
        currentUser = null;
    }

    public void validateLogin() throws Exception{
        if (currentUser == null)
            throw new Exception("You must be signed");
    }

    public void validateLoginAdmin() throws Exception{
        if (currentUser == null || !(currentUser.getUserType().equals(UserType.valueOf("ADMIN"))))
            throw new Exception("You must have UserType = ADMIN");
    }
}
