package dao;

import cz.fi.muni.PA165.persistence.dao.DAOconfig;
import cz.fi.muni.PA165.persistence.dao.TroopDao;
import cz.fi.muni.PA165.persistence.model.Hero;
import cz.fi.muni.PA165.persistence.model.Troop;
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
 * Tests for TroopDao
 *
 * @author Michal Caniga
 */
@ContextConfiguration(classes = DAOconfig.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class TroopDaoTest extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    public EntityManager em;

    @Autowired
    public TroopDao troopDao;

    private Troop troop1, troop2;

    @BeforeMethod
    public void init() {
        Hero hero = new Hero();
        hero.setLevel(1);
        hero.setName("hero");

        troop1 = new Troop();
        troop1.setName("troop1");
        troop1.setGold(50);
        troop1.setMission("mission");
        troop1.addHero(hero);

        troop2 = new Troop();
        troop2.setName("troop2");
        troop2.setGold(100);
        troop2.setMission("mission2");
    }

    @Test
    public void findById() {
        em.persist(troop1);
        Troop foundTroop = troopDao.findById(troop1.getId());
        Assert.assertNotNull(troop1);
        Assert.assertEquals(troop1, foundTroop);
    }

    @Test
    public void findByIdWithTroopNotInDB() {
        Troop troop = troopDao.findById(64L);
        Assert.assertNull(troop);
    }

    @Test
    public void create() {
        troopDao.create(troop1);
        Assert.assertNotNull(troop1.getId());
        Assert.assertTrue(em.contains(troop1));
    }

    @Test(expectedExceptions = PersistenceException.class)
    public void createWithoutName() {
        troop1.setName(null);
        troopDao.create(troop1);
    }

    @Test
    public void createWithoutMission() {
        troop1.setMission(null);
        troopDao.create(troop1);
        Assert.assertTrue(em.contains(troop1));
    }

    @Test
    public void delete() {
        em.persist(troop1);
        Assert.assertTrue(em.contains(troop1));
        troopDao.delete(troop1);
        Assert.assertFalse(em.contains(troop1));
    }

    @Test
    public void findAll() {
        em.persist(troop1);
        em.persist(troop2);
        List<Troop> groups = troopDao.findAll();
        Assert.assertEquals(2, groups.size());
        Assert.assertTrue(groups.contains(troop1));
        Assert.assertTrue(groups.contains(troop2));
    }

    @Test
    public void update() {
        em.persist(troop1);
        troop2.setId(troop1.getId());
        troop2.setName("updated group");
        troop2.setMission("updated mission");
        troop2.setGold(456);
        troopDao.update(troop2);
        Assert.assertEquals(troop2, troop1);
    }
}
