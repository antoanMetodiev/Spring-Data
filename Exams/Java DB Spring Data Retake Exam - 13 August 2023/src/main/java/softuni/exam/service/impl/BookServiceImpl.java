package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.jsons.BookDto;
import softuni.exam.models.entity.Book;
import softuni.exam.repository.BookRepository;
import softuni.exam.service.BookService;
import softuni.exam.util.ValidationUtilImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

// TODO: Implement all methods
@Service
public class BookServiceImpl implements BookService {
    private final String FILE_PATH = "src/main/resources/files/json/books.json";

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtilImpl validationUtil;

    public BookServiceImpl(BookRepository bookRepository, ModelMapper modelMapper, Gson gson, ValidationUtilImpl validationUtil) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.bookRepository.count() > 0;
    }

    @Override
    public String readBooksFromFile() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importBooks() throws IOException {
        StringBuilder sb = new StringBuilder();

        BookDto[] bookDtos = this.gson.fromJson(readBooksFromFile(), BookDto[].class);
        for (BookDto bookDto : bookDtos) {

            Optional<Book> response =
                    this.bookRepository.findByTitle(bookDto.getTitle());

            if (!validationUtil.isValid(bookDto) || response.isPresent()) {
                sb.append("Invalid book").append(System.lineSeparator());
                continue;
            }

            Book forPersist = this.modelMapper.map(bookDto, Book.class);
            this.bookRepository.saveAndFlush(forPersist);
            sb.append(String.format("Successfully imported book %s - %s"
                            , bookDto.getAuthor(), bookDto.getTitle()))
                    .append(System.lineSeparator());
        }

        return sb.toString();
    }
}
