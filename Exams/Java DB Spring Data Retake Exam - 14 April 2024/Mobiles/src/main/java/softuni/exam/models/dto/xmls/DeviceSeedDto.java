package softuni.exam.models.dto.xmls;

import softuni.exam.models.enums.DeviceType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "device")
@XmlAccessorType(XmlAccessType.FIELD)
public class DeviceSeedDto implements Serializable {

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

    @XmlElement(name = "sale_id")
    private long saleId;

    public DeviceSeedDto(String brand, DeviceType deviceType, String model, double price, int storage, long saleId) {
        this.brand = brand;
        this.deviceType = deviceType;
        this.model = model;
        this.price = price;
        this.storage = storage;
        this.saleId = saleId;
    }

    public DeviceSeedDto() {}

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

    public void setStorage(@Positive int storage) {
        this.storage = storage;
    }

    public long getSaleId() {
        return saleId;
    }

    public void setSaleId(long saleId) {
        this.saleId = saleId;
    }
}
