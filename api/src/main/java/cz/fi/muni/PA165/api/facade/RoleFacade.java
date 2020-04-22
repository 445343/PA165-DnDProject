package cz.fi.muni.PA165.api.facade;

import cz.fi.muni.PA165.api.dto.role.RoleCreateDTO;
import cz.fi.muni.PA165.api.dto.role.RoleDTO;
import cz.fi.muni.PA165.api.dto.role.RoleUpdateDTO;

import java.util.List;

/**
 * Interface for Role facade
 * @author Jan Válka
 */
public interface RoleFacade {
    /**
     * Find role by id
     * @param id of Role
     * @return role with corresponding id
     */
    RoleDTO findById(Long id);

    /**
     * Create new role
     * @param role to be created
     */
    void createRole(RoleCreateDTO role);

    /**
     * Delete role with given id
     * @param id of role to be deleted
     */
    void deleteRole(Long id);

    /**
     * Update role
     * @param role to be updated
     */
    void updateRole(RoleUpdateDTO role);

    /**
     * Find all roles
     * @return all roles
     */
    List<RoleDTO> findAllRoles();
}
