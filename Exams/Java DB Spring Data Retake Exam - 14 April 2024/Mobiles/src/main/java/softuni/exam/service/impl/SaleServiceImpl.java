package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.jsons.SalesDto;
import softuni.exam.models.entity.Sale;
import softuni.exam.models.entity.Seller;
import softuni.exam.repository.SaleRepository;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.SaleService;
import softuni.exam.util.ValidationUtilImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class SaleServiceImpl implements SaleService {
    private final String FILE_PATH = "src/main/resources/files/json/sales.json";

    private final SaleRepository saleRepository;
    private final SellerRepository sellerRepository;
    private final Gson gson;
    private final ValidationUtilImpl validationUtil;
    private final ModelMapper modelMapper;

    public SaleServiceImpl(SaleRepository saleRepository, SellerRepository sellerRepository, Gson gson, ValidationUtilImpl validationUtil, ModelMapper modelMapper) {
        this.saleRepository = saleRepository;
        this.sellerRepository = sellerRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return this.saleRepository.count() > 0;
    }

    @Override
    public String readSalesFileContent() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importSales() throws IOException {
        StringBuilder sb = new StringBuilder();

        System.out.println();
        SalesDto[] salesDtos = this.gson.fromJson(readSalesFileContent(), SalesDto[].class);
        for (SalesDto salesDto : salesDtos) {

            Optional<Sale> byNumber = this.saleRepository.findByNumber(salesDto.getNumber());
            if (!this.validationUtil.isValid(salesDto) || byNumber.isPresent()) {
                sb.append("Invalid sale").append(System.lineSeparator());
                continue;
            }

            Sale forPersist = this.modelMapper.map(salesDto, Sale.class);
            Optional<Seller> sellerResponse = this.sellerRepository.findById(salesDto.getSeller());
            forPersist.setSeller(sellerResponse.get());

            this.saleRepository.saveAndFlush(forPersist);
            sb.append(String.format("Successfully imported sale with number %s", salesDto.getNumber()))
                    .append(System.lineSeparator());
        }

        return sb.toString();
    }
}
