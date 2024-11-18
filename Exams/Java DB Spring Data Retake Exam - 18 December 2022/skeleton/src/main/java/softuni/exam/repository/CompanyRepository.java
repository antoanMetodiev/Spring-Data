package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Company;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Optional;

// TODO:
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findByName(@NotNull @Size(min = 2, max = 40) String name);
}
