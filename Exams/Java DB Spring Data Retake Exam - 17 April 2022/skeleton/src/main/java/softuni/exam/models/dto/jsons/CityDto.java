package softuni.exam.models.dto.jsons;

import com.google.gson.annotations.Expose;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CityDto {

    @Expose
    @Size(min = 2, max = 60)
    @NotNull
    private String cityName;

    @Expose
    @Column(columnDefinition = "TEXT")
    @Size(min = 2)
    private String description;

    @Expose
    @Min(value = 500)
    @NotNull
    private long population;

    @Expose
//    @NotNull
    private long country;

    public CityDto(String cityName, String description, long population, long country) {
        this.cityName = cityName;
        this.description = description;
        this.population = population;
        this.country = country;
    }

    public @Size(min = 2, max = 60) @NotNull String getCityName() {
        return cityName;
    }

    public void setCityName(@Size(min = 2, max = 60) @NotNull String cityName) {
        this.cityName = cityName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Min(value = 500)
    @NotNull
    public long getPopulation() {
        return population;
    }

    public void setPopulation(@Min(value = 500) @NotNull long population) {
        this.population = population;
    }

    public long getCountry() {
        return country;
    }

    public void setCountry(long country) {
        this.country = country;
    }
}
