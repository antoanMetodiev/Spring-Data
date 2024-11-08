package softuni.exam.service;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.exam.models.entity.Astronomer;

import javax.xml.bind.JAXBException;
import java.io.IOException;

// TODO: Implement all methods
public interface AstronomerService {

    boolean areImported();

    String readAstronomersFromFile() throws IOException;

	String importAstronomers() throws IOException, JAXBException;

}
