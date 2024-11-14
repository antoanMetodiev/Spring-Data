package softuni.exam.models.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "countries")
public class Country extends BaseEntity {

    @Column(unique = true, nullable = false)
//    @Size(min = 3, max = 30)
    private String name;

    @Column(unique = true)
//    @Size(min = 3, max = 30)
    private String capital;

    @OneToMany(mappedBy = "country")
    private Set<Volcano> volcanoes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public Set<Volcano> getVolcanoes() {
        return volcanoes;
    }

    public void setVolcanoes(Set<Volcano> volcanoes) {
        this.volcanoes = volcanoes;
    }
}
