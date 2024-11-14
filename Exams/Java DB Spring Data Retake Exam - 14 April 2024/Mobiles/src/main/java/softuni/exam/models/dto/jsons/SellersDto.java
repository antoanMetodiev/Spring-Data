package softuni.exam.models.dto.jsons;


import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class SellersDto {
    @Expose
    @Size(min = 2, max = 30)
    @NotNull
    private String firstName;

    @Expose
    @Size(min = 2, max = 30)
    @NotNull
    private String lastName;

    @Expose
    @Size(min = 3, max = 6)
    @NotNull
    private String personalNumber;

    public SellersDto(String firstName, String lastName, String personalNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalNumber = personalNumber;
    }

    public @Size(min = 2, max = 30) @NotNull String getFirstName() {
        return firstName;
    }

    public void setFirstName(@Size(min = 2, max = 30) @NotNull String firstName) {
        this.firstName = firstName;
    }

    public @Size(min = 2, max = 30) @NotNull String getLastName() {
        return lastName;
    }

    public void setLastName(@Size(min = 2, max = 30) @NotNull String lastName) {
        this.lastName = lastName;
    }

    public @Size(min = 3, max = 6) @NotNull String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(@Size(min = 3, max = 6) @NotNull String personalNumber) {
        this.personalNumber = personalNumber;
    }
}
