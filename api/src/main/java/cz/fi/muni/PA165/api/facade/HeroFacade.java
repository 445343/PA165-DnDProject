package cz.fi.muni.PA165.api.facade;

import cz.fi.muni.PA165.api.dto.hero.HeroCreateDTO;
import cz.fi.muni.PA165.api.dto.hero.HeroDTO;
import cz.fi.muni.PA165.api.dto.hero.HeroUpdateDTO;

import java.util.List;

/**
 * Interface for Hero facade
 *
 * @author Michal Caniga
 */

public interface HeroFacade {
    /**
     * Find hero by id
     *
     * @param id of hero
     * @return hero with corresponding id
     */
    HeroDTO findById(Long id);

    /**
     * Create new hero
     *
     * @param hero to be created
     */
    void createHero(HeroCreateDTO hero);

    /**
     * Delete hero with given id
     *
     * @param id of hero to be deleted
     */
    void deleteHero(Long id);

    /**
     * Update hero
     *
     * @param hero to be updated
     */
    void updateHero(HeroUpdateDTO hero);

    /**
     * Find all heroes
     *
     * @return all heroes
     */
    List<HeroDTO> findAllHeroes();

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
     */
    void leaveTroop(Long heroId);
}
