package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Seller;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Optional;

//TODO
@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {


    Optional<Seller> findByLastNameOrPersonalNumber(@Size(min = 2, max = 30) @NotNull String lastName, @Size(min = 3, max = 6) @NotNull String personalNumber);
}
