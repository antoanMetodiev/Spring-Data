package softuni.exam.models.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "companies")
public class Company extends BaseEntity{

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String website;

    @Column(name = "date_established", nullable = false)
    private LocalDate dateEstablished;

    @Column(nullable = false)
    @OneToMany
    private Set<Job> jobs;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    public Company(String name, String website, LocalDate dateEstablished, Set<Job> jobs, Country country) {
        this.name = name;
        this.website = website;
        this.dateEstablished = dateEstablished;
        this.jobs = jobs;
        this.country = country;
    }

    public Company() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public LocalDate getDateEstablished() {
        return dateEstablished;
    }

    public void setDateEstablished(LocalDate dateEstablished) {
        this.dateEstablished = dateEstablished;
    }

    public Set<Job> getJobs() {
        return jobs;
    }

    public void setJobs(Set<Job> jobs) {
        this.jobs = jobs;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
