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

    Optional<Device> findByBrand(@NotNull @Size(min = 2, max = 20) String brand);

    @Query("SELECT d.brand, d.model, d.storage, d.price FROM Device d" +
            " WHERE d.deviceType = 'SMART_PHONE' AND d.price < 1000 AND d.storage >= 128" +
            "ORDER BY LOWER(d.brand) ASC")
    Set<Object> exportNeededData();
}
