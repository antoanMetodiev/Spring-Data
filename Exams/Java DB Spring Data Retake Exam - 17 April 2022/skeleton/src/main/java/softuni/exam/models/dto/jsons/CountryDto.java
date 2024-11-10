package softuni.exam.models.dto.jsons;

import com.google.gson.annotations.Expose;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Component
public class CountryDto {
    @Expose
    @NotNull
    @Size(min = 2, max = 60)
    private String countryName;

    @Expose
    @NotNull
    @Size(min = 2, max = 60)
    private String currency;

    public CountryDto(String countryName, String currency) {
        this.countryName = countryName;
        this.currency = currency;
    }

    // Конструктор по подразбиране, нужен за десериализация
    public CountryDto() {
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
