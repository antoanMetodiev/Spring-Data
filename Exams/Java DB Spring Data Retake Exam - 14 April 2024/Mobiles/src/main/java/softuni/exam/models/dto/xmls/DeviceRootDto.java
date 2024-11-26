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
    private List<DeviceDto> deviceDtoList;

    public DeviceRootDto(List<DeviceDto> deviceDtoList) {
        this.deviceDtoList = deviceDtoList;
    }

    public DeviceRootDto() {
    }

    public List<DeviceDto> getDeviceDtoList() {
        return deviceDtoList;
    }

    public void setDeviceDtoList(List<DeviceDto> deviceDtoList) {
        this.deviceDtoList = deviceDtoList;
    }
}
