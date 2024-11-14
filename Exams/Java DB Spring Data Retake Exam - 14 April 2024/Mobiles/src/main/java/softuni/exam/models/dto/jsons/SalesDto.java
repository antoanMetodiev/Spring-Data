package softuni.exam.models.dto.jsons;

import com.google.gson.annotations.Expose;
import org.apache.tomcat.jni.Local;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

//{
//        "discounted":true,
//        "number":"1000123",
//        "saleDate":"2022-02-02 12:43:00",
//        "seller":1
//        },
