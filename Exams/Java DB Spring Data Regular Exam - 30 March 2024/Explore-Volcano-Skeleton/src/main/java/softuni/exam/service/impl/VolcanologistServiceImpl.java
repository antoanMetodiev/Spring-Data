package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.VolcanologistRootDto;
import softuni.exam.models.dto.VolcanologistSeedDto;
import softuni.exam.models.entity.Volcano;
import softuni.exam.models.entity.Volcanologist;
import softuni.exam.repository.VolcanoRepository;
import softuni.exam.repository.VolcanologistRepository;
import softuni.exam.service.VolcanoService;
import softuni.exam.service.VolcanologistService;
import softuni.exam.util.ValidationUtilImpl;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

// TODO: Implement all methods

@Service
public class VolcanologistServiceImpl implements VolcanologistService {
    private final String FILE_PATH = "D:\\Programming\\Java\\Courses\\Spring Data (ORM)\\Exams\\Java DB Spring Data Regular Exam - 30 March 2024\\Explore-Volcano-Skeleton\\src\\main\\resources\\files\\xml\\volcanologists.xml";

    private final VolcanologistRepository volcanologistRepository;
    private final VolcanoRepository volcanoRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtilImpl validationUtil;

    public VolcanologistServiceImpl(VolcanologistRepository volcanologistRepository, VolcanoRepository volcanoRepository, ModelMapper modelMapper, ValidationUtilImpl validationUtil) {
        this.volcanologistRepository = volcanologistRepository;
        this.volcanoRepository = volcanoRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }


    @Override
    public boolean areImported() {
        return this.volcanologistRepository.count() > 0;
    }

    @Override
    public String readVolcanologistsFromFile() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importVolcanologists() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();

        VolcanologistRootDto volcanologists =
                (VolcanologistRootDto) JAXBContext.newInstance(VolcanologistRootDto.class)
                        .createUnmarshaller()
                        .unmarshal(new File(FILE_PATH));

        for (VolcanologistSeedDto volcanologist : volcanologists.getVolcanologists()) {

            Optional<Volcanologist> volcanologistResponse = this.volcanologistRepository.findByFirstNameAndLastName(volcanologist.getFirstName(), volcanologist.getLastName());
            Optional<Volcano> volcanoResponse = this.volcanoRepository.findById(volcanologist.getExploringVolcanoId());
            if (!this.validationUtil.isValid(volcanologist) || volcanologistResponse.isPresent() || volcanoResponse.isEmpty()) {
                sb.append("Invalid volcanologist").append(System.lineSeparator());
                continue;
            }

            Volcanologist forPersist = this.modelMapper.map(volcanologist, Volcanologist.class);
            this.volcanologistRepository.saveAndFlush(forPersist);
            sb.append(String.format("Successfully imported volcanologist %s %s"
                    , volcanologist.getFirstName(), volcanologist.getLastName())).append(System.lineSeparator());
        }

        return sb.toString();
    }
}