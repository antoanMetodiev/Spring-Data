package softuni.exam.models.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "cities")
public class City extends BaseEntity{

    @Column(name = "city_name", unique = true, nullable = false)
    @Size(min = 2, max = 60)
    private String cityName;

    @Column(columnDefinition = "TEXT")
    @Size(min = 2)
    private String description;

    @Column(nullable = false)
    @Min(value = 500)
    private long population;

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    private Country country;

    @OneToMany(mappedBy = "city")
    private Set<Forecast> forecasts;

    public @Size(min = 2, max = 60) String getCityName() {
        return cityName;
    }

    public void setCityName(@Size(min = 2, max = 60) String cityName) {
        this.cityName = cityName;
    }

    public @Size(min = 2) String getDescription() {
        return description;
    }

    public void setDescription(@Size(min = 2) String description) {
        this.description = description;
    }

    @Min(value = 500)
    public long getPopulation() {
        return population;
    }

    public void setPopulation(@Min(value = 500) long population) {
        this.population = population;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Set<Forecast> getForecasts() {
        return forecasts;
    }

    public void setForecasts(Set<Forecast> forecasts) {
        this.forecasts = forecasts;
    }
}
