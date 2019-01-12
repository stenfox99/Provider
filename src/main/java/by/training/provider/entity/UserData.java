package by.training.provider.entity;

import java.math.BigDecimal;

/**
 * The Class UserData.
 */
public class UserData extends Entity {
    
    /** The user data id. */
    private int userDataId;
    
    /** The first name. */
    private String firstName;
    
    /** The last name. */
    private String lastName;
    
    /** The patronymic. */
    private String patronymic;
    
    /** The email. */
    private String email;
    
    /** The phone. */
    private String phone;
    
    /** The tariff. */
    private Tariff tariff;
    
    /** The balance. */
    private BigDecimal balance;
    
    /** The traffic. */
    private int traffic;
    
    /** The photo. */
    private String photo;
    
    /** The user id. */
    private int userId;

    /**
     * Instantiates a new user data.
     *
     * @param userDataId the user data id
     * @param firstName the first name
     * @param lastName the last name
     * @param patronymic the patronymic
     * @param email the email
     * @param phone the phone
     * @param tariff the tariff
     * @param balance the balance
     * @param traffic the traffic
     * @param photo the photo
     * @param userId the user id
     */
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

    /**
     * Instantiates a new user data.
     *
     * @param userDataId the user data id
     */
    public UserData(int userDataId) {
        this.userDataId = userDataId;
    }

    /**
     * Instantiates a new user data.
     */
    public UserData() {
    }

    /**
     * Instantiates a new user data.
     *
     * @param firstName the first name
     * @param lastName the last name
     * @param patronymic the patronymic
     * @param email the email
     * @param phone the phone
     * @param userId the user id
     */
    public UserData(String firstName, String lastName, String patronymic, String email, String phone, int userId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.email = email;
        this.phone = phone;
        this.userId = userId;
    }

    /**
     * Gets the user data id.
     *
     * @return the user data id
     */
    public int getUserDataId() {
        return userDataId;
    }

    /**
     * Gets the first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets the patronymic.
     *
     * @return the patronymic
     */
    public String getPatronymic() {
        return patronymic;
    }

    /**
     * Gets the email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the phone.
     *
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Gets the tariff.
     *
     * @return the tariff
     */
    public Tariff getTariff() {
        return tariff;
    }

    /**
     * Sets the tariff.
     *
     * @param tariff the new tariff
     */
    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    /**
     * Gets the balance.
     *
     * @return the balance
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * Sets the balance.
     *
     * @param balance the new balance
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * Gets the traffic.
     *
     * @return the traffic
     */
    public int getTraffic() {
        return traffic;
    }

    /**
     * Sets the traffic.
     *
     * @param traffic the new traffic
     */
    public void setTraffic(int traffic) {
        this.traffic = traffic;
    }

    /**
     * Gets the photo.
     *
     * @return the photo
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * Sets the photo.
     *
     * @param photo the new photo
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     * Gets the user id.
     *
     * @return the user id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the user id.
     *
     * @param userId the new user id
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }
}
