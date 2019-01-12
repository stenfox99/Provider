package by.training.provider.entity;

import java.math.BigDecimal;

/**
 * The Class Tariff.
 */
public class Tariff extends Entity{
    
    /** The tariff id. */
    private int tariffId;
    
    /** The name. */
    private String name;
    
    /** The price. */
    private BigDecimal price;
    
    /** The price with discount. */
    private BigDecimal priceWithDiscount;
    
    /** The month traffic. */
    private int monthTraffic;
    
    /** The description. */
    private String description;

    /**
     * Instantiates a new tariff.
     *
     * @param tariffId the tariff id
     * @param name the name
     * @param price the price
     * @param priceWithDiscount the price with discount
     * @param monthTraffic the month traffic
     * @param description the description
     */
    public Tariff(int tariffId, String name, BigDecimal price, BigDecimal priceWithDiscount, int monthTraffic, String description) {
        this.tariffId = tariffId;
        this.name = name;
        this.price = price;
        this.priceWithDiscount = priceWithDiscount;
        this.monthTraffic = monthTraffic;
        this.description = description;
    }

    /**
     * Instantiates a new tariff.
     *
     * @param name the name
     * @param price the price
     * @param monthTraffic the month traffic
     * @param description the description
     */
    public Tariff(String name, BigDecimal price, int monthTraffic, String description){
        this.name = name;
        this.price = price;
        this.monthTraffic = monthTraffic;
        this.description = description;
    }

    /**
     * Instantiates a new tariff.
     *
     * @param name the name
     * @param price the price
     * @param priceWithDiscount the price with discount
     * @param monthTraffic the month traffic
     */
    public Tariff(String name, BigDecimal price, BigDecimal priceWithDiscount, int monthTraffic){
        this.name = name;
        this.price = price;
        this.priceWithDiscount = priceWithDiscount;
        this.monthTraffic = monthTraffic;
    }

    /**
     * Instantiates a new tariff.
     *
     * @param name the name
     */
    public Tariff(String name){
        this.name = name;
    }

    /**
     * Instantiates a new tariff.
     *
     * @param tariffId the tariff id
     * @param name the name
     */
    public Tariff(int tariffId, String name){
        this.tariffId = tariffId;
        this.name = name;
    }

    /**
     * Instantiates a new tariff.
     */
    public Tariff() {
    }

    /**
     * Gets the tariff id.
     *
     * @return the tariff id
     */
    public int getTariffId() {
        return tariffId;
    }

    /**
     * Sets the tariff id.
     *
     * @param tariffId the new tariff id
     */
    public void setTariffId(int tariffId) {
        this.tariffId = tariffId;
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
     * Gets the price.
     *
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
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
     * Gets the month traffic.
     *
     * @return the month traffic
     */
    public int getMonthTraffic() {
        return monthTraffic;
    }

    /**
     * Gets the price with discount.
     *
     * @return the price with discount
     */
    public BigDecimal getPriceWithDiscount() {
        return priceWithDiscount;
    }
}
