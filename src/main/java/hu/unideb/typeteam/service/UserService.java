package hu.unideb.typeteam.service;

import hu.unideb.typeteam.dto.UserDto;
import hu.unideb.typeteam.entity.User;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByUserId(String userId);
    User findUserByEmail(String email);
}
