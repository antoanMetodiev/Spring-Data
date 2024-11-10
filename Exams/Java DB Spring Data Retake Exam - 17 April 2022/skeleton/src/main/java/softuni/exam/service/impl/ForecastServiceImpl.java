package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.repository.ForecastRepository;
import softuni.exam.service.ForecastService;
import softuni.exam.util.ValidationUtil_Impl;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class ForecastServiceImpl implements ForecastService {
    private final String FILE_PATH = "src/main/resources/files/xml/forecasts.xml";

    private final ForecastRepository forecastRepository;
    private final ModelMapper modelMapper;
    private ValidationUtil_Impl validationUtil;


    @Autowired
    public ForecastServiceImpl(ForecastRepository forecastRepository, ModelMapper modelMapper, ValidationUtil_Impl validationUtil) {
        this.forecastRepository = forecastRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.forecastRepository.count() > 0;
    }

    @Override
    public String readForecastsFromFile() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importForecasts() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();

        JAXBContext jaxbContext = JAXBContext.newInstance();
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();



        return sb.toString();
    }

    @Override
    public String exportForecasts() {
        return "";
    }
}
