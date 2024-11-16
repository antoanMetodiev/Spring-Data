package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.VolcanoDto;
import softuni.exam.models.entity.Volcano;
import softuni.exam.models.entity.Volcanologist;
import softuni.exam.models.enums.VolcanoType;
import softuni.exam.repository.CountryRepository;
import softuni.exam.repository.VolcanoRepository;
import softuni.exam.service.VolcanoService;
import softuni.exam.util.ValidationUtilImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

// TODO: Implement all methods
@Service
public class VolcanoServiceImpl implements VolcanoService {
    private final String FILE_PATH = "D:\\Programming\\Java\\Courses\\Spring Data (ORM)\\Exams\\Java DB Spring Data Regular Exam - 30 March 2024\\Explore-Volcano-Skeleton\\src\\main\\resources\\files\\json\\volcanoes.json";
//    src/main/resources/files/json/volcanoes.json

    private final VolcanoRepository volcanoRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtilImpl validationUtil;

    public VolcanoServiceImpl(VolcanoRepository volcanoRepository, ModelMapper modelMapper, Gson gson, ValidationUtilImpl validationUtil) {
        this.volcanoRepository = volcanoRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.volcanoRepository.count() > 0;
    }

    @Override
    public String readVolcanoesFileContent() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importVolcanoes() throws IOException {
        StringBuilder sb = new StringBuilder();

        System.out.println();
        VolcanoDto[] volcanoDto = this.gson.fromJson(readVolcanoesFileContent(), VolcanoDto[].class);
        System.out.println();

        for (VolcanoDto volcano : volcanoDto) {
            Optional<Volcano> response =
                    this.volcanoRepository.findByName(volcano.getName());

            if (!this.validationUtil.isValid(volcano) || response.isPresent()) {
                sb.append("Invalid volcano").append(System.lineSeparator());
                continue;
            }

            Volcano forPersist =
                    this.modelMapper.map(volcano, Volcano.class);

            this.volcanoRepository.saveAndFlush(forPersist);
            sb.append(String.format("Successfully imported volcano %s of type %s"
                    , volcano.getName(), volcano.getVolcanoType())).append(System.lineSeparator());
        }

        return sb.toString();
    }

    @Override
    public String exportVolcanoes() {
        return this.volcanoRepository.findAllByIsActiveAndElevationGreaterThanAndLastEruptionIsNotNullOrderByElevationDesc(3000)
                .stream()
                .map(volcano -> String.format("Volcano: %s\n" +
                                "   *Located in: %s\n" +
                                "   **Elevation: %d\n" +
                                "   ***Last eruption on: %s\n",
                        volcano.getName(),
                        volcano.getCountry().getName(),
                        volcano.getElevation(),
                        volcano.getLastEruption()))
                .collect(Collectors.joining());
    }
}