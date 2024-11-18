package softuni.exam.models.dto.xmls;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "jobs")
@XmlAccessorType(XmlAccessType.FIELD)
public class JobRootDto implements Serializable {

    @XmlElement(name = "job")
    private List<JobSeedDto> jobSeedDtoList;

    public JobRootDto(List<JobSeedDto> jobSeedDtoList) {
        this.jobSeedDtoList = jobSeedDtoList;
    }

    public JobRootDto() {
    }

    public List<JobSeedDto> getJobSeedDtoList() {
        return jobSeedDtoList;
    }

    public void setJobSeedDtoList(List<JobSeedDto> jobSeedDtoList) {
        this.jobSeedDtoList = jobSeedDtoList;
    }
}
