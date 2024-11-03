package hu.unideb.typeteam.repository;

import hu.unideb.typeteam.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
    Role findByRole(String role);
}
