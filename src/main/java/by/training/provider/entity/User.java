package by.training.provider.entity;

public class User {
    private int userId;
    private String login;
    private String password;
    private int userTypeId;
    private int userDataId;

    public User() {
    }

    public User(int userId, String login, String password, int userTypeId, int userDataId) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.userTypeId = userTypeId;
        this.userDataId = userDataId;
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

    public int getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(int userTypeId) {
        this.userTypeId = userTypeId;
    }

    public int getUserDataId() {
        return userDataId;
    }

    public void setUserDataId(int userDataId) {
        this.userDataId = userDataId;
    }
}
