package repository;

import model.User;
import model.UserType;

import java.util.ArrayList;

public class UserRepository extends GeneralRepository<User> {
    private String path = "/home/anton/UserDB.txt";

    public ArrayList<User> readFile() {
        return readFromFile();
    }

    public User registerUser(User user) {
        return addObject(user);
    }

    public User findUserById(long id) {
        for (User el : readFile()){
            if (el.getId() == id)
                return el;
        }
        return null;
    }
    @Override
    public User map(String str) {
        String[] array = str.split(",");
        return new User(Long.parseLong(array[0].trim()),array[1].trim(),array[2].trim(),UserType.valueOf(array[3].trim()));
    }

    @Override
    public String reverseMap(User user) {
        return user.getId() + "," + user.getUserName() + "," + user.getPassword() + "," + user.getUserType();
    }

    public UserRepository() {
        setPath(path);
    }
}
