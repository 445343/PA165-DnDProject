package dao;

import cz.fi.muni.PA165.persistence.DAOconfig;
import cz.fi.muni.PA165.persistence.dao.GroupDao;
import cz.fi.muni.PA165.persistence.model.Group;
import cz.fi.muni.PA165.persistence.model.Hero;
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
 * Tests for GroupDao
 *
 * @author Michal Caniga
 */
@ContextConfiguration(classes = DAOconfig.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class GroupDaoTest extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    public EntityManager em;

    @Autowired
    public GroupDao groupDao;

    private Group group1, group2;

    @BeforeMethod
    public void init() {
        Hero hero = new Hero();
        hero.setLevel(1);
        hero.setName("hero");

        group1 = new Group();
        group1.setName("group1");
        group1.setGold(50);
        group1.setMission("mission");
        group1.addHero(hero);

        group2 = new Group();
        group2.setName("group2");
        group2.setGold(100);
        group2.setMission("mission2");
    }

    @Test
    public void findById() {
        em.persist(group1);
        Group foundGroup = groupDao.findById(group1.getId());
        Assert.assertNotNull(group1);
        Assert.assertEquals(group1, foundGroup);
    }

    @Test
    public void findByIdWithGroupNotInDB() {
        Group group = groupDao.findById(64L);
        Assert.assertNull(group);
    }

    @Test
    public void create() {
        groupDao.create(group1);
        Assert.assertNotNull(group1.getId());
        Assert.assertTrue(em.contains(group1));
    }

    @Test(expectedExceptions = PersistenceException.class)
    public void createWithoutName() {
        group1.setName(null);
        groupDao.create(group1);
    }

    @Test
    public void createWithoutMission() {
        group1.setMission(null);
        groupDao.create(group1);
        Assert.assertTrue(em.contains(group1));
    }

    /*
    @Test(expectedExceptions = PersistenceException.class)
    public void createWithNegativeAmountOfGold(){
        group1.setGold(-20);
        groupDao.create(group1);
    }
    */

    @Test
    public void delete() {
        em.persist(group1);
        Assert.assertTrue(em.contains(group1));
        groupDao.delete(group1);
        Assert.assertFalse(em.contains(group1));
    }

    @Test
    public void findAll() {
        em.persist(group1);
        em.persist(group2);
        List<Group> groups = groupDao.findAll();
        Assert.assertEquals(2, groups.size());
        Assert.assertTrue(groups.contains(group1));
        Assert.assertTrue(groups.contains(group2));
    }

    @Test
    public void update(){
        em.persist(group1);
        group2.setId(group1.getId());
        group2.setName("updated group");
        group2.setMission("updated mission");
        group2.setGold(456);
        groupDao.update(group2);
        Assert.assertEquals(group2, group1);
    }
}
