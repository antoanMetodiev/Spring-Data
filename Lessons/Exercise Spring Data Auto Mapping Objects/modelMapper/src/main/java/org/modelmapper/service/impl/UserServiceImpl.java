package org.modelmapper.service.impl;

import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.modelmapper.data.entities.User;
import org.modelmapper.data.repositories.UserRepository;
import org.modelmapper.service.UserService;
import org.modelmapper.service.dtos.UserRegisterDTO;
import org.modelmapper.util.ValidatorService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ValidatorService validatorService;

    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ValidatorService validatorService, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.validatorService = validatorService;
        this.modelMapper = modelMapper;
    }

    @Override
    public String registerUser(UserRegisterDTO userRegisterDTO) {
        if (!this.validatorService.isValid(userRegisterDTO)) {
            Set<ConstraintViolation<UserRegisterDTO>> set = validatorService.validate(userRegisterDTO);
            return set.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining("%n"));
        }

        if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())) {
            return "passwords dont match!";
        }

        User user = this.modelMapper.map(userRegisterDTO, User.class);
        if (this.userRepository.count() == 0) {
            user.setAdmin(true);
        }

        this.userRepository.saveAndFlush(user);
        return "User was registered!";
    }
}
