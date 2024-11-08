package softuni.exam.service;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.exam.models.entity.Astronomer;
import softuni.exam.models.entity.Star;

import java.io.IOException;

// TODO: Implement all methods
public interface StarService  {

    boolean areImported();

    String readStarsFileContent() throws IOException;
	
	String importStars() throws IOException;

    String exportStars();
}
