package cz.fi.muni.PA165.persistence.dao;

import cz.fi.muni.PA165.persistence.model.Hero;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Implementation of HeroDao interface
 * @author Boris Jadus
 */
@Repository
public class HeroDaoImpl implements HeroDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Hero findById(Long id) {
        return entityManager.find(Hero.class, id);
    }

    @Override
    public void create(Hero hero) {
        entityManager.persist(hero);
    }

    @Override
    public void delete(Hero hero) throws IllegalArgumentException{
        entityManager.remove(findById(hero.getId()));
    }

    @Override
    public List<Hero> findAll() {
        return entityManager.createQuery("SELECT h FROM Hero h", Hero.class).getResultList();
    }

    @Override
    public void update(Hero hero) {
        entityManager.merge(hero);
    }
}
