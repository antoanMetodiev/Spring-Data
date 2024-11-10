package softuni.exam.models.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalTime;

@Entity
@Table(name = "forecasts")
public class Forecast extends BaseEntity{

    @Column(name = "day_of_week", nullable = false)
    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    @Column(name = "max_temperature", nullable = false)
    @Min(value = -20)
    @Max(value = 60)
    private double maxTemperature;

    @Column(name = "min_temperature", nullable = false)
    @Min(value = -50)
    @Max(value = 40)
    private double minTemperature;

    @Column(nullable = false)
    private LocalTime sunrise;

    @Column(nullable = false)
    private LocalTime sunset;

    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private City city;


}
