package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Country;

import javax.validation.constraints.Size;
import java.util.Optional;

// TODO:

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    Optional<Country> findByName(@Size(min = 3, max = 30) String name);
}
