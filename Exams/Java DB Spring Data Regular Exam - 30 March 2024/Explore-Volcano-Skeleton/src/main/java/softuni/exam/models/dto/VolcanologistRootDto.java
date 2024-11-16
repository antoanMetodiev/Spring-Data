package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "volcanologists") // Това трябва да бъде кореновият елемент
@XmlAccessorType(XmlAccessType.FIELD)
public class VolcanologistRootDto {

    @XmlElement(name = "volcanologist") // Елементите в списъка ще бъдат "volcanologist"
    private List<VolcanologistSeedDto> volcanologists;

    public List<VolcanologistSeedDto> getVolcanologists() {
        return volcanologists;
    }
}
