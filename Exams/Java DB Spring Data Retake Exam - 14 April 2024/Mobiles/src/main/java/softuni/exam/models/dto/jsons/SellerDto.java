package softuni.exam.models.dto.jsons;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SellerDto {

    @NotNull
    @Size(min = 2, max = 30)
    @Expose
    private String firstName;

    @NotNull
    @Size(min = 2, max = 30)
    @Expose
    private String lastName;

    @Size(min = 3, max = 6)
    @NotNull
    @Expose
    private String personalNumber;

    public SellerDto(String firstName, String lastName, String personalNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalNumber = personalNumber;
    }

    public SellerDto() {}

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

    public @Size(min = 3, max = 6) @NotNull String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(@Size(min = 3, max = 6) @NotNull String personalNumber) {
        this.personalNumber = personalNumber;
    }
}
