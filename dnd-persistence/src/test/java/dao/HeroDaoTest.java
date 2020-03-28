package dao;

import cz.fi.muni.PA165.persistence.DAOconfig;
import cz.fi.muni.PA165.persistence.dao.HeroDao;
import cz.fi.muni.PA165.persistence.model.Hero;
import cz.fi.muni.PA165.persistence.model.Role;
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

@ContextConfiguration(classes = DAOconfig.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class HeroDaoTest extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    public EntityManager em;

    @Autowired
    public HeroDao heroDao;

    private Hero hero1, hero2;

    @BeforeMethod
    public void init() {

        Troop troop = new Troop();
        troop.setMission("mission1");
        troop.setName("troop");
        troop.setGold(50);

        Role role = new Role();
        role.setName("role");
        role.setDescription("description");

        hero1 = new Hero();
        hero1.setName("hero1");
        hero1.setLevel(1);
        hero1.setTroop(troop);
        hero1.addRole(role);

        hero2 = new Hero();
        hero2.setName("hero2");
        hero2.setLevel(2);
        hero2.setTroop(troop);
    }

    @Test
    public void findById(){
        em.persist(hero1);
        Hero foundHero = heroDao.findById(hero1.getId());
        Assert.assertNotNull(foundHero);
        Assert.assertEquals(hero1, foundHero);
    }

    @Test
    public void findByIdWithUserNotInDB(){
        Hero hero = heroDao.findById(598L);
        Assert.assertNull(hero);
    }

    @Test
    public void create(){
        heroDao.create(hero1);
        Assert.assertTrue(em.contains(hero1));
        Assert.assertNotNull(hero1.getId());
        Assert.assertTrue(em.contains(hero1));
    }

    @Test(expectedExceptions = PersistenceException.class)
    public void createWithoutName(){
        hero1.setName(null);
        heroDao.create(hero1);
    }

    @Test
    public void delete(){
        em.persist(hero1);
        Assert.assertTrue(em.contains(hero1));
        heroDao.delete(hero1);
        Assert.assertFalse(em.contains(hero1));
    }

    @Test
    public void findAll(){
        em.persist(hero1);
        em.persist(hero2);
        List<Hero> heroes = heroDao.findAll();
        Assert.assertEquals(2, heroes.size());
        Assert.assertTrue(heroes.contains(hero1));
        Assert.assertTrue(heroes.contains(hero2));
    }

    @Test
    public void update(){
        em.persist(hero1);
        hero2.setId(hero1.getId());
        hero2.setName("newName");
        hero2.setLevel(5);

        heroDao.update(hero2);
        Assert.assertEquals(hero2, hero1);
    }
}
