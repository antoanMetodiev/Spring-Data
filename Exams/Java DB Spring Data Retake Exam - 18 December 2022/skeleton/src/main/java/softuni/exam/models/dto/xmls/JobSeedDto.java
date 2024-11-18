package softuni.exam.models.dto.xmls;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "job")
@XmlAccessorType(XmlAccessType.FIELD)
public class JobSeedDto implements Serializable {

    @XmlElement
    @Size(min = 2, max = 40)
    @NotNull
    private String jobTitle;

    @XmlElement
    @Min(value = 10)
    @NotNull
    private double hoursAWeek;

    @XmlElement
    @Min(value = 300)
    @NotNull
    private double salary;

    @XmlElement
    @Size(min = 5)
    @NotNull
    private String description;

    @XmlElement
    private long companyId;

    public JobSeedDto(String jobTitle, double hoursAWeek, double salary, String description, long companyId) {
        this.jobTitle = jobTitle;
        this.hoursAWeek = hoursAWeek;
        this.salary = salary;
        this.description = description;
        this.companyId = companyId;
    }

    public JobSeedDto() {}

    public @Size(min = 2, max = 40) @NotNull String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(@Size(min = 2, max = 40) @NotNull String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Min(value = 10)
    @NotNull
    public double getHoursAWeek() {
        return hoursAWeek;
    }

    public void setHoursAWeek(@Min(value = 10) @NotNull double hoursAWeek) {
        this.hoursAWeek = hoursAWeek;
    }

    @Min(value = 300)
    @NotNull
    public double getSalary() {
        return salary;
    }

    public void setSalary(@Min(value = 300) @NotNull double salary) {
        this.salary = salary;
    }

    public @Size(min = 5) @NotNull String getDescription() {
        return description;
    }

    public void setDescription(@Size(min = 5) @NotNull String description) {
        this.description = description;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }
}
