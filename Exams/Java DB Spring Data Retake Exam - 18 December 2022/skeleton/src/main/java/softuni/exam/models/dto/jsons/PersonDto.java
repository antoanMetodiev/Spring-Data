package softuni.exam.models.dto.jsons;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PersonDto {

    @Email
    @NotNull
    @Expose
    private String email;

    @Size(min = 2, max = 30)
    @NotNull
    @Expose
    private String firstName;

    @Size(min = 2, max = 30)
    @NotNull
    @Expose
    private String lastName;

    @Size(min = 2, max = 13)
    @Expose
    private String phone;

    @NotNull
    @Expose
    private String statusType;

    @Expose
//    @NotNull
    private long country;

    public PersonDto(String firstName, String lastName, String email, String phone, String statusType, long country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.statusType = statusType;
        this.country = country;
    }

    public PersonDto() {}

    public @Email @NotNull String getEmail() {
        return email;
    }

    public void setEmail(@Email @NotNull String email) {
        this.email = email;
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

    public @Size(min = 2, max = 13) String getPhone() {
        return phone;
    }

    public void setPhone(@Size(min = 2, max = 13) String phone) {
        this.phone = phone;
    }

    public @NotNull String getStatusType() {
        return statusType;
    }

    public void setStatusType(@NotNull String statusType) {
        this.statusType = statusType;
    }

    public long getCountry() {
        return country;
    }

    public void setCountry(long country) {
        this.country = country;
    }
}
