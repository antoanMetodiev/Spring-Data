package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.jsons.SellersDto;
import softuni.exam.models.entity.Seller;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.SellerService;
import softuni.exam.util.ValidationUtilImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class SellerServiceImpl implements SellerService {
    private final String FILE_TEXT = "src/main/resources/files/json/sellers.json";

    private final SellerRepository sellerRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtilImpl validationUtil;
    private final Gson gson;

    public SellerServiceImpl(SellerRepository sellerRepository, ModelMapper modelMapper, ValidationUtilImpl validationUtil, Gson gson) {
        this.sellerRepository = sellerRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
    }


    @Override
    public boolean areImported() {
        return this.sellerRepository.count() > 0;
    }

    @Override
    public String readSellersFromFile() throws IOException {
        return Files.readString(Path.of(FILE_TEXT));
    }

    @Override
    public String importSellers() throws IOException {
        StringBuilder sb = new StringBuilder();

        SellersDto[] sellersDtos = this.gson.fromJson(readSellersFromFile(), SellersDto[].class);
        for (SellersDto sellersDto : sellersDtos) {

            Optional<Seller> byLastName = this.sellerRepository.findByLastName(sellersDto.getLastName());

            if (!this.validationUtil.isValid(sellersDto) || byLastName.isPresent()) {
                sb.append("Invalid seller").append(System.lineSeparator());
                continue;
            }

            Seller forPers = this.modelMapper.map(sellersDto, Seller.class);
            this.sellerRepository.saveAndFlush(forPers);
            sb.append(String.format("Successfully imported seller %s %s"
                            , sellersDto.getFirstName(), sellersDto.getLastName()))
                    .append(System.lineSeparator());
        }

        return sb.toString();
    }
}
