package softuni.exam.models.entity;

import softuni.exam.models.enums.DeviceType;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "devices")
public class Device extends BaseEntity {

    @Column(nullable = false)
    private String brand;

    @Column(name = "device_type")
    @Enumerated(EnumType.STRING)
    private DeviceType deviceType;

    @Column(nullable = false, unique = true)
    private String model;

    @Column
    @Positive
    private double price;

    @Column
    @Positive
    private int storage;

    @ManyToOne
    @JoinColumn(name = "sale_id", referencedColumnName = "id")
    private Sale sale;

    public Device(String brand, DeviceType deviceType, String model, double price, int storage, Sale sale) {
        this.brand = brand;
        this.deviceType = deviceType;
        this.model = model;
        this.price = price;
        this.storage = storage;
        this.sale = sale;
    }

    public Device() {}

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Positive
    public double getPrice() {
        return price;
    }

    public void setPrice(@Positive double price) {
        this.price = price;
    }

    @Positive
    public int getStorage() {
        return storage;
    }

    public void setStorage(@Positive int storage) {
        this.storage = storage;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }
}
