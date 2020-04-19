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
}
