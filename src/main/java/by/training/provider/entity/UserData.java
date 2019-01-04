package by.training.provider.entity;

import java.math.BigDecimal;
import java.sql.Blob;

public class UserData extends Entity{
    private int userDataId;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String email;
    private String phone;
    private Tariff tariff;
    private BigDecimal balance;
    private int traffic;
    private String photo;
    private int userId;

    public UserData(int userDataId, String firstName, String lastName, String patronymic, String email, String phone, Tariff tariff, BigDecimal balance, int traffic, String photo, int userId) {
        this.userDataId = userDataId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.email = email;
        this.phone = phone;
        this.tariff = tariff;
        this.balance = balance;
        this.traffic = traffic;
        this.photo = photo;
        this.userId = userId;
    }

    public UserData(int userDataId){
        this.userDataId = userDataId;
    }

    public UserData() {
    }

    public UserData(String firstName, String lastName, String patronymic, String email, String phone, int userId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.email = email;
        this.phone = phone;
        this.userId = userId;
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

    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
