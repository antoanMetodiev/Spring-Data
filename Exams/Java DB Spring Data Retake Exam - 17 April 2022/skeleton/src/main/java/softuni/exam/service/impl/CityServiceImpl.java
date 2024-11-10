package softuni.exam.service.impl;

import com.google.gson.Gson;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.jsons.CityDto;
import softuni.exam.models.entity.City;
import softuni.exam.repository.CityRepository;
import softuni.exam.service.CityService;
import softuni.exam.util.ValidationUtil_Impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {
    private final String FILE_PATH = "src/main/resources/files/json/cities.json";

    private final CityRepository cityRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil_Impl validationUtil;

    public CityServiceImpl(CityRepository cityRepository, ModelMapper modelMapper, Gson gson, ValidationUtil_Impl validationUtil) {
        this.cityRepository = cityRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.cityRepository.count() > 0;
    }

    @Override
    public String readCitiesFileContent() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importCities() throws IOException {
        StringBuilder sb = new StringBuilder();
        CityDto[] cities = this.gson.fromJson(readCitiesFileContent(), CityDto[].class);

        System.out.println();
        for (CityDto city : cities) {

            Optional<City> response = this.cityRepository.findByCityName(city.getCityName());
            if (!this.validationUtil.isValid(city) || response.isPresent()) {
                sb.append("Invalid city").append(System.lineSeparator());
                continue;
            }


            City cityForPersist = this.modelMapper.map(city, City.class);
            this.cityRepository.saveAndFlush(cityForPersist);
            sb.append(String.format("Successfully imported city %s - %s",
                    city.getCityName(), city.getPopulation())).append(System.lineSeparator());
        }

        return sb.toString();
    }
}
