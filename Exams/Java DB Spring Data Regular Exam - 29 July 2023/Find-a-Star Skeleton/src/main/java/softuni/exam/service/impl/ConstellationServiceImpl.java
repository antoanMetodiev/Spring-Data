package softuni.exam.service.impl;

// TODO: Implement all methods

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.jsons.ConstellationSeedDto;
import softuni.exam.models.entity.Constellation;
import softuni.exam.repository.ConstellationRepository;
import softuni.exam.service.ConstellationService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.ValidationUtilImpl;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service
public class ConstellationServiceImpl implements ConstellationService {

    private static final String FILE_PATH = "src/main/resources/files/json/constellations.json";

    private final ConstellationRepository constellationRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtilImpl;

    public ConstellationServiceImpl(ConstellationRepository constellationRepository, Gson gson, ModelMapper modelMapper, ValidationUtilImpl validationUtilImpl) {
        this.constellationRepository = constellationRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtilImpl = validationUtilImpl;
    }

    @Override
    public boolean areImported() {
        return this.constellationRepository.count() > 0;
    }

    @Override
    public String readConstellationsFromFile() throws IOException {
        return new String(Files.readAllBytes(Path.of(FILE_PATH)));
    }

    @Override
    public String importConstellations() throws IOException {
        StringBuilder sb = new StringBuilder();
        ConstellationSeedDto[] constellationSeedDtos =
                this.gson.fromJson(new FileReader(FILE_PATH),
                        ConstellationSeedDto[].class);

        for (ConstellationSeedDto constellationSeedDto : constellationSeedDtos) {
            Optional<Constellation> response =
                    this.constellationRepository.findByName(constellationSeedDto.getName());

            if (!this.validationUtilImpl.isValid(constellationSeedDto) || response.isPresent()) {
                sb.append("Invalid constellation\n");
                continue;
            }

            Constellation objForDb = this.modelMapper.map(constellationSeedDto, Constellation.class);
            this.constellationRepository.saveAndFlush(objForDb);
            sb.append(String.format("Successfully imported constellation %s - %s\n", constellationSeedDto.getName(), constellationSeedDto.getDescription()));
        }

        return sb.toString();
    }
}
