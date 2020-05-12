package dao;

import cz.fi.muni.PA165.persistence.dao.DAOconfig;
import cz.fi.muni.PA165.persistence.dao.UserDao;
import cz.fi.muni.PA165.persistence.model.Hero;
import cz.fi.muni.PA165.persistence.model.User;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ContextConfiguration(classes = DAOconfig.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class UserDaoTest extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    public EntityManager em;

    @Autowired
    public UserDao userDao;

    private User user1, user2;

    @BeforeMethod
    public void init(){

        Hero hero = new Hero();
        hero.setLevel(9);
        hero.setName("name");

        user1 = new User();
        user1.setUserName("user1");
        user1.setPasswordHash("xzkjfkjsaflksa2121adsf10");
        user1.setAdmin(true);
        user1.setHeroes(new HashSet<>(Set.of(hero)));
        
        user2 = new User();
        user2.setUserName("user2");
        user2.setPasswordHash("mxclklkslksd2121sfdg21xchjs");
        user2.setAdmin(false);
    }

    @Test
    public void findById(){
        em.persist(user1);
        User foundUser = userDao.findById(user1.getId());
        Assert.assertNotNull(foundUser);
        Assert.assertEquals(user1, foundUser);
    }

    @Test
    public void findByIdWithUserNotInDB(){
        User user = userDao.findById(100L);
        Assert.assertNull(user);
    }

    @Test
    public void create(){
        userDao.create(user2);
        Assert.assertNotNull(user2.getId());
        Assert.assertTrue(em.contains(user2));
    }

    @Test(expectedExceptions = PersistenceException.class)
    public void createWithoutName(){
        user1.setUserName(null);
        userDao.create(user1);
    }

    @Test(expectedExceptions = PersistenceException.class)
    public void createWithoutPassword(){
        user1.setPasswordHash(null);
        userDao.create(user1);
    }

    @Test
    public void delete(){
        em.persist(user1);
        Assert.assertTrue(em.contains(user1));
        userDao.delete(user1);
        Assert.assertFalse(em.contains(user1));
    }

    @Test
    public void findAll(){
        em.persist(user1);
        em.persist(user2);
        List<User> users = userDao.findAll();
        Assert.assertEquals(2, users.size());
        Assert.assertTrue(users.contains(user1));
        Assert.assertTrue(users.contains(user2));
    }

    @Test
    public void update(){
        em.persist(user1);
        user2.setId(user1.getId());
        user2.setUserName("updatedName");
        user2.setAdmin(false);
        user2.setPasswordHash("updatedHash");
        userDao.update(user2);
        Assert.assertEquals(user2, user1);
    }
}
