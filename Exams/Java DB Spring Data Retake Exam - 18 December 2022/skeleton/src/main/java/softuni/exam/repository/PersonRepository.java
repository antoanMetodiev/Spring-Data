package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Person;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Optional;

// TODO:
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByFirstName(@Size(min = 2, max = 30) @NotNull String firstName);

    Optional<Person> findByEmail(@Email @NotNull String email);

    Optional<Person> findByPhone(@Size(min = 2, max = 13) String phone);
}
