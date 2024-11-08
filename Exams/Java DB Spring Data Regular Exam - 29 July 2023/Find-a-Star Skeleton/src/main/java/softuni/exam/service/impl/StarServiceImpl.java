package softuni.exam.service.impl;

// TODO: Implement all methods

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.jsons.StarSeedDto;
import softuni.exam.models.entity.Constellation;
import softuni.exam.models.entity.Star;
import softuni.exam.models.entity.StarType;
import softuni.exam.repository.ConstellationRepository;
import softuni.exam.repository.StarRepository;
import softuni.exam.service.StarService;
import softuni.exam.util.ValidationUtilImpl;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StarServiceImpl implements StarService {

    private static final String FILE_PATH = "src/main/resources/files/json/stars.json";

    private final StarRepository starRepository;

    private final ConstellationRepository constellationRepository;

    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtilImpl validationUtil;

    public StarServiceImpl(StarRepository starRepository, ConstellationRepository constellationRepository, ModelMapper modelMapper, Gson gson, ValidationUtilImpl validationUtil) {
        this.starRepository = starRepository;
        this.constellationRepository = constellationRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.starRepository.count() > 0;
    }

    @Override
    public String readStarsFileContent() throws IOException {
        return new String(Files.readAllBytes(Path.of(FILE_PATH)));
    }

    @Override
    public String importStars() throws IOException {
        StringBuilder sb = new StringBuilder();
        StarSeedDto[] starSeedDtos = this.gson.fromJson(readStarsFileContent(), StarSeedDto[].class);

        for (StarSeedDto starSeedDto : starSeedDtos) {
            Optional<Constellation> responseObj = this.starRepository.findByName(starSeedDto.getName());
            if (!this.validationUtil.isValid(starSeedDto) || responseObj.isPresent()) {
                sb.append("Invalid star").append(System.lineSeparator());
                continue;
            }

            Star star = this.modelMapper.map(starSeedDto, Star.class);
            star.setStarType(StarType.valueOf(starSeedDto.getStarType()));
            star.setConstellation(this.constellationRepository.findById(starSeedDto.getConstellation()).get());

            this.starRepository.saveAndFlush(star);
            sb.append(String.format("Successfully imported star %s - %.2f light years",
                            starSeedDto.getName(), starSeedDto.getLightYears()))
                    .append(System.lineSeparator());
        }

        return sb.toString();
    }

    @Override
    public String exportStars() {
        return this.starRepository.findAllByStarTypeOOrderByLightYears()
                .stream()
                .map(star -> String.format("Star: %s\n" +
                                "   *Distance: %.2f light years\n" +
                                "   **Description: %s\n" +
                                "   ***Constellation: %s\n",
                        star.getName(),
                        star.getLightYears(),
                        star.getDescription(),
                        star.getConstellation().getName()))
                .collect(Collectors.joining());
    }
}
