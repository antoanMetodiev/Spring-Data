package softuni.exam.models.entity;


import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "volcanologists")
public class Volcanologist extends BaseEntity{

    @Column(name = "first_name", nullable = false, unique = true)
//    @Size(min = 2, max = 30)
    private String firstName;

    @Column(name = "last_name", nullable = false, unique = true)
//    @Size(min = 2, max = 30)
    private String lastName;

    @Column(nullable = false)
    @Min(value = 1)
    private double salary;

    @Column(nullable = false)
//    @Min(value = 18)
//    @Max(value = 80)
    private int age;

    @Column
    private LocalDate exploringFrom;

    @ManyToOne()
    @JoinColumn(name = "exploring_volcano_id", referencedColumnName = "id")
    private Volcano volcano;

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

    @Min(value = 1)
    public double getSalary() {
        return salary;
    }

    public void setSalary(@Min(value = 1) double salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getExploringFrom() {
        return exploringFrom;
    }

    public void setExploringFrom(LocalDate exploringFrom) {
        this.exploringFrom = exploringFrom;
    }

    public Volcano getVolcano() {
        return volcano;
    }

    public void setVolcano(Volcano volcano) {
        this.volcano = volcano;
    }
}
