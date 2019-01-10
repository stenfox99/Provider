package by.training.provider.entity;

import java.sql.Date;

public class Discount extends Entity{
    private String name;
    private Tariff tariff;
    private int discountValue;
    private String description;
    private Date beginningDate;
    private Date endDate;

    public Discount(String name, int discount, String description, Date beginningDate, Date endDate, Tariff tariff){
        this.name = name;
        this.tariff = tariff;
        this.discountValue = discount;
        this.description = description;
        this.beginningDate = beginningDate;
        this.endDate = endDate;
    }

    public Discount(String name){
        this.name = name;
    }

    public Discount() {
    }

    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    public int getDiscountValue() {
        return discountValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBeginningDate() {
        return beginningDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
