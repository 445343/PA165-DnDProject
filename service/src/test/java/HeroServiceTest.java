import cz.fi.muni.PA165.api.exceptions.DnDServiceException;
import cz.fi.muni.PA165.persistence.dao.HeroDao;
import cz.fi.muni.PA165.persistence.dao.RoleDao;
import cz.fi.muni.PA165.persistence.dao.TroopDao;
import cz.fi.muni.PA165.persistence.model.Hero;
import cz.fi.muni.PA165.persistence.model.Role;
import cz.fi.muni.PA165.persistence.model.Troop;
import cz.fi.muni.PA165.service.HeroService;
import cz.fi.muni.PA165.service.HeroServiceImpl;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Tests for Hero service
 *
 * @author Jan Válka
 */
public class HeroServiceTest {

    private HeroService heroService;
    private Hero hero;
    private Troop troop;
    private Role role;

    @Mock
    private HeroDao heroDao;
    @Mock
    private RoleDao roleDao;
    @Mock
    private TroopDao troopDao;


    @BeforeMethod
    public void init(){
        MockitoAnnotations.initMocks(this);
        heroService = new HeroServiceImpl(heroDao, roleDao, troopDao);

        hero = new Hero();
        hero.setId(1L);
        hero.setLevel(1);
        hero.setName("TestHero");

        troop = new Troop();
        troop.setName("TestTroop");
        troop.setId(1L);
        troop.setGold(4654);
        troop.setMission("Some mission");

        role = new Role();
        role.setId(1L);
        role.setName("TestRole");
        role.setDescription("Some testing string");
        hero.addRole(role);

    }

    @Test
    public void findById(){
        given(heroDao.findById(hero.getId())).willReturn(hero);
        Hero hero1 = heroService.findById(hero.getId());
        assertEquals(hero1, hero);
        then(heroDao).should().findById(hero.getId());

    }

    @Test(expectedExceptions = DnDServiceException.class)
    public void findByIdWithException(){
        given(heroDao.findById(anyLong())).willReturn(null);
        heroService.findById(2L);
    }

    @Test
    public void createHero(){
        heroService.createHero(hero);
        then(heroDao).should().create(hero);
    }


    @Test
    public void deleteHero(){
        given(heroDao.findById(hero.getId())).willReturn(hero);
        heroService.deleteHero(hero.getId());
        then(heroDao).should().delete(hero);
    }

    @Test(expectedExceptions = DnDServiceException.class)
    public void deleteHeroBadId(){
        heroService.deleteHero(566L);
        then(heroDao).should().delete(hero);
    }

    @Test
    public void updateHero(){
        hero.setName("New name");
        heroService.updateHero(hero);
        then(heroDao).should().update(hero);
    }


    @Test
    public void findAllHeroes(){
        Hero newHero = new Hero();
        newHero.setId(2L);
        newHero.setLevel(1);
        newHero.setName("Another hero");

        given(heroDao.findAll()).willReturn(List.of(hero, newHero));
        List<Hero> heroes = heroService.findAllHeroes();
        assertEquals(heroes.size(), 2);
        assertTrue(heroes.contains(hero));
        assertTrue(heroes.contains(newHero));
        then(heroDao).should().findAll();
    }

    @Test
    public void addRole(){

        given(heroDao.findById(hero.getId())).willReturn(hero);
        Set<Role> allRoles = hero.getRoles();
        assertEquals(allRoles.size(), 1);
        assertTrue(allRoles.contains(role));

        Role newRole = new Role();
        newRole.setId(2L);
        newRole.setName("New Role");
        given(roleDao.findById(newRole.getId())).willReturn(newRole);
        heroService.addRole(hero.getId(), newRole.getId());

        allRoles = hero.getRoles();
        assertEquals(allRoles.size(), 2);
        assertTrue(allRoles.contains(role));
        assertTrue(allRoles.contains(newRole));

    }
    @Test
    public void removeRole(){
        given(heroDao.findById(hero.getId())).willReturn(hero);
        given(roleDao.findById(role.getId())).willReturn(role);
        heroService.removeRole(hero.getId(), role.getId());
        assertEquals(hero.getRoles().size(), 0);
    }

    @Test(expectedExceptions = DnDServiceException.class)
    public void removeRoleWrongHeroId(){
        given(roleDao.findById(role.getId())).willReturn(role);
        heroService.removeRole(3L, role.getId());
        assertEquals(hero.getRoles().size(), 0);
    }

    @Test(expectedExceptions = DnDServiceException.class)
    public void removeRoleWrongRoleId(){
        given(heroDao.findById(hero.getId())).willReturn(hero);
        heroService.removeRole(hero.getId(), 42L);
        assertEquals(hero.getRoles().size(), 0);
    }

    @Test(expectedExceptions = DnDServiceException.class)
    public void removeRoleWrongBoth(){
        heroService.removeRole(545L, 42L);
        assertEquals(hero.getRoles().size(), 0);
    }

    @Test
    public void joinTroop(){
        given(heroDao.findById(hero.getId())).willReturn(hero);
        given(troopDao.findById(troop.getId())).willReturn(troop);
        heroService.joinTroop(hero.getId(), troop.getId());
        assertEquals(hero.getTroop(), troop);
    }

    @Test(expectedExceptions = DnDServiceException.class)
    public void joinTroopWhileInATroop(){
        hero.setTroop(new Troop());
        given(heroDao.findById(anyLong())).willReturn(hero);
        given(troopDao.findById(anyLong())).willReturn(troop);

        heroService.joinTroop(hero.getId(), troop.getId());
    }

    @Test
    public void leaveTroop(){
        given(heroDao.findById(hero.getId())).willReturn(hero);
        given(troopDao.findById(troop.getId())).willReturn(troop);
        heroService.leaveTroop(hero.getId(), troop.getId());
        assertEquals(hero.getTroop(),null);
    }

}
