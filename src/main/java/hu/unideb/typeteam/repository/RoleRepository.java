package hu.unideb.typeteam.repository;

import hu.unideb.typeteam.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, String> {
    Role findByRole(String role);

    @Query("SELECT r FROM Role r WHERE r.roleId IN :roleIds")
    List<Role> findRolesByIds(List<Integer> roleIds);
}
