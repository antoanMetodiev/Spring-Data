package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import softuni.exam.models.dto.xmls.AllDevicesDto;
import softuni.exam.models.dto.xmls.DeviceDto;
import softuni.exam.models.entity.Device;
import softuni.exam.models.entity.Sale;
import softuni.exam.repository.DeviceRepository;
import softuni.exam.repository.SaleRepository;
import softuni.exam.service.DeviceService;
import softuni.exam.util.ValidationUtilImpl;
import softuni.exam.util.XmlParserImpl;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DeviceServiceImpl implements DeviceService {
    private final String FILE_PATH = "src/main/resources/files/xml/devices.xml";

    private final DeviceRepository deviceRepository;
    private final XmlParserImpl xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtilImpl validationUtil;
    private final SaleRepository saleRepository;

    public DeviceServiceImpl(DeviceRepository deviceRepository, XmlParserImpl xmlParser, ModelMapper modelMapper, ValidationUtilImpl validationUtil, SaleRepository saleRepository) {
        this.deviceRepository = deviceRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.saleRepository = saleRepository;
    }

    @Override
    public boolean areImported() {
        return this.deviceRepository.count() > 0;
    }

    @Override
    public String readDevicesFromFile() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importDevices() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();

        Unmarshaller unmarshaller = JAXBContext.newInstance(AllDevicesDto.class).createUnmarshaller();
        AllDevicesDto devicesDto = (AllDevicesDto) unmarshaller.unmarshal(new File(this.FILE_PATH));

        System.out.println();
        for (DeviceDto device : devicesDto.getDevices()) {

            Optional<Device> response1 = this.deviceRepository.findByModelAndBrand(device.getModel(),
                    device.getBrand());

            Optional<Sale> response2 = this.saleRepository.findById(device.getSale_id());

            if (!this.validationUtil.isValid(device) || response1.isPresent() || response2.isEmpty()) {
                sb.append("Invalid device").append(System.lineSeparator());
                continue;
            }

            Device forPersist = this.modelMapper.map(device, Device.class);
            this.deviceRepository.saveAndFlush(forPersist);
            sb.append(String.format("Successfully imported device of type %s with brand %s"
                    , device.getDevice_type(), device.getBrand())).append(System.lineSeparator());
        }

        return sb.toString();
    }

    @Override
    public String exportDevices() {
        StringBuilder sb = new StringBuilder();

        Set<Object> res = this.deviceRepository.exportNeededData();
        for (Object random : res) {

            Object[] random1 = (Object[]) random;

            sb.append(String.format("Device brand: %s\n" +
                            "   *Model: %s\n" +
                            "   **Storage: %s\n" +
                            "   ***Price: %.2f\n",
                    random1[0], random1[1], random1[2], random1[3]));

//            System.out.println(random1[0]);
//            System.out.println(random1[1]);
//            System.out.println(random1[2]);
//            System.out.println(random1[3]);
        }

        return sb.toString();
    }
}
