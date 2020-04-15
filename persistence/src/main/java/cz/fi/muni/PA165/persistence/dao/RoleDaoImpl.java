package cz.fi.muni.PA165.persistence.dao;

import cz.fi.muni.PA165.persistence.model.Hero;
import cz.fi.muni.PA165.persistence.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Role DAO Implementation
 * @author Alena Bednarikova
 */
@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role findById(Long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public void create(Role role) {
        entityManager.persist(role);
    }

    @Override
    public void delete(Role role) {
        entityManager.remove(findById(role.getId()));
    }

    @Override
    public List<Role> findAll() {
        return entityManager.createQuery("SELECT role FROM Role role", Role.class).getResultList();
    }

    @Override
    public void update(Role role) {
        entityManager.merge(role);
    }
}
