package cz.fi.muni.PA165.persistence.dao;


import cz.fi.muni.PA165.persistence.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Implementation of UserDao interface
 *
 * @author Michal Caniga
 */

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findByName(String name) {
        try{
            return entityManager
                    .createQuery("SELECT u FROM User u WHERE u.userName = :name", User.class)
                    .setParameter("name", name).getSingleResult();
        } catch (NoResultException ex){
            return null;
        }
    }

    @Override
    public void create(User user) {
        entityManager.persist(user);
    }

    @Override
    public void delete(User user) throws IllegalArgumentException {
        entityManager.remove(findById(user.getId()));
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }
}
