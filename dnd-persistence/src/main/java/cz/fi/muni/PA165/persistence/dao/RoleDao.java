package cz.fi.muni.PA165.persistence.dao;

import cz.fi.muni.PA165.persistence.model.Role;

import java.util.List;

/**
 * DAO for Role of DnD Character
 * @author Alena Bednarikova
 */
public interface RoleDao {
    public Role findById(Long id);
    public void create(Role role);
    public void delete(Role role);
    public List<Role> findAll();
    public void update(Role role);
}
