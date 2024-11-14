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
    private static final String FILE_PATH = "src/main/resources/files/json/sellers.json";

    private final SellerRepository sellerRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtilImpl validationUtil;

    public SellerServiceImpl(SellerRepository sellerRepository, Gson gson, ModelMapper modelMapper, ValidationUtilImpl validationUtil) {
        this.sellerRepository = sellerRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.sellerRepository.count() > 0;
    }

    @Override
    public String readSellersFromFile() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importSellers() throws IOException {
        StringBuilder sb = new StringBuilder();

        SellersDto[] sellersDto = this.gson.fromJson(readSellersFromFile(), SellersDto[].class);
        for (SellersDto seller : sellersDto) {
            Optional<Seller> response =
                    this.sellerRepository.findByLastNameOrPersonalNumber(seller.getLastName(), seller.getPersonalNumber());

            System.out.println();
            if (!this.validationUtil.isValid(seller) || response.isPresent()) {
                sb.append("Invalid seller").append(System.lineSeparator());
                continue;
            }

            Seller persistObj = this.modelMapper.map(seller, Seller.class);
            this.sellerRepository.saveAndFlush(persistObj);
            sb.append(String.format("Successfully imported seller %s %s"
                    , seller.getFirstName(), seller.getLastName())).append(System.lineSeparator());
        }

        return sb.toString();
    }
}
