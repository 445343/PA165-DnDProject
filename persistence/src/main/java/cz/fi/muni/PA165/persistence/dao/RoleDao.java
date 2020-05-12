package cz.fi.muni.PA165.persistence.dao;

import cz.fi.muni.PA165.persistence.model.Role;

import java.util.List;

/**
 * DAO for Role of DnD Character
 *
 * @author Alena Bednarikova
 */
public interface RoleDao {
    /**
     * Finds role by given ID
     *
     * @param id id of role
     * @return role with given id
     */
    Role findById(Long id);

    /**
     * Create role in DB
     *
     * @param role role to create
     */
    Long create(Role role);

    /**
     * Deletes role from DB
     *
     * @param role role to delete
     */
    void delete(Role role);

    /**
     * Finds all stored roles
     *
     * @return List of all roles
     */
    List<Role> findAll();

    /**
     * Updates role in DB
     *
     * @param role role to update
     */
    void update(Role role);
}
