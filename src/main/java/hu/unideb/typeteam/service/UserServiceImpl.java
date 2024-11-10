package hu.unideb.typeteam.service;

import hu.unideb.typeteam.dto.UserDto;
import hu.unideb.typeteam.entity.Role;
import hu.unideb.typeteam.entity.User;
import hu.unideb.typeteam.repository.RoleRepository;
import hu.unideb.typeteam.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());

        Role role = roleRepository.findByRole("USER");
        if (role == null) {
            role = checkRoleExist();
        }
        user.setRoles(List.of(role));
        user.setActive(true);
        userRepository.save(user);
    }


    @Override
    public User findUserByUserId(String userId) {
        return userRepository.findByUserId(userId);

    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setRole("USER");
        return roleRepository.save(role);
    }
}
