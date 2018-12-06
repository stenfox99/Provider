package by.training.provider.entity;

import java.util.Objects;

public class UserType extends Entity{
    private int userTypeId;
    private String userType;

    public UserType() {
    }

    public UserType(int userTypeId){
        this.userTypeId = userTypeId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserType userType1 = (UserType) o;
        return userTypeId == userType1.userTypeId &&
                Objects.equals(userType, userType1.userType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userTypeId, userType);
    }

    @Override
    public String toString() {
        return "UserType{" +
                "userTypeId=" + userTypeId +
                ", userType='" + userType + '\'' +
                '}';
    }
}
