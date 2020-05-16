package facade;

import cz.fi.muni.PA165.api.dto.hero.HeroCreateDTO;
import cz.fi.muni.PA165.api.dto.hero.HeroDTO;
import cz.fi.muni.PA165.api.dto.hero.HeroUpdateDTO;
import cz.fi.muni.PA165.api.facade.HeroFacade;
import cz.fi.muni.PA165.persistence.model.Hero;
import cz.fi.muni.PA165.persistence.model.Role;
import cz.fi.muni.PA165.persistence.model.Troop;
import cz.fi.muni.PA165.service.HeroService;
import cz.fi.muni.PA165.service.facade.HeroFacadeImpl;
import cz.fi.muni.PA165.service.mapping.BeanMapper;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.testng.Assert.assertEquals;


/**
 * Tests for Hero facade
 *
 * @author Jan Válka
 */
public class HeroFacadeTest {

    private HeroFacade heroFacade;
    @Mock
    private HeroService heroService;
    @Mock
    private BeanMapper beanMapper;

    private Hero hero;
    private HeroDTO heroDTO;
    private HeroCreateDTO heroCreateDTO;
    private HeroUpdateDTO heroUpdateDTO;
    private Role role;
    private Troop troop;

    @BeforeMethod
    public void init(){

        MockitoAnnotations.initMocks(this);
        heroFacade = new HeroFacadeImpl(heroService,beanMapper);

        hero = new Hero();
        hero.setId(1L);
        hero.setLevel(1);
        hero.setName("TestHero");

        heroDTO = new HeroDTO();
        heroDTO.setId(hero.getId());
        heroDTO.setLevel(hero.getLevel());
        heroDTO.setName(hero.getName());

        heroCreateDTO = new HeroCreateDTO();
        heroCreateDTO.setLevel(hero.getLevel());
        heroCreateDTO.setName(hero.getName());

        heroUpdateDTO = new HeroUpdateDTO();
        heroUpdateDTO.setId(hero.getId());
        heroUpdateDTO.setLevel(hero.getLevel());
        heroUpdateDTO.setName(hero.getName());

        troop = new Troop();
        troop.setName("TestTroop");
        troop.setId(1L);
        troop.setGold(4654);
        troop.setMission("Some mission");

        role = new Role();
        role.setId(1L);
        role.setName("TestRole");
        role.setDescription("Some testing string");
    }

    @Test
    public void findById(){
        given(heroService.findById(hero.getId())).willReturn(hero);
        given(beanMapper.mapTo(hero, HeroDTO.class)).willReturn(heroDTO);
        HeroDTO result = heroFacade.findById(hero.getId());
        assertEquals(heroDTO, result);
        then(heroService).should().findById(hero.getId());
    }

    @Test
    public void createHero(){
        given(beanMapper.mapTo(heroCreateDTO, Hero.class)).willReturn(hero);
        heroFacade.createHero(heroCreateDTO);
        then(heroService).should().createHero(hero);
    }

    @Test
    public void deleteHero(){
        heroFacade.deleteHero(hero.getId());
        then(heroService).should().deleteHero(hero.getId());
    }

    @Test
    public void updateHero(){
        given(beanMapper.mapTo(heroUpdateDTO, Hero.class)).willReturn(hero);
        heroFacade.updateHero(heroUpdateDTO);
        then(heroService).should().updateHero(hero);
    }

    @Test
    public void addRole(){
        heroFacade.addRole(hero.getId(), role.getId());
        then(heroService).should().addRole(hero.getId(), role.getId());
    }

    @Test
    public void removeRole(){
        heroFacade.removeRole(hero.getId(), role.getId());
        then(heroService).should().removeRole(hero.getId(), role.getId());
    }

    @Test
    public void joinTroop(){
        heroFacade.joinTroop(hero.getId(), troop.getId());
        then(heroService).should().joinTroop(hero.getId(), troop.getId());
    }

    @Test
    public void leaveTroop(){
        heroFacade.leaveTroop(hero.getId());
        then(heroService).should().leaveTroop(hero.getId());
    }
}
