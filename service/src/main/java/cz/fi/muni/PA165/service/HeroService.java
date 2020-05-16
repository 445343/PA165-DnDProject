package cz.fi.muni.PA165.service;

import cz.fi.muni.PA165.persistence.model.Hero;
import cz.fi.muni.PA165.api.exceptions.DnDServiceException;
import cz.fi.muni.PA165.persistence.model.Role;

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
     * @throws DnDServiceException if hero is not found.
     * @return Hero with corresponding id
     */
    Hero findById(Long id);

    /**
     * Create new Hero
     *
     * @param Hero to be created
     */
    Long createHero(Hero Hero);

    /**
     * Delete Hero with given id
     *
     * @param id of Hero to be deleted
     * @throws DnDServiceException if hero is not found.
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
     * @throws DnDServiceException if hero or role is not found.
     */
    void addRole(Long heroId, Long roleId);

    /**
     * Removes role from hero
     *
     * @param heroId - id of the hero
     * @param roleId - id of the role
     * @throws DnDServiceException if hero or role is not found.
     */
    void removeRole(Long heroId, Long roleId);

    /**
     * Adds hero to troop
     *
     * @param heroId  - id of the hero
     * @param troopId - id of the troop
     * @throws DnDServiceException if hero is already in another troop or troop was not found.
     */
    void joinTroop(Long heroId, Long troopId);

    /**
     * Removes hero from troop
     *
     * @param heroId  - id of the hero
     */
    void leaveTroop(Long heroId);

    /**
     * List all roles that are not assigned to a hero
     * @param heroId id of hero
     * @return List of all roles that are not assigned to a hero
     */
    List<Role> listAllRolesNotInHero(Long heroId);
}
