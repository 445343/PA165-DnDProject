package cz.fi.muni.PA165.persistence.dao;

import cz.fi.muni.PA165.persistence.model.Group;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Implementation of GroupDao interface
 * @author Jan Válka
 */
@Repository
public class GroupDaoImpl implements GroupDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Group findById(Long id){
        return em.find(Group.class,id);
    }

    @Override
    public void create(Group group){
        em.persist(group);
    }
    @Override
    public void delete(Group group) throws IllegalArgumentException{
        em.remove(findById(group.getId()));

    }
    @Override
    public List<Group> findAll(){
        return em.createQuery("SELECT g FROM Group g", Group.class).getResultList();
    }
    @Override
    public void update(Group group){
        em.merge(group);

    }
}
