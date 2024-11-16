package softuni.exam.repository;

// TODO:

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Volcanologist;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Optional;

@Repository
public interface VolcanologistRepository extends JpaRepository<Volcanologist, Long> {

    Optional<Volcanologist> findByFirstNameAndLastName(@Size(min = 2, max = 30) @NotNull String firstName, @Size(min = 2, max = 30) @NotNull String lastName);
}
