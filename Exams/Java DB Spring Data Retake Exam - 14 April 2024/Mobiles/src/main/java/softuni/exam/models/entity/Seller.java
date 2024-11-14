package softuni.exam.models.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "sellers")
public class Seller extends BaseEntity {

    @Column(name = "first_name", nullable = false)
//    @Size(min = 2, max = 30)
    private String firstName;

    @Column(name = "last_name", unique = true, nullable = false)
//    @Size(min = 2, max = 30)
    private String lastName;

    @Column(name = "personal_number", unique = true, nullable = false)
//    @Size(min = 3, max = 6)
    private String personalNumber;

    @OneToMany(mappedBy = "seller")
    private Set<Sale> sales;

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

    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }
}
