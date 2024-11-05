package org.modelmapper.service;

import org.modelmapper.service.dtos.UserRegisterDTO;

public interface UserService {

    String registerUser(UserRegisterDTO userRegisterDTO);
}
