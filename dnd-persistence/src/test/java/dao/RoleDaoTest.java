package dao;

import cz.fi.muni.PA165.persistence.DAOconfig;
import cz.fi.muni.PA165.persistence.dao.RoleDao;
import cz.fi.muni.PA165.persistence.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

import java.util.List;



/**
 * Tests for RoleDao
 *
 * @author Jan Válka
 */
@ContextConfiguration(classes = DAOconfig.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class RoleDaoTest extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    public EntityManager em;

    @Autowired
    public RoleDao roleDao;

    private Role role1, role2;

    @BeforeMethod
    public void init(){

        role1 = new Role();
        role1.setName("Warrior");
        role1.setDescription("Brave soldier prepared for adventure");

        role2 = new Role();
        role2.setName("Wizard");
        role2.setDescription("Wise person wielding magical powers");
    }

    @Test
    public void findById(){
        Role returnedRole = roleDao.findById(Long.valueOf("1"));
        Assert.assertNull(returnedRole);
        em.persist(role1);
        returnedRole = roleDao.findById(role1.getId());
        Assert.assertNotNull(returnedRole);
        Assert.assertEquals(returnedRole,role1);
        em.persist(role2);
        returnedRole = roleDao.findById(role2.getId());
        Assert.assertNotEquals(returnedRole,role1);

    }

    @Test
    public void create(){
        roleDao.create(role1);
        roleDao.create(role2);
        Assert.assertTrue(em.contains(role1));
        Assert.assertTrue(em.contains(role2));
        Assert.assertNotNull(role1.getId());
        Assert.assertNotNull(role2.getId());
        Assert.assertNotEquals(role1.getId(),role2.getId());

    }

    @Test(expectedExceptions = PersistenceException.class)
    public void createNullName(){
        role1.setName(null);
        roleDao.create(role1);
    }

    @Test
    public void delete(){
        Assert.assertFalse(em.contains(role1));
        em.persist(role1);
        em.persist(role2);
        Assert.assertTrue(em.contains(role1));
        Assert.assertTrue(em.contains(role2));
        roleDao.delete(role1);
        Assert.assertFalse(em.contains(role1));
        Assert.assertTrue(em.contains(role2));

    }

    @Test
    public void findAll(){
        List<Role> roles = roleDao.findAll();
        Assert.assertEquals(0,roles.size());
        em.persist(role1);
        roles = roleDao.findAll();
        Assert.assertEquals(1,roles.size());
        em.persist(role2);
        roles = roleDao.findAll();
        Assert.assertEquals(2,roles.size());
        em.remove(role2);
        roles = roleDao.findAll();
        Assert.assertEquals(1,roles.size());

    }

    @Test
    public void update(){
        em.persist(role1);
        Assert.assertEquals(role1,em.find(Role.class,role1.getId()));
        role1.setName("New name of role");
        roleDao.update(role1);
        Assert.assertEquals(role1,em.find(Role.class,role1.getId()));
        Assert.assertNotEquals(role2,em.find(Role.class,role1.getId()));
        role2.setId(role1.getId());
        role2.setName(role1.getName());
        role2.setDescription(role1.getDescription());
        roleDao.update(role2);
        Assert.assertEquals(role2,em.find(Role.class,role1.getId()));


    }
    @Test(expectedExceptions = PersistenceException.class)
    public void updateNameToNull(){
        role1.setName(null);
        roleDao.update(role1);
    }

}