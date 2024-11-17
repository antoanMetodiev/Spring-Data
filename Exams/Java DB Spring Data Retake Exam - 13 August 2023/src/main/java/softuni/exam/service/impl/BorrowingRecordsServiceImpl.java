package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.xmls.BorrowingRecordRootDto;
import softuni.exam.models.dto.xmls.BorrowingSeedRecordDto;
import softuni.exam.models.dto.xmls.LibraryMemberXmlSeedDTO;
import softuni.exam.models.entity.Book;
import softuni.exam.models.entity.BorrowingRecord;
import softuni.exam.models.entity.LibraryMember;
import softuni.exam.models.enums.GenreType;
import softuni.exam.repository.BookRepository;
import softuni.exam.repository.BorrowingRecordRepository;
import softuni.exam.repository.LibraryMemberRepository;
import softuni.exam.service.BorrowingRecordsService;
import softuni.exam.util.ValidationUtilImpl;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

// TODO: Implement all methods
@Service
public class BorrowingRecordsServiceImpl implements BorrowingRecordsService {
    private final String FILE_PATH = "src/main/resources/files/xml/borrowing-records.xml";

    private final BorrowingRecordRepository borrowingRecordRepository;
    private final LibraryMemberRepository libraryMemberRepository;
    private final BookRepository bookRepository;
    private final ValidationUtilImpl validationUtil;
    private final ModelMapper modelMapper;

    public BorrowingRecordsServiceImpl(BorrowingRecordRepository borrowingRecordRepository, LibraryMemberRepository libraryMemberRepository, BookRepository bookRepository, ValidationUtilImpl validationUtil, ModelMapper modelMapper) {
        this.borrowingRecordRepository = borrowingRecordRepository;
        this.libraryMemberRepository = libraryMemberRepository;
        this.bookRepository = bookRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return this.borrowingRecordRepository.count() > 0;
    }

    @Override
    public String readBorrowingRecordsFromFile() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importBorrowingRecords() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();


        BorrowingRecordRootDto borrowingRecordRootDto =
                (BorrowingRecordRootDto) JAXBContext.newInstance(BorrowingRecordRootDto.class)
                        .createUnmarshaller()
                        .unmarshal(new File(FILE_PATH));

        System.out.println();


        for (BorrowingSeedRecordDto borrowing : borrowingRecordRootDto.getBorrowingSeedRecordDtos()) {

            String bookTitle = borrowing.getBook().getTitle();

            Optional<LibraryMember> memberResponse =
                    this.libraryMemberRepository.findById(borrowing.getMember().getId());
            Optional<Book> bookResponse =
                    this.bookRepository.findByTitle(bookTitle);

            if (!this.validationUtil.isValid(borrowing) || memberResponse.isEmpty() || bookResponse.isEmpty()) {
                sb.append("Invalid borrowing record").append(System.lineSeparator());
                continue;
            }

            BorrowingRecord forPersist =
                    this.modelMapper.map(borrowing, BorrowingRecord.class);

            forPersist.setBook(bookResponse.get());
            forPersist.setLibraryMember(memberResponse.get());

            this.borrowingRecordRepository.saveAndFlush(forPersist);
            sb.append(String.format("Successfully imported borrowing record %s - %s"
                    , borrowing.getBook().getTitle(), borrowing.getBorrowDate().toString())).append(System.lineSeparator());
        }

        return sb.toString();
    }

    @Override
    public String exportBorrowingRecords() {
        StringBuilder sb = new StringBuilder();
        List<BorrowingRecord> response =
                this.borrowingRecordRepository
                        .findByBookGenreOrderByBorrowDateDesc(GenreType.SCIENCE_FICTION);

        for (BorrowingRecord borrowingRecord : response) {

            sb.append(String.format("Book title: %s\n" +
                    "*Book author: %s\n" +
                    "**Date borrowed: %s\n" +
                    "***Borrowed by: %s %s"
                            , borrowingRecord.getBook().getTitle(), borrowingRecord.getBook().getAuthor(),
                            borrowingRecord.getBorrowDate().toString(),
                            borrowingRecord.getLibraryMember().getFirstName(),
                            borrowingRecord.getLibraryMember().getLastName()))
                    .append(System.lineSeparator());
        }

        return sb.toString();
    }
}
