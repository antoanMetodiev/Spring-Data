package bg.softuni.spring_data_intro.data.repositories;

import bg.softuni.spring_data_intro.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{
}
