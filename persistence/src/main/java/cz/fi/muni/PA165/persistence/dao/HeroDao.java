package cz.fi.muni.PA165.persistence.dao;

import cz.fi.muni.PA165.persistence.model.Hero;

import java.util.List;

/**
 * DAO for DnD Hero character
 *
 * @author Boris Jadus
 */
public interface HeroDao {
    /**
     * Finds hero by given ID
     *
     * @param id id of hero
     * @return hero with given id
     */
    Hero findById(Long id);

    /**
     * Create hero in DB
     *
     * @param hero hero to create
     */
    Long create(Hero hero);


    /**
     * Deletes hero from DB
     *
     * @param hero hero to delete
     */
    void delete(Hero hero);

    /**
     * Finds all stored heroes
     *
     * @return List of all heroes
     */
    List<Hero> findAll();

    /**
     * Updates hero in DB
     *
     * @param hero hero to update
     */
    void update(Hero hero);
}
