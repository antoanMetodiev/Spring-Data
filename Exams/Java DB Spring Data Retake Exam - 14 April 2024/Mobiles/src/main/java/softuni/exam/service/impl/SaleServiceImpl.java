package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.jsons.SalesDto;
import softuni.exam.models.dto.jsons.SalesProviderDto;
import softuni.exam.models.entity.Sale;
import softuni.exam.repository.SaleRepository;
import softuni.exam.service.SaleService;
import softuni.exam.util.ValidationUtilImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class SaleServiceImpl implements SaleService {
    private final String FILE_PATH = "src/main/resources/files/json/sales.json";

    private final SaleRepository saleRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtilImpl validationUtil;

    public SaleServiceImpl(SaleRepository saleRepository, Gson gson, ModelMapper modelMapper, ValidationUtilImpl validationUtil) {
        this.saleRepository = saleRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
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
        SalesProviderDto[] salesProviderDto =
                this.gson.fromJson(readSalesFileContent(), SalesProviderDto[].class);

        // Формат на дата и час
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (SalesProviderDto saleProviderDto : salesProviderDto) {

            // Преобразуване към адекватно DTO:
            SalesDto saleDto = new SalesDto(saleProviderDto.isDiscounted(), saleProviderDto.getNumber()
                    , LocalDateTime.parse(saleProviderDto.getSaleDate(), formatter),
                    saleProviderDto.getSeller());

            Optional<Sale> saleByNumber =
                    this.saleRepository.findSaleByNumber(saleDto.getNumber());

            boolean validationReponse = this.validationUtil.isValid(saleDto);
            if (!validationReponse || saleByNumber.isPresent()) {
                sb.append(String.format("Invalid sale")).append(System.lineSeparator());
                continue;
            }

            Sale persistObj = this.modelMapper.map(saleDto, Sale.class);
            this.saleRepository.saveAndFlush(persistObj);

            sb.append(String.format("Successfully imported sale with number %s",
                    saleDto.getNumber())).append(System.lineSeparator());
        }

        return sb.toString();
    }
}

//{
//        "discounted": false,
//        "number": "8756321",
//        "saleDate": "2021-11-30 17:50:00",
//        "seller": 5
//        },
