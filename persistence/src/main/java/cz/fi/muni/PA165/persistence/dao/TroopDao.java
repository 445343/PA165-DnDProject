package cz.fi.muni.PA165.persistence.dao;


import cz.fi.muni.PA165.persistence.model.Troop;

import java.util.List;

/**
 * DAO for DnD troop of heroes
 *
 * @author Jan Válka
 */
public interface TroopDao {
    /**
     * Finds troop by given ID
     *
     * @param id id of troop
     * @return troop with given id
     */
    public Troop findById(Long id);

    /**
     * Create troop in DB
     *
     * @param troop troop to create
     */
    public void create(Troop troop);

    /**
     * Deletes troop from DB
     *
     * @param troop troop to delete
     */
    public void delete(Troop troop);

    /**
     * Finds all stored troops
     *
     * @return List of all troops
     */
    public List<Troop> findAll();

    /**
     * Updates troop in DB
     *
     * @param troop troop to update
     */
    public void update(Troop troop);
}
