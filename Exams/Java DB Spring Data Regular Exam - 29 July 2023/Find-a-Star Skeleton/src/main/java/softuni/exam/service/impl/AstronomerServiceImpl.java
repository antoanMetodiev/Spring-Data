package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import softuni.exam.models.dto.xmls.AstronomerRootDto;
import softuni.exam.models.dto.xmls.AstronomerSeedDto;
import softuni.exam.models.entity.Astronomer;
import softuni.exam.models.entity.Star;
import softuni.exam.repository.AstronomerRepository;
import softuni.exam.repository.StarRepository;
import softuni.exam.service.AstronomerService;
import softuni.exam.util.ValidationUtilImpl;

import javax.xml.bind.JAXB;
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
public class AstronomerServiceImpl implements AstronomerService {

    private static final String FILE_PATH = "src/main/resources/files/xml/astronomers.xml";

    private final AstronomerRepository astronomerRepository;
    private final StarRepository starRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtilImpl validationUtil;

    public AstronomerServiceImpl(AstronomerRepository astronomerRepository, StarRepository starRepository, ModelMapper modelMapper, Gson gson, ValidationUtilImpl validationUtil) {
        this.astronomerRepository = astronomerRepository;
        this.starRepository = starRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.astronomerRepository.count() > 0;
    }

    @Override
    public String readAstronomersFromFile() throws IOException {
        return new String(Files.readAllBytes(Path.of(FILE_PATH)));
    }

    @Override
    public String importAstronomers() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();

        JAXBContext jaxbContext = JAXBContext.newInstance(AstronomerRootDto.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        AstronomerRootDto astronomerRootDto = (AstronomerRootDto) unmarshaller.unmarshal(new File(FILE_PATH));

        for (AstronomerSeedDto astronomerSeedDto : astronomerRootDto.getAstronomerSeedDtoList()) {
            Optional<Astronomer> optional =
                    this.astronomerRepository.findByFirstNameAndLastName(astronomerSeedDto.getFirstName(),
                            astronomerSeedDto.getLastName());

            Optional<Star> optionalStar = this.starRepository.findById(astronomerSeedDto.getStar());
            if (!this.validationUtil.isValid(astronomerSeedDto) || optional.isPresent() || optionalStar.isEmpty()) {
                sb.append("Invalid astronomer").append(System.lineSeparator());
                continue;
            }

            Astronomer astr = this.modelMapper.map(astronomerSeedDto, Astronomer.class);
            astr.setObservingStar(optionalStar.get());
            this.astronomerRepository.saveAndFlush(astr);

            sb.append(String.format("Successfully imported astronomer %s %s - %.2f"
                    , astronomerSeedDto.getFirstName(), astronomerSeedDto.getLastName()
                    , astronomerSeedDto.getAverageObservationHours())).append(System.lineSeparator());
        }


        return sb.toString();
    }
}
