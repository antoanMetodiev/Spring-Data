package softuni.exam.models.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name = "jobs")
public class Job extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @Min(value = 300)
    private double salary;

    @Column(nullable = false)
    @Min(value = 10)
    private double hoursAWeek;

    @Column(columnDefinition = "TEXT", nullable = false)
    @Size(min = 5)
    private String description;

    @ManyToOne
    private Company company;

    public Job(String title, double salary, double hoursAWeek, String description, Company company) {
        this.title = title;
        this.salary = salary;
        this.hoursAWeek = hoursAWeek;
        this.description = description;
        this.company = company;
    }

    public Job() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Min(value = 300)
    public double getSalary() {
        return salary;
    }

    public void setSalary(@Min(value = 300) double salary) {
        this.salary = salary;
    }

    @Min(value = 10)
    public double getHoursAWeek() {
        return hoursAWeek;
    }

    public void setHoursAWeek(@Min(value = 10) double hoursAWeek) {
        this.hoursAWeek = hoursAWeek;
    }

    public @Size(min = 5) String getDescription() {
        return description;
    }

    public void setDescription(@Size(min = 5) String description) {
        this.description = description;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
