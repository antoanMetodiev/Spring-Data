package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;
import org.springframework.lang.Nullable;
import softuni.exam.models.enums.VolcanoType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class VolcanoDto {

    @Size(min = 2, max = 30)
    @NotNull
    @Expose
    private String name;

    @Min(value = 1)
    @NotNull
    @Expose
    private int elevation;

    @Enumerated(EnumType.STRING)
    @Expose
    private VolcanoType volcanoType;

    @Expose
    @NotNull
    private boolean isActive;

    @Expose
//    private LocalDate lastEruption;

    private String lastEruption;

    @Expose
    @Nullable
    private long country;

    public VolcanoDto(String name, int elevation, VolcanoType volcanoType, boolean isActive, String lastEruption, long country) {
        this.name = name;
        this.elevation = elevation;
        this.volcanoType = volcanoType;
        this.isActive = isActive;
        this.lastEruption = lastEruption;
        this.country = country;
    }

    public @Size(min = 2, max = 30) @NotNull String getName() {
        return name;
    }

    public void setName(@Size(min = 2, max = 30) @NotNull String name) {
        this.name = name;
    }

    @Min(value = 1)
    @NotNull
    public int getElevation() {
        return elevation;
    }

    public void setElevation(@Min(value = 1) @NotNull int elevation) {
        this.elevation = elevation;
    }

    public VolcanoType getVolcanoType() {
        return volcanoType;
    }

    public void setVolcanoType(VolcanoType volcanoType) {
        this.volcanoType = volcanoType;
    }

    @NotNull
    public boolean isActive() {
        return isActive;
    }

    public void setActive(@NotNull boolean active) {
        isActive = active;
    }

    public String getLastEruption() {
        return lastEruption;
    }

    public void setLastEruption(String lastEruption) {
        this.lastEruption = lastEruption;
    }

    public long getCountry() {
        return country;
    }

    public void setCountry(long country) {
        this.country = country;
    }
}
