package cz.fi.muni.PA165.api.facade;

import cz.fi.muni.PA165.api.dto.troop.TroopCreateDTO;
import cz.fi.muni.PA165.api.dto.troop.TroopDTO;
import cz.fi.muni.PA165.api.dto.troop.TroopUpdateDTO;

import java.util.List;

/**
 * Interface for Troop facade
 *
 * @author Alena Bednarikova
 */
public interface TroopFacade {
    /**
     * Find Troop by id
     *
     * @param id of Troop
     * @return Troop with corresponding id
     */
    TroopDTO findById(Long id);

    /**
     * Create new Troop
     *
     * @param troop to be created
     */
    Long createTroop(TroopCreateDTO troop);

    /**
     * Disband Troop with given id
     * All heroes leave that troop, then troop gets deleted
     *
     * @param id of Troop to be disbanded
     */
    void disbandTroop(Long id);

    /**
     * Update troop
     *
     * @param troop to be updated
     */
    void updateTroop(TroopUpdateDTO troop);

    /**
     * Find all troops
     *
     * @return all troops
     */
    List<TroopDTO> findAllTroops();
}
