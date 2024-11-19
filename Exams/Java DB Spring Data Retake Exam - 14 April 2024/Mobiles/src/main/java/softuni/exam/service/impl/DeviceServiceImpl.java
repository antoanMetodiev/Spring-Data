package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.xmls.DeviceRootDto;
import softuni.exam.models.dto.xmls.DeviceSeedDto;
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
    private final ValidationUtilImpl validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public DeviceServiceImpl(DeviceRepository deviceRepository, SaleRepository saleRepository, ValidationUtilImpl validationUtil, ModelMapper modelMapper) {
        this.deviceRepository = deviceRepository;
        this.saleRepository = saleRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
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

        DeviceRootDto deviceDtos =
                (DeviceRootDto) JAXBContext.newInstance(DeviceRootDto.class).createUnmarshaller().unmarshal(new File(FILE_PATH));

        for (DeviceSeedDto deviceSeedDto : deviceDtos.getDeviceSeedDtoList()) {

            Optional<Device> byBrandAndModel =
                    this.deviceRepository.findByBrandAndModel(deviceSeedDto.getBrand(), deviceSeedDto.getModel());

            Optional<Sale> byId = this.saleRepository.findById(deviceSeedDto.getSaleId());

            if (!this.validationUtil.isValid(deviceSeedDto) || byBrandAndModel.isPresent() || byId.isEmpty()) {
                sb.append("Invalid device").append(System.lineSeparator());
                continue;
            }

            Device forPers =
                    this.modelMapper.map(deviceSeedDto, Device.class);

            this.deviceRepository.saveAndFlush(forPers);
            sb.append(String.format("Successfully imported device of type %s with brand %s"
                            , deviceSeedDto.getDeviceType(), deviceSeedDto.getBrand()))
                    .append(System.lineSeparator());
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
        }

        return sb.toString();
    }
}
