package softuni.exam.models.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name = "devices")
public class Device extends BaseEntity{

    @Column(nullable = false)
//    @Size(min = 2, max = 20)
    private String brand;

    @Enumerated(EnumType.STRING)
    @Column(name = "device_type")
    private DeviceType deviceType;

    @Column(unique = true, nullable = false)
//    @Size(min = 1, max = 20)
    private String model;

    @Column
    @Min(1)
    private double price;

    @Column
    @Min(1)
    private int storage;

    @ManyToOne
    @JoinColumn(name= "sale_id", referencedColumnName = "id")
    private Sale sale;

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

    @Min(1)
    public double getPrice() {
        return price;
    }

    public void setPrice(@Min(1) double price) {
        this.price = price;
    }

    @Min(1)
    public int getStorage() {
        return storage;
    }

    public void setStorage(@Min(1) int storage) {
        this.storage = storage;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }
}
