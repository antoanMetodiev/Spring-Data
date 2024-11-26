package softuni.exam.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Device;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Optional;
import java.util.Set;

//TODO
@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

    Optional<Device> findByBrandAndModel(@NotNull @Size(min = 2, max = 20) String brand, @Size(min = 1, max = 20) @NotNull String model);

    @Query(nativeQuery = true,
            value = "SELECT * FROM devices " +
                    "WHERE price < 1000 AND storage >= 128 AND device_type = 'SMART_PHONE' " +
                    "ORDER BY LOWER(brand) ASC")
    Set<Device> findByPriceLessThanAndStorageEqualsOrStorageGreaterThanOrderByBrandAscLowerCase();
}
