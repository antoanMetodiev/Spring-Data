package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CountryDto;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CountryService;
import softuni.exam.util.ValidationUtilImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

// TODO: Implement all methods
@Service
public class CountryServiceImpl implements CountryService {
    private final String FILE_PATH = "D:\\Programming\\Java\\Courses\\Spring Data (ORM)\\Exams\\Java DB Spring Data Regular Exam - 30 March 2024\\Explore-Volcano-Skeleton\\src\\main\\resources\\files\\json\\countries.json";

    private final CountryRepository countryRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtilImpl validationUtil;

    public CountryServiceImpl(CountryRepository countryRepository, ModelMapper modelMapper, Gson gson, ValidationUtilImpl validationUtil) {
        this.countryRepository = countryRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.countryRepository.count() > 0;
    }

    @Override
    public String readCountriesFromFile() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importCountries() throws IOException {
        StringBuilder sb = new StringBuilder();

        System.out.println();
        CountryDto[] countries = this.gson.fromJson(readCountriesFromFile(), CountryDto[].class);
        for (CountryDto countryDto : countries) {

            Optional<Country> response = this.countryRepository.findByName(countryDto.getName());
            if (!validationUtil.isValid(countryDto) || response.isPresent()) {
                sb.append("Invalid country").append(System.lineSeparator());
                continue;
            }

            Country persistObj = this.modelMapper.map(countryDto, Country.class);
            this.countryRepository.saveAndFlush(persistObj);
            sb.append(String.format("Successfully imported country %s - %s",
                    countryDto.getName(), countryDto.getCapital())).append(System.lineSeparator());
        }

        return sb.toString();
    }
}
