package softuni.exam.models.dto;

import softuni.exam.util.adapters.LocalDateAdapterXML;

import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;

@XmlRootElement(name = "volcanologist")
@XmlAccessorType(XmlAccessType.FIELD)
public class VolcanologistSeedDto implements Serializable {

    @XmlElement(name = "first_name")
    @Size(min = 2, max = 30)
    @NotNull
    private String firstName;

    @XmlElement(name = "last_name")
    @Size(min = 2, max = 30)
    @NotNull
    private String lastName;

    @XmlElement
    @Positive
    @NotNull
    private double salary;

    @XmlElement
    @Min(value = 18)
    @Max(value = 80)
    @NotNull
    private int age;

    @XmlElement(name = "exploring_from")
    @XmlJavaTypeAdapter(LocalDateAdapterXML.class)
    private LocalDate exploringFrom;

    @XmlElement(name = "exploring_volcano_id")
    private long exploringVolcanoId;

    public VolcanologistSeedDto(String firstName, String lastName, double salary, int age, LocalDate exploringFrom, long exploringVolcanoId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.age = age;
        this.exploringFrom = exploringFrom;
        this.exploringVolcanoId = exploringVolcanoId;
    }

    public VolcanologistSeedDto() {

    }

    public @Size(min = 2, max = 30) @NotNull String getFirstName() {
        return firstName;
    }

    public void setFirstName(@Size(min = 2, max = 30) @NotNull String firstName) {
        this.firstName = firstName;
    }

    public @Size(min = 2, max = 30) @NotNull String getLastName() {
        return lastName;
    }

    public void setLastName(@Size(min = 2, max = 30) @NotNull String lastName) {
        this.lastName = lastName;
    }

    @Positive
    @NotNull
    public double getSalary() {
        return salary;
    }

    public void setSalary(@Positive @NotNull double salary) {
        this.salary = salary;
    }

    @Min(value = 18)
    @Max(value = 80)
    @NotNull
    public int getAge() {
        return age;
    }

    public void setAge(@Min(value = 18) @Max(value = 80) @NotNull int age) {
        this.age = age;
    }

    public LocalDate getExploringFrom() {
        return exploringFrom;
    }

    public void setExploringFrom(LocalDate exploringFrom) {
        this.exploringFrom = exploringFrom;
    }

    public long getExploringVolcanoId() {
        return exploringVolcanoId;
    }

    public void setExploringVolcanoId(long exploringVolcanoId) {
        this.exploringVolcanoId = exploringVolcanoId;
    }
}
