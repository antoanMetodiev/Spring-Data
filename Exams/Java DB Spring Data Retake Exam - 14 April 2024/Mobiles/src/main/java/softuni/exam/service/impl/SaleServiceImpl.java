package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.jsons.SaleDto;
import softuni.exam.models.entity.Sale;
import softuni.exam.repository.SaleRepository;
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
    private final ModelMapper modelMapper;
    private final ValidationUtilImpl validationUtil;
    private final Gson gson;

    public SaleServiceImpl(SaleRepository saleRepository, ModelMapper modelMapper, ValidationUtilImpl validationUtil, Gson gson) {
        this.saleRepository = saleRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
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

        SaleDto[] saleDtos = this.gson.fromJson(readSalesFileContent(), SaleDto[].class);
        for (SaleDto saleDto : saleDtos) {

            Optional<Sale> byNumber = this.saleRepository.findByNumber(saleDto.getNumber());
            if (!this.validationUtil.isValid(saleDto) || byNumber.isPresent()) {
                sb.append("Invalid sale").append(System.lineSeparator());
                continue;
            }

            Sale forPers = this.modelMapper.map(saleDto, Sale.class);
            this.saleRepository.saveAndFlush(forPers);
            sb.append(String.format("Successfully imported sale with number %s", saleDto.getNumber()))
                    .append(System.lineSeparator());
        }


        return sb.toString();
    }
}
