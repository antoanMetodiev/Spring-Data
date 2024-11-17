package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.LibraryMember;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Optional;

// TODO:
@Repository
public interface LibraryMemberRepository extends JpaRepository<LibraryMember, Long> {

    Optional<LibraryMember> findByPhoneNumber(@Size(min = 2, max = 20) @NotNull String phoneNumber);
}
