package softuni.exam.models.dto.jsons;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class SalesDto {
    @Expose
    private boolean discounted;

    @Expose
    @Size(min = 7, max = 7)
    @NotNull
    private String number;

    @Expose
    @NotNull
    private LocalDateTime saleDate;

    @Expose
    private long seller;

    public SalesDto(boolean discounted, String number, LocalDateTime saleDate, long seller) {
        this.discounted = discounted;
        this.number = number;
        this.saleDate = saleDate;
        this.seller = seller;
    }

    public SalesDto() {}

    public boolean isDiscounted() {
        return discounted;
    }

    public void setDiscounted(boolean discounted) {
        this.discounted = discounted;
    }

    public @Size(min = 7, max = 7) @NotNull String getNumber() {
        return number;
    }

    public void setNumber(@Size(min = 7, max = 7) @NotNull String number) {
        this.number = number;
    }

    public @NotNull LocalDateTime getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(@NotNull LocalDateTime saleDate) {
        this.saleDate = saleDate;
    }

    public long getSeller() {
        return seller;
    }

    public void setSeller(long seller) {
        this.seller = seller;
    }
}
