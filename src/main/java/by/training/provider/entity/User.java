package by.training.provider.entity;

public class User extends Entity {
    private int userId;
    private String login;
    private String password;
    private UserType userType;
    private boolean ban;

    public User() {
    }

    public User(int userId, String login, String password, UserType userType) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.userType = userType;
    }

    public User(int userId, String login, UserType userType, boolean ban) {
        this.userId = userId;
        this.login = login;
        this.userType = userType;
        this.ban = ban;
    }

    public User(String login, String password, UserType userType) {
        this.login = login;
        this.userType = userType;
        this.password = password;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(int userId, String password) {
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

    public boolean isBan() {
        return ban;
    }
}
