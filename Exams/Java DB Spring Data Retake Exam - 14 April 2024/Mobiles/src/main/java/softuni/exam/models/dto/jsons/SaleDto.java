package softuni.exam.models.dto.jsons;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class SaleDto {
    private boolean discounted;

    @NotNull
    @Size(min = 7, max = 7)
    @Expose
    private String number;

    @Expose
    private LocalDateTime saleDate;

    @Expose
    private long seller;

    public SaleDto(boolean discounted, String number, LocalDateTime saleDate, long seller) {
        this.discounted = discounted;
        this.number = number;
        this.saleDate = saleDate;
        this.seller = seller;
    }

    public SaleDto() {}

    public boolean isDiscounted() {
        return discounted;
    }

    public void setDiscounted(boolean discounted) {
        this.discounted = discounted;
    }

    public @NotNull @Size(min = 7, max = 7) String getNumber() {
        return number;
    }

    public void setNumber(@NotNull @Size(min = 7, max = 7) String number) {
        this.number = number;
    }

    public LocalDateTime getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDateTime saleDate) {
        this.saleDate = saleDate;
    }

    public long getSeller() {
        return seller;
    }

    public void setSeller(long seller) {
        this.seller = seller;
    }
}
