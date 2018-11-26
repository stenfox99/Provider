package by.training.provider.entity;

public class Discount {
    private int discountId;
    private int tariffId;
    private int discount;
    private String description;

    public Discount(int discountId, int tariffId, int discount, String description) {
        this.discountId = discountId;
        this.tariffId = tariffId;
        this.discount = discount;
        this.description = description;
    }

    public Discount() {
    }

    public int getDiscountId() {
        return discountId;
    }

    public int getTariffId() {
        return tariffId;
    }

    public void setTariffId(int tariffId) {
        this.tariffId = tariffId;
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
}
