package softuni.exam.repository;

// TODO:

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Constellation;
import softuni.exam.models.entity.Star;
import softuni.exam.models.entity.StarType;

import javax.validation.constraints.Size;
import java.util.Optional;
import java.util.Set;

@Repository
public interface StarRepository extends JpaRepository<Star, Long> {

    Optional<Constellation> findByName(@Size(min = 2, max = 30) String name);

    @Query(value = "FROM Star WHERE starType = 'RED_GIANT' AND observers.size = 0 ORDER BY lightYears")
    Set<Star> findAllByStarTypeOOrderByLightYears();
}
