package softuni.exam.models.dto.xmls;

import softuni.exam.util.adapters.LocalDateXmlAdapter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;

@XmlRootElement(name = "company")
@XmlAccessorType(XmlAccessType.FIELD)
public class CompanySeedDto implements Serializable {

    @XmlElement
    @NotNull
    @Size(min = 2, max = 40)
    private String companyName;

    @XmlElement
    @NotNull
    @Size(min = 2, max = 30)
    private String website;

    @XmlElement
    @NotNull
    @XmlJavaTypeAdapter(LocalDateXmlAdapter.class)
    private LocalDate dateEstablished;

    @XmlElement
    private long countryId;

    public CompanySeedDto(String companyName, String website, LocalDate dateEstablished, long countryId) {
        this.companyName = companyName;
        this.website = website;
        this.dateEstablished = dateEstablished;
        this.countryId = countryId;
    }

    public CompanySeedDto() {
    }

    public @NotNull @Size(min = 2, max = 40) String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(@NotNull @Size(min = 2, max = 40) String companyName) {
        this.companyName = companyName;
    }

    public @NotNull @Size(min = 2, max = 30) String getWebsite() {
        return website;
    }

    public void setWebsite(@NotNull @Size(min = 2, max = 30) String website) {
        this.website = website;
    }

    public @NotNull LocalDate getDateEstablished() {
        return dateEstablished;
    }

    public void setDateEstablished(@NotNull LocalDate dateEstablished) {
        this.dateEstablished = dateEstablished;
    }

    public long getCountryId() {
        return countryId;
    }

    public void setCountryId(long countryId) {
        this.countryId = countryId;
    }
}
