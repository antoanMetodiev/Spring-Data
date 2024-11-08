package softuni.exam.models.entity;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "constellations")
public class Constellation extends BaseEntity {

    @Column(unique = true, nullable = false)
//    @Min(3)
//    @Max(20)
    private String name;

    @Column(nullable = false)
//    @Min(5)
    private String description;

    @OneToMany(mappedBy = "constellation")
    private Set<Star> stars;

    public Constellation(String name, String description, Set<Star> stars) {
        this.name = name;
        this.description = description;
        this.stars = stars;
    }

    public Constellation() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Star> getStars() {
        return stars;
    }

    public void setStars(Set<Star> stars) {
        this.stars = stars;
    }
}
