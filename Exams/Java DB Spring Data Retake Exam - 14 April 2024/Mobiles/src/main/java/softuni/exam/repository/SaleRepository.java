package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Sale;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Optional;

//TODO
@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    Optional<Sale> findSaleByNumber(@Size(min = 7, max = 7) @NotNull String number);
}
