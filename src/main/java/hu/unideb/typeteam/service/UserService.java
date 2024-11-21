package hu.unideb.typeteam.service;

import hu.unideb.typeteam.dto.UserDto;
import hu.unideb.typeteam.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);
    List<User> findAllUsers();
    User findUserByUserId(String userId);
    User findUserByEmail(String email);
    void deleteById(String id);
}
