package by.training.provider.entity;

import java.math.BigDecimal;
import java.sql.Blob;

public class UserData {
    private int userDataId;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String email;
    private String phone;
    private int tariffId;
    private BigDecimal balance;
    private int traffic;
    private boolean ban;
    private Blob photo;

    public UserData(int userDataId, String firstName, String lastName, String patronymic, String email, String phone, int tariffId, BigDecimal balance, int traffic, boolean ban, Blob photo) {
        this.userDataId = userDataId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.email = email;
        this.phone = phone;
        this.tariffId = tariffId;
        this.balance = balance;
        this.traffic = traffic;
        this.ban = ban;
        this.photo = photo;
    }

    public UserData() {
    }

    public int getUserDataId() {
        return userDataId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getTariffId() {
        return tariffId;
    }

    public void setTariffId(int tariffId) {
        this.tariffId = tariffId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public int getTraffic() {
        return traffic;
    }

    public void setTraffic(int traffic) {
        this.traffic = traffic;
    }

    public boolean isBan() {
        return ban;
    }

    public void setBan(boolean ban) {
        this.ban = ban;
    }

    public Blob getPhoto() {
        return photo;
    }

    public void setPhoto(Blob photo) {
        this.photo = photo;
    }
}
