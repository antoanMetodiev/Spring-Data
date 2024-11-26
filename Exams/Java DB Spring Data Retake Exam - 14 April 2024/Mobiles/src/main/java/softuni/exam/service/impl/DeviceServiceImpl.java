package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.xmls.DeviceDto;
import softuni.exam.models.dto.xmls.DeviceRootDto;
import softuni.exam.models.entity.Device;
import softuni.exam.models.entity.Sale;
import softuni.exam.repository.DeviceRepository;
import softuni.exam.repository.SaleRepository;
import softuni.exam.service.DeviceService;
import softuni.exam.util.ValidationUtilImpl;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Set;

@Service
public class DeviceServiceImpl implements DeviceService {
    private final String FILE_PATH = "src/main/resources/files/xml/devices.xml";

    private final DeviceRepository deviceRepository;
    private final SaleRepository saleRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtilImpl validationUtil;

    public DeviceServiceImpl(DeviceRepository deviceRepository, SaleRepository saleRepository, ModelMapper modelMapper, ValidationUtilImpl validationUtil) {
        this.deviceRepository = deviceRepository;
        this.saleRepository = saleRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
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

        System.out.println();
        DeviceRootDto deviceRootDto =
                (DeviceRootDto) JAXBContext.newInstance(DeviceRootDto.class).createUnmarshaller().unmarshal(new File(FILE_PATH));

        for (DeviceDto deviceDto : deviceRootDto.getDeviceDtoList()) {

            Optional<Device> byBrandAndModel = this.deviceRepository.findByBrandAndModel(deviceDto.getBrand(), deviceDto.getModel());
            Optional<Sale> byId = this.saleRepository.findById(deviceDto.getSale_id());

            if (!this.validationUtil.isValid(deviceDto) || byBrandAndModel.isPresent() || byId.isEmpty()) {
                sb.append("Invalid device").append(System.lineSeparator());
                continue;
            }

            Device forPersist = this.modelMapper.map(deviceDto, Device.class);
            this.deviceRepository.saveAndFlush(forPersist);
            sb.append(String.format("Successfully imported device of type %s with brand %s"
                            , deviceDto.getDeviceType().toString(), deviceDto.getBrand()))
                    .append(System.lineSeparator());
        }

        return sb.toString();
    }

    @Override
    public String exportDevices() {
        StringBuilder sb = new StringBuilder();
        Set<Device> response = this.deviceRepository
                .findByPriceLessThanAndStorageEqualsOrStorageGreaterThanOrderByBrandAscLowerCase();

        for (Device device : response) {
            sb.append(String.format(
                    "Device brand: %s\n" +
                    "   *Model: %s\n" +
                    "   **Storage: %s\n" +
                    "   ***Price: %.2f"
                    , device.getBrand(), device.getModel(),
                            device.getStorage(), device.getPrice()))
                    .append(System.lineSeparator());
        }

        return sb.toString();
    }
}
