package by.training.provider.entity;

/**
 * The Class UserType.
 */
public class UserType extends Entity {
    
    /** The user type id. */
    private int userTypeId;
    
    /** The user type value. */
    private String userTypeValue;

    /**
     * Instantiates a new user type.
     *
     * @param userTypeId the user type id
     */
    public UserType(int userTypeId) {
        this.userTypeId = userTypeId;
    }

    /**
     * Instantiates a new user type.
     *
     * @param userTypeId the user type id
     * @param userType the user type
     */
    public UserType(int userTypeId, String userType) {
        this.userTypeId = userTypeId;
        this.userTypeValue = userType;
    }

    /**
     * Gets the user type id.
     *
     * @return the user type id
     */
    public int getUserTypeId() {
        return userTypeId;
    }

    /**
     * Gets the user type value.
     *
     * @return the user type value
     */
    public String getUserTypeValue() {
        return userTypeValue;
    }
}
