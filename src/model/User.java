package model;

import java.util.Objects;

public class User extends GeneralClass{
    private long id;
    private String userName;
    private String password;
    private UserType userType;

    public User(long id, String userName, String password, UserType userType) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.userType = userType;
    }

    public User(String userName, String password) {
        this.id = Math.abs(Objects.hash(userName,password));
        this.userName = userName;
        this.password = password;
        this.userType = UserType.USER;
    }

    public long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public UserType getUserType() {
        return userType;
    }

    @Override
    public String toString() {
        return  "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", userType=" + userType;
    }
}
