package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.jsons.CountryDto;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CountryService;
import softuni.exam.util.ValidationUtilImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class CountryServiceImpl implements CountryService {
    private final String FILE_PATH = "D:\\Programming\\Java\\Courses\\Spring Data (ORM)\\Exams\\Java DB Spring Data Retake Exam - 18 December 2022\\skeleton\\src\\main\\resources\\files\\json\\countries.json";

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
    public String readCountriesFileContent() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importCountries() throws IOException {
        StringBuilder sb = new StringBuilder();

        CountryDto[] countryDtos = this.gson.fromJson(readCountriesFileContent(), CountryDto[].class);
        for (CountryDto countryDto : countryDtos) {

            if (!this.validationUtil.isValid(countryDto)) {
                sb.append("Invalid country").append(System.lineSeparator());
                continue;
            }

            Country forPersist = this.modelMapper.map(countryDto, Country.class);
            this.countryRepository.saveAndFlush(forPersist);
            sb.append(String.format("Successfully imported country %s - %s"
                            , countryDto.getName(), countryDto.getCountryCode()))
                    .append(System.lineSeparator());
        }

        return sb.toString();
    }
}