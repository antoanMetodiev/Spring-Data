package softuni.exam.models.entity;

import softuni.exam.models.enums.StatusType;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name = "people")
public class Person extends BaseEntity {

    @Column(name = "first_name", unique = true, nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    @Email
    private String email;

    @Column(unique = true)
    private String phone;

    @Column(name = "status_type", nullable = false)
    @Enumerated(EnumType.ORDINAL)  // ЗА ЕТО ТУК ТИ ГОВОРЯ ЧЕ ТРЯБВА ДА БЪДЕ int (така пише в чертежа който ми е даден)
    private StatusType statusType;

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    private Country country;


    public Person(String firstName, String lastName, String email, String phone, StatusType statusType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.statusType = statusType;
    }

    public Person() {}

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

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

    public @Email String getEmail() {
        return email;
    }

    public void setEmail(@Email String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public StatusType getStatusType() {
        return statusType;
    }

    public void setStatusType(StatusType statusType) {
        this.statusType = statusType;
    }
}

