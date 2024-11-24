package hu.unideb.typeteam.service;

import hu.unideb.typeteam.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();

    List<Role> findRolesByIds(List<Integer> roleIds);
}
