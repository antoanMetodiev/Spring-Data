package softuni.exam.models.dto.xmls;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "devices")
@XmlAccessorType(XmlAccessType.FIELD)
public class DeviceRootDto {

    @XmlElement(name = "device")
    private List<DeviceSeedDto> deviceSeedDtoList;

    public DeviceRootDto(List<DeviceSeedDto> deviceSeedDtoList) {
        this.deviceSeedDtoList = deviceSeedDtoList;
    }

    public DeviceRootDto() {
    }

    public List<DeviceSeedDto> getDeviceSeedDtoList() {
        return deviceSeedDtoList;
    }

    public void setDeviceSeedDtoList(List<DeviceSeedDto> deviceSeedDtoList) {
        this.deviceSeedDtoList = deviceSeedDtoList;
    }
}
