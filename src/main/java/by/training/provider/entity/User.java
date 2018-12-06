package by.training.provider.entity;

import java.util.Objects;

public class User extends Entity{
    private int userId;
    private String login;
    private String password;
    private UserType userType;

    public User() {
    }

    public User(int userId, String login, String password, UserType userType) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.userType = userType;
    }

    public User(int userId, String login, UserType userType) {
        this.userId = userId;
        this.login = login;
        this.userType = userType;
    }

    public User(String login, String password){
        this.login = login;
        this.password = password;
    }

    public User(int userId, String password){
        this.userId = userId;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(userType, user.userType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, login, password, userType);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", userType=" + userType +
                '}';
    }
}
