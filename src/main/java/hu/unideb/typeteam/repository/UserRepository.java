package hu.unideb.typeteam.repository;

import hu.unideb.typeteam.entity.Role;
import hu.unideb.typeteam.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUserId(String userId);
    User findByEmail(String email);
}