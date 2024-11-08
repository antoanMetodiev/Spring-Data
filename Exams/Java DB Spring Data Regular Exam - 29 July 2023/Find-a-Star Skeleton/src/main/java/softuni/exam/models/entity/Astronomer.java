package softuni.exam.models.entity;


import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Entity
@Table(name = "astronomers")
public class Astronomer extends BaseEntity {

    @Column(name = "first_name", nullable = false)
//    @Min(2)
//    @Max(30)
    private String firstName;

    @Column(name = "last_name", nullable = false)
//    @Min(2)
//    @Max(30)
    private String lastName;

//    @Min(15000)
    @Column(nullable = false)
    private double salary;

//    @Min(500)
    @Column(name = "average_observation_hours", nullable = false)
    private double averageObservationHours;

    @Column
    private LocalDate birthday;

    @ManyToOne
    @JoinColumn(name = "observing_star_id", referencedColumnName = "id")
    private Star observingStar;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getAverageObservationHours() {
        return averageObservationHours;
    }

    public void setAverageObservationHours(double averageObservationHours) {
        this.averageObservationHours = averageObservationHours;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Star getObservingStar() {
        return observingStar;
    }

    public void setObservingStar(Star observingStar) {
        this.observingStar = observingStar;
    }
}
