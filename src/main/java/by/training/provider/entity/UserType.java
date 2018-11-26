package by.training.provider.entity;

public class UserType {
    private int userTypeId;
    private String userType;

    public UserType() {
    }

    public UserType(int userTypeId, String userType) {
        this.userTypeId = userTypeId;
        this.userType = userType;
    }

    public int getUserTypeId() {
        return userTypeId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
