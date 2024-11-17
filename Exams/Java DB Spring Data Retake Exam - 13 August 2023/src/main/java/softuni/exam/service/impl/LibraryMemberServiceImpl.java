package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.jsons.LibraryMemberDto;
import softuni.exam.models.entity.LibraryMember;
import softuni.exam.repository.LibraryMemberRepository;
import softuni.exam.service.LibraryMemberService;
import softuni.exam.util.ValidationUtilImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

// TODO: Implement all methods

@Service
public class LibraryMemberServiceImpl implements LibraryMemberService {
    private final String FILE_PATH = "src/main/resources/files/json/library-members.json";

    private final LibraryMemberRepository libraryMemberRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtilImpl validationUtil;

    public LibraryMemberServiceImpl(LibraryMemberRepository libraryMemberRepository, ModelMapper modelMapper, Gson gson, ValidationUtilImpl validationUtil) {
        this.libraryMemberRepository = libraryMemberRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return libraryMemberRepository.count() > 0;
    }

    @Override
    public String readLibraryMembersFileContent() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importLibraryMembers() throws IOException {
        StringBuilder sb = new StringBuilder();

        System.out.println();
        LibraryMemberDto[] libraryMemberDto =
                this.gson.fromJson(readLibraryMembersFileContent(), LibraryMemberDto[].class);

        for (LibraryMemberDto memberDto : libraryMemberDto) {
            Optional<LibraryMember> response =
                    this.libraryMemberRepository.findByPhoneNumber(memberDto.getPhoneNumber());

            if (!this.validationUtil.isValid(memberDto) || response.isPresent()) {
                sb.append("Invalid library member").append(System.lineSeparator());
                continue;
            }

            LibraryMember forPersist =
                    this.modelMapper.map(memberDto, LibraryMember.class);
            this.libraryMemberRepository.saveAndFlush(forPersist);
            sb.append(String.format("Successfully imported library member %s - %s",
                            memberDto.getFirstName(), memberDto.getLastName()))
                    .append(System.lineSeparator());
        }

        return sb.toString();
    }
}
