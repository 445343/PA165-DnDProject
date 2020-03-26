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
    public Role findById(Long id);

    /**
     * Create role in DB
     *
     * @param role role to create
     */
    public void create(Role role);

    /**
     * Deletes role from DB
     *
     * @param role role to delete
     */
    public void delete(Role role);

    /**
     * Finds all stored roles
     *
     * @return List of all roles
     */
    public List<Role> findAll();

    /**
     * Updates role in DB
     *
     * @param role role to update
     */
    public void update(Role role);
}
