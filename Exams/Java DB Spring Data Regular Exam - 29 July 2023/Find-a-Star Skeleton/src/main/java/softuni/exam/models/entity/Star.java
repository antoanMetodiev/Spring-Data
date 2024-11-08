package softuni.exam.models.entity;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "stars")
public class Star extends BaseEntity {

    @Column(nullable = false, unique = true)
//    @Min(2)
//    @Max(30)
    private String name;

    @Column(name = "light_years", nullable = false)
//    @Min(1)
    private double lightYears;

    @Column(name = "star_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private StarType starType;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "observingStar")
    private Set<Astronomer> observers;

    @ManyToOne
    @JoinColumn(name = "constellation_id", referencedColumnName = "id")
    private Constellation constellation;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Constellation getConstellation() {
        return constellation;
    }

    public void setConstellation(Constellation constellation) {
        this.constellation = constellation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLightYears() {
        return lightYears;
    }

    public void setLightYears(double lightYears) {
        this.lightYears = lightYears;
    }

    public StarType getStarType() {
        return starType;
    }

    public void setStarType(StarType starType) {
        this.starType = starType;
    }

    public Set<Astronomer> getObservers() {
        return observers;
    }

    public void setObservers(Set<Astronomer> observers) {
        this.observers = observers;
    }
}
