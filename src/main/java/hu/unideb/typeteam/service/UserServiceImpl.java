package hu.unideb.typeteam.service;

import hu.unideb.typeteam.entity.Role;
import hu.unideb.typeteam.entity.User;
import hu.unideb.typeteam.repository.RoleRepository;
import hu.unideb.typeteam.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            Role defaultRole = roleRepository.findByRole("ROLE_USER");
            if (defaultRole == null) {
                defaultRole = new Role();
                defaultRole.setRole("ROLE_USER");
                roleRepository.save(defaultRole);
            }
            user.setRoles(new ArrayList<>(List.of(defaultRole)));
        }

        userRepository.save(user);
    }


    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }


    @Override
    public User findUserByUserId(String userId) {
        return userRepository.findByUserId(userId);

    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setRole("USER");
        return roleRepository.save(role);
    }
}
