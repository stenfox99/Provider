package by.training.provider.entity;

import java.sql.Date;

public class Discount extends Entity{
    private int discountId;
    private String name;
    private Tariff tariff;
    private int discount;
    private String description;
    private Date beginningDate;
    private Date endDate;

    public Discount(int discountId, String name, Tariff tariff, int discount, String description, Date beginningDate, Date endDate) {
        this.discountId = discountId;
        this.name = name;
        this.tariff = tariff;
        this.discount = discount;
        this.description = description;
        this.beginningDate = beginningDate;
        this.endDate = endDate;
    }

    public Discount(String name, int discount, String description, Date beginningDate, Date endDate, Tariff tariff){
        this.name = name;
        this.tariff = tariff;
        this.discount = discount;
        this.description = description;
        this.beginningDate = beginningDate;
        this.endDate = endDate;
    }

    public Discount() {
    }

    public int getDiscountId() {
        return discountId;
    }

    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
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

    public void setBeginningDate(Date beginningDate) {
        this.beginningDate = beginningDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
