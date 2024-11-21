package hu.unideb.typeteam.repository;

import hu.unideb.typeteam.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUserId(String userId);
    List<User> findAll();
    User findByEmail(String email);
}