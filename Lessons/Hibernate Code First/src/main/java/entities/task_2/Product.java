package entities.task_2;

import entities.BaseEntity;

import javax.persistence.*;
import javax.xml.namespace.QName;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product extends BaseEntity {

    @Column(length = 100)
    private String name;

    @Column()
    private Double quantity;

    @Column()
    private BigDecimal price;

    @OneToMany
    @JoinColumn(name = "sales_id")
    private Set<Sale> sales;

}
