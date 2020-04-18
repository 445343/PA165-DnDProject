package service;

import cz.fi.muni.PA165.persistence.model.Role;

import java.util.List;
/**
 * Service for Role
 * @author Jan Válka
 */
public interface RoleService {
    /**
     * Find role by id
     * @param id of Role
     * @return role with corresponding id
     */
    Role findById(Long id);

    /**
     * Create new role
     * @param role to be created
     */
    void createRole(Role role);

    /**
     * Delete role with given id
     * @param id of role to be deleted
     */
    void deleteRole(Long id);

    /**
     * Update role
     * @param role to be updated
     */
    void updateRole(Role role);

    /**
     * Find all roles
     * @return all roles
     */
    List<Role> findAllRoles();
}
