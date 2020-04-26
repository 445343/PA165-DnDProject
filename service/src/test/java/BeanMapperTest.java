import cz.fi.muni.PA165.api.dto.hero.HeroDTO;
import cz.fi.muni.PA165.api.dto.role.RoleDTO;
import cz.fi.muni.PA165.api.dto.troop.TroopDTO;
import cz.fi.muni.PA165.api.dto.user.UserDTO;
import cz.fi.muni.PA165.persistence.model.Hero;
import cz.fi.muni.PA165.persistence.model.Role;
import cz.fi.muni.PA165.persistence.model.Troop;
import cz.fi.muni.PA165.persistence.model.User;
import cz.fi.muni.PA165.service.mapping.BeanMapper;
import cz.fi.muni.PA165.service.mapping.BeanMapperImpl;
import org.modelmapper.ModelMapper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Test for bean mapper
 * @author Boris Jadus
 */
public class BeanMapperTest {

    private BeanMapper beanMapper;
    private Hero hero;
    private Troop troop;
    private Role role;
    private User user;

    @BeforeMethod
    public void init(){
        beanMapper = new BeanMapperImpl(new ModelMapper());
        role = new Role();
        role.setId(1L);
        role.setName("RoleName");
        role.setDescription("RoleDescription");

        troop = new Troop();
        troop.setId(2L);
        troop.setGold(100);
        troop.setMission("Mission");
        troop.setName("TroopName");

        hero = new Hero();
        hero.setId(3L);
        hero.setLevel(9);
        hero.setName("HeroName");
        hero.addRole(role);
        hero.joinTroop(troop);

        user = new User();
        user.setId(4L);
        user.setUserName("UserName");
        user.setPasswordHash("afdb5a241x2a");
        user.setAdmin(true);
        user.addHero(hero);
    }

    @Test
    public void testMapTo(){
        UserDTO userDTO = beanMapper.mapTo(user, UserDTO.class);

        assertEquals(userDTO.getId(), user.getId());
        assertEquals(userDTO.getUserName(), user.getUserName());
        assertEquals(userDTO.isAdmin(), user.isAdmin());

        HeroDTO heroDTO = (HeroDTO) userDTO.getHeroes().toArray()[0];
        assertEquals(heroDTO.getId(), hero.getId());
        assertEquals(heroDTO.getName(), hero.getName());
        assertEquals(heroDTO.getLevel(), hero.getLevel());

        RoleDTO roleDTO = (RoleDTO) heroDTO.getRoles().toArray()[0];
        assertEquals(roleDTO.getId(), role.getId());
        assertEquals(roleDTO.getDescription(), role.getDescription());
        assertEquals(roleDTO.getName(), role.getName());

        TroopDTO troopDTO = beanMapper.mapTo(troop, TroopDTO.class);
        assertEquals(troopDTO.getId(), troop.getId());
        assertEquals(troopDTO.getGold(), troop.getGold());
        assertEquals(troopDTO.getMission(), troop.getMission());
        assertEquals(troopDTO.getName(), troop.getName());
        HeroDTO heroFromTroop = (HeroDTO) troopDTO.getHeroes().toArray()[0];
        assertEquals(heroFromTroop, heroDTO);
    }
}

