package hu.unideb.typeteam.service;

import hu.unideb.typeteam.entity.Role;
import hu.unideb.typeteam.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public List<Role> findRolesByIds(List<Integer> roleIds) {
        return roleRepository.findRolesByIds(roleIds);
    }
}
