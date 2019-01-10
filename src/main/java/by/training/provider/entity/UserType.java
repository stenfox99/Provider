package by.training.provider.entity;

public class UserType extends Entity {
    private int userTypeId;
    private String userTypeValue;

    public UserType(int userTypeId) {
        this.userTypeId = userTypeId;
    }

    public UserType(int userTypeId, String userType) {
        this.userTypeId = userTypeId;
        this.userTypeValue = userType;
    }

    public int getUserTypeId() {
        return userTypeId;
    }

    public String getUserTypeValue() {
        return userTypeValue;
    }
}
