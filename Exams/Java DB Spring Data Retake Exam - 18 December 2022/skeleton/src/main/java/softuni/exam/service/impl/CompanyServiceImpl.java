package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.xmls.CompanyRootDto;
import softuni.exam.models.dto.xmls.CompanySeedDto;
import softuni.exam.models.entity.Company;
import softuni.exam.repository.CompanyRepository;
import softuni.exam.service.CompanyService;
import softuni.exam.util.ValidationUtilImpl;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final String FILE_PATH = "D:\\Programming\\Java\\Courses\\Spring Data (ORM)\\Exams\\Java DB Spring Data Retake Exam - 18 December 2022\\skeleton\\src\\main\\resources\\files\\xml\\companies.xml";

    private final CompanyRepository companyRepository;
    private final ValidationUtilImpl validationUtil;
    private final ModelMapper modelMapper;

    public CompanyServiceImpl(CompanyRepository companyRepository, ValidationUtilImpl validationUtil, ModelMapper modelMapper) {
        this.companyRepository = companyRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return this.companyRepository.count() > 0;
    }

    @Override
    public String readCompaniesFromFile() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importCompanies() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();

        System.out.println();
        CompanyRootDto companiesDto =
                (CompanyRootDto) JAXBContext.newInstance(CompanyRootDto.class).createUnmarshaller().unmarshal(new File(FILE_PATH));

        System.out.println();

        for (CompanySeedDto companySeedDto : companiesDto.getCompanySeedDtos()) {

            Optional<Company> byName =
                    this.companyRepository.findByName(companySeedDto.getCompanyName());

            if (!this.validationUtil.isValid(companySeedDto) || byName.isPresent()) {
                sb.append("Invalid company").append(System.lineSeparator());
                continue;
            }

            Company forPers = this.modelMapper.map(companySeedDto, Company.class);
            this.companyRepository.saveAndFlush(forPers);
            sb.append(String.format("Successfully imported company %s - %s",
                            companySeedDto.getCompanyName(), companySeedDto.getCountryId()))
                    .append(System.lineSeparator());
        }

        return sb.toString();
    }
}
