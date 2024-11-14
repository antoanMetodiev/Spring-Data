package softuni.exam.models.dto.xmls;

import softuni.exam.models.entity.DeviceType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "device")
@XmlAccessorType(XmlAccessType.FIELD)
public class DeviceDto implements Serializable {

    @XmlElement
    @Size(min = 2, max = 20)
    @NotNull
    private String brand;

    @XmlElement(name = "device_type")
    @Enumerated(EnumType.STRING)
    private DeviceType device_type;

    @XmlElement
    @Size(min = 1, max = 20)
    @NotNull
    private String model;

    @XmlElement
    @Min(value = 1)
    private double price;

    @XmlElement
    @Min(value = 1)
    private int storage;

    @XmlElement
    private long sale_id;

    public DeviceDto(String brand, DeviceType device_type, String model, double price, int storage, long sale_id) {
        this.brand = brand;
        this.device_type = device_type;
        this.model = model;
        this.price = price;
        this.storage = storage;
        this.sale_id = sale_id;
    }

    public DeviceDto() {
    }

    public @Size(min = 2, max = 20) @NotNull String getBrand() {
        return brand;
    }

    public void setBrand(@Size(min = 2, max = 20) @NotNull String brand) {
        this.brand = brand;
    }

    public DeviceType getDevice_type() {
        return device_type;
    }

    public void setDevice_type(DeviceType device_type) {
        this.device_type = device_type;
    }

    public @Size(min = 1, max = 20) @NotNull String getModel() {
        return model;
    }

    public void setModel(@Size(min = 1, max = 20) @NotNull String model) {
        this.model = model;
    }

    @Min(value = 1)
    public double getPrice() {
        return price;
    }

    public void setPrice(@Min(value = 1) double price) {
        this.price = price;
    }

    @Min(value = 1)
    public int getStorage() {
        return storage;
    }

    public void setStorage(@Min(value = 1) int storage) {
        this.storage = storage;
    }

    public long getSale_id() {
        return sale_id;
    }

    public void setSale_id(long sale_id) {
        this.sale_id = sale_id;
    }
}
