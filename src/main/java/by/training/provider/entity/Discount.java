package by.training.provider.entity;

import java.sql.Date;

/**
 * The Class Discount.
 */
public class Discount extends Entity{
    
    /** The name. */
    private String name;
    
    /** The tariff. */
    private Tariff tariff;
    
    /** The discount value. */
    private int discountValue;
    
    /** The description. */
    private String description;
    
    /** The beginning date. */
    private Date beginningDate;
    
    /** The end date. */
    private Date endDate;

    /**
     * Instantiates a new discount.
     *
     * @param name the name
     * @param discount the discount
     * @param description the description
     * @param beginningDate the beginning date
     * @param endDate the end date
     * @param tariff the tariff
     */
    public Discount(String name, int discount, String description, Date beginningDate, Date endDate, Tariff tariff){
        this.name = name;
        this.tariff = tariff;
        this.discountValue = discount;
        this.description = description;
        this.beginningDate = beginningDate;
        this.endDate = endDate;
    }

    /**
     * Instantiates a new discount.
     *
     * @param name the name
     */
    public Discount(String name){
        this.name = name;
    }

    /**
     * Instantiates a new discount.
     */
    public Discount() {
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
     * Gets the discount value.
     *
     * @return the discount value
     */
    public int getDiscountValue() {
        return discountValue;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param description the new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the beginning date.
     *
     * @return the beginning date
     */
    public Date getBeginningDate() {
        return beginningDate;
    }

    /**
     * Gets the end date.
     *
     * @return the end date
     */
    public Date getEndDate() {
        return endDate;
    }
}
