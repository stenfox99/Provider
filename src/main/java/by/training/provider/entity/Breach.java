package by.training.provider.entity;

public class Breach extends Entity{
    private int breachId;
    private String description;
    private int userId;

    public Breach(int breachId, String description, int userId) {
        this.breachId = breachId;
        this.description = description;
        this.userId = userId;
    }

    public Breach() {}

    public int getBreachId() {
        return breachId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
