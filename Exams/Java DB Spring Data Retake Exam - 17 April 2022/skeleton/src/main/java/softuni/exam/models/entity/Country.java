package softuni.exam.models.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "countries")
public class Country extends BaseEntity {

    @Column(name = "country_name", unique = true, nullable = false)
    @Size(min = 2, max = 60)
    private String countryName;

    @Column(nullable = false)
    @Size(min = 2, max = 20)
    private String currency;

    @OneToMany(mappedBy = "country")
    private Set<City> cities;

    public Country(String countryName, String currency) {
        this.countryName = countryName;
        this.currency = currency;
    }

    public Country() {
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
