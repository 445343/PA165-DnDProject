package cz.fi.muni.PA165.persistence.dao;

import cz.fi.muni.PA165.persistence.model.Hero;

import java.util.List;

/**
 * DAO for DnD Hero character
 * @author Boris Jadus
 */
public interface HeroDao {
    public Hero findById(Long id);
    public void create(Hero hero);
    public void delete(Hero hero);
    public List<Hero> findAll();
    public void update(Hero hero);
}
