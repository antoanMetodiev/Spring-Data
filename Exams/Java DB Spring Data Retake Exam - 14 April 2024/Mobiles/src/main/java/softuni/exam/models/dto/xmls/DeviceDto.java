package softuni.exam.models.dto.xmls;

import softuni.exam.models.enums.DeviceType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "device")
@XmlAccessorType(XmlAccessType.FIELD)
public class DeviceDto {
    @XmlElement
    @NotNull
    @Size(min = 2, max = 20)
    private String brand;

    @XmlElement(name = "device_type")
    private DeviceType deviceType;

    @XmlElement
    @Size(min = 1, max = 20)
    @NotNull
    private String model;

    @XmlElement
    @Positive
    private double price;

    @XmlElement
    @Positive
    private int storage;

    @XmlElement
    private long sale_id;

    public DeviceDto(String brand, DeviceType deviceType, String model, double price, int storage, long sale_id) {
        this.brand = brand;
        this.deviceType = deviceType;
        this.model = model;
        this.price = price;
        this.storage = storage;
        this.sale_id = sale_id;
    }

    public DeviceDto() {
    }

    public @NotNull @Size(min = 2, max = 20) String getBrand() {
        return brand;
    }

    public void setBrand(@NotNull @Size(min = 2, max = 20) String brand) {
        this.brand = brand;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public @Size(min = 1, max = 20) @NotNull String getModel() {
        return model;
    }

    public void setModel(@Size(min = 1, max = 20) @NotNull String model) {
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

    public long getSale_id() {
        return sale_id;
    }

    public void setSale_id(long sale_id) {
        this.sale_id = sale_id;
    }

    public void setStorage(@Positive int storage) {
        this.storage = storage;
    }
}
