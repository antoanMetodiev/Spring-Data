package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Volcano;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Optional;

// TODO:
@Repository
public interface VolcanoRepository extends JpaRepository<Volcano, Long> {

    Optional<Volcano> findByName(@Size(min = 2, max = 30) @NotNull String name);

    @Query("SELECT v FROM Volcano v WHERE v.elevation > :elevation AND v.lastEruption IS NOT NULL AND v.isActive = true ORDER BY v.elevation DESC")
    List<Volcano> findAllByIsActiveAndElevationGreaterThanAndLastEruptionIsNotNullOrderByElevationDesc(int elevation);
}
