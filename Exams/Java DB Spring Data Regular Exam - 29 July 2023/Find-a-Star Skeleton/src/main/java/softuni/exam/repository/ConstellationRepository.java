package softuni.exam.repository;

// TODO:

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Constellation;

import javax.validation.constraints.Size;
import java.util.Optional;

@Repository
public interface ConstellationRepository extends JpaRepository<Constellation, Long> {

    Optional<Constellation> findByName(@Size(min = 3, max = 20) String name);
}
