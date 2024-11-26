package softuni.exam.models.dto.jsons;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SellersDto {
    @Expose
    @NotNull
    @Size(min = 2, max = 30)
    private String firstName;

    @Expose
    @NotNull
    @Size(min = 2, max = 30)
    private String lastName;

    @Expose
    @NotNull
    @Size(min = 3, max = 6)
    private String personalNumber;

    public SellersDto(String firstName, String lastName, String personalNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalNumber = personalNumber;
    }

    public SellersDto() {}

    public @NotNull @Size(min = 2, max = 30) String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotNull @Size(min = 2, max = 30) String firstName) {
        this.firstName = firstName;
    }

    public @NotNull @Size(min = 2, max = 30) String getLastName() {
        return lastName;
    }

    public void setLastName(@NotNull @Size(min = 2, max = 30) String lastName) {
        this.lastName = lastName;
    }

    public @NotNull @Size(min = 3, max = 6) String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(@NotNull @Size(min = 3, max = 6) String personalNumber) {
        this.personalNumber = personalNumber;
    }
}
