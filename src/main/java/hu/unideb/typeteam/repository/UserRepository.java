package hu.unideb.typeteam.repository;

import hu.unideb.typeteam.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUserId(String userId);
    User findByEmail(String email);
}