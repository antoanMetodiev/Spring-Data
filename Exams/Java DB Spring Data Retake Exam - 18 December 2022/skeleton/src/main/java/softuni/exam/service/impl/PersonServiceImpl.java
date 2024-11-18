package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.jsons.PersonDto;
import softuni.exam.models.entity.Person;
import softuni.exam.repository.PersonRepository;
import softuni.exam.service.PersonService;
import softuni.exam.util.ValidationUtilImpl;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {
    private final String FILE_PATH = "D:\\Programming\\Java\\Courses\\Spring Data (ORM)\\Exams\\Java DB Spring Data Retake Exam - 18 December 2022\\skeleton\\src\\main\\resources\\files\\json\\people.json";

    private final PersonRepository personRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtilImpl validationUtil;

    public PersonServiceImpl(PersonRepository personRepository, ModelMapper modelMapper, Gson gson, ValidationUtilImpl validationUtil) {
        this.personRepository = personRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.personRepository.count() > 0;
    }

    @Override
    public String readPeopleFromFile() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importPeople() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();

        System.out.println();
        PersonDto[] personDtos = this.gson.fromJson(readPeopleFromFile(), PersonDto[].class);
        for (PersonDto personDto : personDtos) {

            Optional<Person> byFirstName = this.personRepository.findByFirstName(personDto.getFirstName());
            Optional<Person> byEmail = this.personRepository.findByEmail(personDto.getEmail());
            Optional<Person> byPhone = this.personRepository.findByPhone(personDto.getPhone());

            if (!this.validationUtil.isValid(personDto) || byFirstName.isPresent()
                    || byEmail.isPresent() || byPhone.isPresent()) {
                sb.append("Invalid person").append(System.lineSeparator());
                continue;
            }

            Person forPers = this.modelMapper.map(personDto, Person.class);
            this.personRepository.saveAndFlush(forPers);
            sb.append(String.format("Successfully imported person %s %s"
                            , personDto.getFirstName(), personDto.getLastName()))
                    .append(System.lineSeparator());
        }

        return sb.toString();
    }
}
