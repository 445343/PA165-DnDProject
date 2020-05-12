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
    Troop findById(Long id);

    /**
     * Create troop in DB
     *
     * @param troop troop to create
     */
    Long create(Troop troop);

    /**
     * Deletes troop from DB
     *
     * @param troop troop to delete
     */
     void delete(Troop troop);

    /**
     * Finds all stored troops
     *
     * @return List of all troops
     */
    List<Troop> findAll();

    /**
     * Updates troop in DB
     *
     * @param troop troop to update
     */
    void update(Troop troop);
}
