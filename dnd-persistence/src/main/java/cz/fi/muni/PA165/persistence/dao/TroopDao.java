package cz.fi.muni.PA165.persistence.dao;


import cz.fi.muni.PA165.persistence.model.Troop;
import java.util.List;

/**
 * DAO for DnD troop of heroes
 * @author Jan Válka
 */
public interface TroopDao {
    public Troop findById(Long id);
    public void create(Troop troop);
    public void delete(Troop troop);
    public List<Troop> findAll();
    public void update(Troop troop);
}
