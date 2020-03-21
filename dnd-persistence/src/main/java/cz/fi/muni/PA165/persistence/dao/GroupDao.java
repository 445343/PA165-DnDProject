package cz.fi.muni.PA165.persistence.dao;


import cz.fi.muni.PA165.persistence.model.Group;
import java.util.List;

/**
 * DAO for DnD group of heroes
 * @author Jan Válka
 */
public interface GroupDao {
    public Group findById(Long id);
    public void create(Group group);
    public void delete(Group group);
    public List<Group> findAll();
    public void update(Group group);
}
