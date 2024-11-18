package softuni.exam.models.dto.xmls;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "companies")
@XmlAccessorType(XmlAccessType.FIELD)
public class CompanyRootDto implements Serializable {

    @XmlElement(name = "company")
    private List<CompanySeedDto> companySeedDtos;

    public CompanyRootDto(List<CompanySeedDto> companySeedDtos) {
        this.companySeedDtos = companySeedDtos;
    }

    public CompanyRootDto() {}

    public List<CompanySeedDto> getCompanySeedDtos() {
        return companySeedDtos;
    }

    public void setCompanySeedDtos(List<CompanySeedDto> companySeedDtos) {
        this.companySeedDtos = companySeedDtos;
    }
}
