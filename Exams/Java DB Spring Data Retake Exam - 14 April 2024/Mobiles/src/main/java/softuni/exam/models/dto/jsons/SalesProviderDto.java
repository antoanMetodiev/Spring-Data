package softuni.exam.models.dto.jsons;

import com.google.gson.annotations.Expose;
import org.apache.tomcat.jni.Local;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SalesProviderDto {
    @Expose
    private boolean discounted;

    @Expose
    @Size(min = 7, max = 7)
    @NotNull
    private String number;

    @Expose
    @NotNull
    private String saleDate;

    @Expose
    private long seller;


//    private LocalDateTime formatLocalDateTime(String saleDate) {
//        // Формат на дата и час
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        return LocalDateTime.parse(saleDate, formatter);
//    }


    public SalesProviderDto(boolean discounted, String number, String saleDate, long seller) {
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

    public @NotNull String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(@NotNull String saleDate) {
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
