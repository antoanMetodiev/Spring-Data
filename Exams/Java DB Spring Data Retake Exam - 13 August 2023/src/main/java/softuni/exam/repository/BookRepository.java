package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Book;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Optional;

// TODO:
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByTitle(@NotNull @Size(min = 3, max = 40) String title);
}
