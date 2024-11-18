package softuni.exam.models.dto.jsons;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CountryDto {

    @Size(min = 2, max = 30)
    @NotNull
    @Expose
    private String name;

    @Size(min = 2, max = 19)
    @NotNull
    @Expose
    private String countryCode;

    @Size(min = 2, max = 19)
    @NotNull
    @Expose
    private String currency;

    public CountryDto(String name, String countryCode, String currency) {
        this.name = name;
        this.countryCode = countryCode;
        this.currency = currency;
    }

    public CountryDto() {}

    public @Size(min = 2, max = 30) @NotNull String getName() {
        return name;
    }

    public void setName(@Size(min = 2, max = 30) @NotNull String name) {
        this.name = name;
    }

    public @Size(min = 2, max = 19) @NotNull String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(@Size(min = 2, max = 19) @NotNull String countryCode) {
        this.countryCode = countryCode;
    }

    public @Size(min = 2, max = 19) @NotNull String getCurrency() {
        return currency;
    }

    public void setCurrency(@Size(min = 2, max = 19) @NotNull String currency) {
        this.currency = currency;
    }
}
