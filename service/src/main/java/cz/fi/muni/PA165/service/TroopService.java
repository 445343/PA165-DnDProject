package cz.fi.muni.PA165.service;

import cz.fi.muni.PA165.persistence.model.Troop;

import java.util.List;

/**
 * Service for Troops
 * @author Alena Bednarikova
 */
public interface TroopService {
    /**
     * Find Troop by id
     *
     * @param id of Troop
     * @return Troop with corresponding id
     */
    Troop findById(Long id);

    /**
     * Create new Troop
     *
     * @param troop to be created
     */
    void createTroop(Troop troop);

    /**
     * Delete Troop with given id
     *
     * @param id of Troop to be deleted
     */
    void deleteTroop(Long id);

    /**
     * Update troop
     *
     * @param troop to be updated
     */
    void updateTroop(Troop troop);

    /**
     * Find all troops
     *
     * @return all troops
     */
    List<Troop> findAllTroops();
}
