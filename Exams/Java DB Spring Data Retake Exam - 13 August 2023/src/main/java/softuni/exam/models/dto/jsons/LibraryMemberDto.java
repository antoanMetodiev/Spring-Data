package softuni.exam.models.dto.jsons;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LibraryMemberDto {

    @Size(min = 2, max = 40)
    @Expose
    private String address;

    @Size(min = 2, max = 30)
    @NotNull
    @Expose
    private String firstName;

    @Size(min = 2, max = 30)
    @NotNull
    @Expose
    private String lastName;

    @Size(min = 2, max = 20)
    @NotNull
    @Expose
    private String phoneNumber;

    public LibraryMemberDto(String address, String firstName, String lastName, String phoneNumber) {
        this.address = address;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public LibraryMemberDto() {}


    public @Size(min = 2, max = 40) String getAddress() {
        return address;
    }

    public void setAddress(@Size(min = 2, max = 40) String address) {
        this.address = address;
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

    public @Size(min = 2, max = 20) @NotNull String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@Size(min = 2, max = 20) @NotNull String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
