package softuni.exam.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.dto.xmls.AstronomerSeedDto;
import softuni.exam.models.entity.Astronomer;

import javax.validation.constraints.Size;
import java.util.Optional;

@Repository
public interface AstronomerRepository extends JpaRepository<Astronomer, Long> {


    Optional<Astronomer> findByFirstNameAndLastName(@Size(min = 2, max = 30) String firstName, @Size(min = 2, max = 30) String lastName);
}
