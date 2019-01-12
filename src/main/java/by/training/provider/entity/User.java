package by.training.provider.entity;

/**
 * The Class User.
 */
public class User extends Entity {
    
    /** The user id. */
    private int userId;
    
    /** The login. */
    private String login;
    
    /** The password. */
    private String password;
    
    /** The user type. */
    private UserType userType;
    
    /** The ban. */
    private boolean ban;

    /**
     * Instantiates a new user.
     */
    public User() {
    }

    /**
     * Instantiates a new user.
     *
     * @param userId the user id
     * @param login the login
     * @param password the password
     * @param userType the user type
     */
    public User(int userId, String login, String password, UserType userType) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.userType = userType;
    }

    /**
     * Instantiates a new user.
     *
     * @param userId the user id
     * @param login the login
     * @param userType the user type
     * @param ban the ban
     */
    public User(int userId, String login, UserType userType, boolean ban) {
        this.userId = userId;
        this.login = login;
        this.userType = userType;
        this.ban = ban;
    }

    /**
     * Instantiates a new user.
     *
     * @param login the login
     * @param password the password
     * @param userType the user type
     */
    public User(String login, String password, UserType userType) {
        this.login = login;
        this.userType = userType;
        this.password = password;
    }

    /**
     * Instantiates a new user.
     *
     * @param login the login
     * @param password the password
     */
    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    /**
     * Instantiates a new user.
     *
     * @param userId the user id
     * @param password the password
     */
    public User(int userId, String password) {
        this.userId = userId;
        this.password = password;
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
     * Gets the login.
     *
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets the login.
     *
     * @param login the new login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the user type.
     *
     * @return the user type
     */
    public UserType getUserType() {
        return userType;
    }

    /**
     * Checks if is ban.
     *
     * @return true, if is ban
     */
    public boolean isBan() {
        return ban;
    }
}
