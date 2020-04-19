package facade;

import dto.user.UserCreateDTO;
import dto.user.UserDTO;
import dto.user.UserUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import service.UserService;

import java.util.List;

public class UserFacadeImpl implements UserFacade{

    private UserService userService;

    @Autowired
    public UserFacadeImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDTO findById(Long id) {
        return null;
    }

    @Override
    public void createUser(UserCreateDTO user) {

    }

    @Override
    public void deleteUser(Long id) {

    }

    @Override
    public void updateUser(UserUpdateDTO user) {

    }

    @Override
    public List<UserDTO> findAllUsers() {
        return null;
    }
}
