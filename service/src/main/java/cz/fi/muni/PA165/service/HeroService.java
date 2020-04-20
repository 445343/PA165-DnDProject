package cz.fi.muni.PA165.service;

import cz.fi.muni.PA165.persistence.model.Hero;

import java.util.List;

/**
 * Service for Hero
 *
 * @author Michal Caniga
 */

public interface HeroService {
    /**
     * Find Hero by id
     *
     * @param id of Hero
     * @return Hero with corresponding id
     */
    Hero findById(Long id);

    /**
     * Create new Hero
     *
     * @param Hero to be created
     */
    void createHero(Hero Hero);

    /**
     * Delete Hero with given id
     *
     * @param id of Hero to be deleted
     */
    void deleteHero(Long id);

    /**
     * Update hero
     *
     * @param Hero to be updated
     */
    void updateHero(Hero Hero);

    /**
     * Find all heroes
     *
     * @return all heroes
     */
    List<Hero> findAllHeroes();


    /**
     * Adds role to hero
     *
     * @param heroId - id of the hero
     * @param roleId - id of the role
     */
    void addRole(Long heroId, Long roleId);

    /**
     * Removes role from hero
     *
     * @param heroId - id of the hero
     * @param roleId - id of the role
     */
    void removeRole(Long heroId, Long roleId);

    /**
     * Adds hero to troop
     *
     * @param heroId  - id of the hero
     * @param troopId - id of the troop
     */

    void joinTroop(Long heroId, Long troopId);

    /**
     * Removes hero from troop
     *
     * @param heroId  - id of the hero
     * @param troopId - id of the troop
     */
    void leaveTroop(Long heroId, Long troopId);
}
