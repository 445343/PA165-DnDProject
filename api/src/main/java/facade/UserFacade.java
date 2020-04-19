package facade;

import dto.user.UserDTO;
import dto.user.UserCreateDTO;
import dto.user.UserUpdateDTO;

import java.util.List;

/**
 * Interface for User facade
 * @author Boris Jadus
 */
public interface UserFacade {

    /**
     * Find user by id
     * @param id of user
     * @return user with corresponding id
     */
    UserDTO findById(Long id);

    /**
     * Create new user
     * @param user to be created
     */
    void createUser(UserCreateDTO user);

    /**
     * Delete user with given id
     * @param id of user to be deleted
     */
    void deleteUser(Long id);

    /**
     * Update user
     * @param user to be updated
     */
    void updateUser(UserUpdateDTO user);

    /**
     * Find all users
     * @return all users
     */
    List<UserDTO> findAllUsers();

    /**
     * Adds hero to user
     * @param userId - id of the user
     * @param heroId - id of the hero
     */
    void addHeroToUser(Long userId, Long heroId);

    /**
     * Removes hero to user
     * @param userId - id of the user
     * @param heroId - id of the hero
     */
    void removeHeroFromUser(Long userId, Long heroId);
}
