package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CountryDto {

    @Size(min = 3, max = 30)
    @NotNull
    @Expose
    private String name;

    @Size(min = 3, max = 30)
    @Expose
    private String capital;

    public CountryDto(String name, String capital) {
        this.name = name;
        this.capital = capital;
    }

    public @Size(min = 3, max = 30) String getName() {
        return name;
    }

    public void setName(@Size(min = 3, max = 30) String name) {
        this.name = name;
    }

    public @Size(min = 3, max = 30) String getCapital() {
        return capital;
    }

    public void setCapital(@Size(min = 3, max = 30) String capital) {
        this.capital = capital;
    }
}
