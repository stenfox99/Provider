package by.training.provider.entity;

import java.math.BigDecimal;

public class Tariff extends Entity{
    private int tariffId;
    private String name;
    private BigDecimal price;
    private int monthTraffic;
    private String description;

    public Tariff(int tariffId, String name, BigDecimal price, int monthTraffic, String description) {
        this.tariffId = tariffId;
        this.name = name;
        this.price = price;
        this.monthTraffic = monthTraffic;
        this.description = description;
    }

    public Tariff(String name, BigDecimal price, int monthTraffic, String description){
        this.name = name;
        this.price = price;
        this.monthTraffic = monthTraffic;
        this.description = description;
    }

    public Tariff(String name){
        this.name = name;
    }

    public Tariff() {
    }

    public int getTariffId() {
        return tariffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMonthTraffic() {
        return monthTraffic;
    }

    public void setMonthTraffic(int monthTraffic) {
        this.monthTraffic = monthTraffic;
    }
}
