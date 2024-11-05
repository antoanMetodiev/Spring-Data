package entities.task_2;

import entities.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "store_locations")
public class StoreLocation extends BaseEntity {
    @Column(name = "location_name")
    private String locationName;

    @OneToMany
    @JoinColumn(name = "seles_id")
    private Set<Sale> sales;
}
