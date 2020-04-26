package facade;

import cz.fi.muni.PA165.api.dto.user.UserCreateDTO;
import cz.fi.muni.PA165.api.dto.user.UserDTO;
import cz.fi.muni.PA165.api.dto.user.UserUpdateDTO;
import cz.fi.muni.PA165.api.facade.UserFacade;
import cz.fi.muni.PA165.persistence.model.Hero;
import cz.fi.muni.PA165.persistence.model.User;
import cz.fi.muni.PA165.service.UserService;
import cz.fi.muni.PA165.service.facade.UserFacadeImpl;
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
 * Tests for User facade
 *
 * @author Alena Bednarikova
 */
public class UserFacadeTest {
    private UserFacade userFacade;
    @Mock
    private UserService userService;
    @Mock
    private BeanMapper beanMapper;

    private User user;
    private UserDTO userDTO;
    private UserCreateDTO userCreateDTO;
    private UserUpdateDTO userUpdateDTO;
    private Hero hero;

    @BeforeMethod
    public void init(){

        MockitoAnnotations.initMocks(this);
        userFacade = new UserFacadeImpl(userService,beanMapper);

        user = new User();
        user.setId(1L);
        user.setUserName("UserName");
        user.setPasswordHash("fdslfkjsdgjfj565sdsdf");
        user.setAdmin(true);

        userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUserName(user.getUserName());
        userDTO.setAdmin(user.isAdmin());

        userCreateDTO = new UserCreateDTO();
        userCreateDTO.setPassword(user.getPasswordHash());
        userCreateDTO.setUserName(user.getUserName());
        userCreateDTO.setAdmin(user.isAdmin());

        userUpdateDTO = new UserUpdateDTO();
        userUpdateDTO.setId(user.getId());
        userUpdateDTO.setAdmin(user.isAdmin());
        userUpdateDTO.setUserName(user.getUserName());

        hero = new Hero();
        hero.setId(20L);
        hero.setName("Hero Name");
        hero.setLevel(1);
    }

    @Test
    public void findById(){
        given(userService.findById(user.getId())).willReturn(user);
        given(beanMapper.mapTo(user, UserDTO.class)).willReturn(userDTO);
        UserDTO resultUserDTO = userFacade.findById(user.getId());
        assertEquals(userDTO, resultUserDTO);
        then(userService).should().findById(user.getId());
    }

    @Test
    public void createUser(){
        given(beanMapper.mapTo(userCreateDTO, User.class)).willReturn(user);
        userFacade.createUser(userCreateDTO);
        then(userService).should().createUser(user, user.getPasswordHash());
    }

    @Test
    public void deleteUser(){
        userFacade.deleteUser(user.getId());
        then(userService).should().deleteUser(user.getId());
    }

    @Test
    public void updateUser(){
        given(beanMapper.mapTo(userUpdateDTO, User.class)).willReturn(user);
        userFacade.updateUser(userUpdateDTO);
        then(userService).should().updateUser(user);
    }

    @Test
    public void findAllUsers(){
        User user2 = new User();
        user2.setId(2L);
        user2.setUserName("User2");
        user2.setPasswordHash("jdskfjkdjfijsd0");
        user2.setAdmin(false);

        given(userService.findAllUsers()).willReturn(List.of(user, user2));
        userFacade.findAllUsers();
        then(userService).should().findAllUsers();
        then(beanMapper).should().mapTo(List.of(user, user2), UserDTO.class);
    }

    @Test
    public void addHeroToUser(){
        userFacade.addHeroToUser(user.getId(), hero.getId());
        then(userService).should().addHeroToUser(user.getId(), hero.getId());
    }

    @Test
    public void removeHeroFromUser(){
        userFacade.removeHeroFromUser(user.getId(), hero.getId());
        then(userService).should().removeHeroFromUser(user.getId(), hero.getId());
    }
}
