package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.jsons.CountryDto;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CountryService;
import softuni.exam.util.ValidationUtil_Impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    private final String FILE_PATH = "src/main/resources/files/json/countries.json";

    private final CountryRepository countryRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil_Impl validationUtil;

    public CountryServiceImpl(CountryRepository countryRepository, Gson gson, ModelMapper modelMapper, ValidationUtil_Impl validationUtil) {
        this.countryRepository = countryRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
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

        String a = readCountriesFromFile();

        CountryDto[] countryDtos = this.gson.fromJson(readCountriesFromFile(), CountryDto[].class);

        System.out.println();
        for (CountryDto countryDto : countryDtos) {

            Optional<Country> optional = this.countryRepository.getCountryByCountryName(countryDto.getCountryName());
            if (!this.validationUtil.isValid(countryDto) || optional.isPresent()) {
                sb.append("Invalid country").append(System.lineSeparator());
                continue;
            }

            Country persistObj = this.modelMapper.map(countryDto, Country.class);
            this.countryRepository.saveAndFlush(persistObj);
            sb.append(String.format("Successfully imported country %s - %s"
                            , countryDto.getCountryName(), countryDto.getCurrency()))
                    .append(System.lineSeparator());
        }

        return sb.toString();
    }
}
