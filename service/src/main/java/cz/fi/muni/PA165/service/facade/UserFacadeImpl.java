package cz.fi.muni.PA165.service.facade;

import cz.fi.muni.PA165.api.facade.UserFacade;
import cz.fi.muni.PA165.persistence.model.User;
import cz.fi.muni.PA165.api.dto.user.UserCreateDTO;
import cz.fi.muni.PA165.api.dto.user.UserDTO;
import cz.fi.muni.PA165.api.dto.user.UserUpdateDTO;
import cz.fi.muni.PA165.service.mapping.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import cz.fi.muni.PA165.service.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Implementation of UserFacade interface
 *
 * @author Boris Jadus
 */
@Service
@Transactional
public class UserFacadeImpl implements UserFacade {

    private UserService userService;
    private BeanMapper beanMapper;

    @Autowired
    public UserFacadeImpl(UserService userService, BeanMapper beanMapper) {
        this.userService = userService;
        this.beanMapper = beanMapper;
    }

    @Override
    public UserDTO findById(Long id) {
        User user = userService.findById(id);
        return beanMapper.mapTo(user, UserDTO.class);
    }

    @Override
    public void createUser(UserCreateDTO user) {
        User userForCreation = beanMapper.mapTo(user, User.class);
        userService.createUser(userForCreation, user.getPassword());
    }

    @Override
    public void deleteUser(Long id) {
        userService.deleteUser(id);
    }

    @Override
    public void updateUser(UserUpdateDTO user) {
        User userForUpdate = beanMapper.mapTo(user, User.class);
        userService.updateUser(userForUpdate);
    }

    @Override
    public List<UserDTO> findAllUsers() {
        List<User> users = userService.findAllUsers();
        return beanMapper.mapTo(users, UserDTO.class);
    }

    @Override
    public void addHeroToUser(Long userId, Long heroId) {
        userService.addHeroToUser(userId, heroId);
    }

    @Override
    public void removeHeroFromUser(Long userId, Long heroId) {
        userService.removeHeroFromUser(userId, heroId);
    }
}
