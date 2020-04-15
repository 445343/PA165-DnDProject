package cz.fi.muni.PA165.persistence.dao;

import cz.fi.muni.PA165.persistence.model.Troop;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Implementation of TroopDao interface
 * @author Jan Válka
 */
@Repository
public class TroopDaoImpl implements TroopDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Troop findById(Long id){
        return em.find(Troop.class,id);
    }

    @Override
    public void create(Troop troop){
        em.persist(troop);
    }
    @Override
    public void delete(Troop troop) throws IllegalArgumentException{
        em.remove(findById(troop.getId()));

    }
    @Override
    public List<Troop> findAll(){
        return em.createQuery("SELECT t FROM Troop t", Troop.class).getResultList();
    }
    @Override
    public void update(Troop troop){
        em.merge(troop);

    }
}
