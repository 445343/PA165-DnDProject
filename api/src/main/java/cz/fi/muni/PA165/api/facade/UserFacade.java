package cz.fi.muni.PA165.api.facade;

import cz.fi.muni.PA165.api.dto.user.UserDTO;
import cz.fi.muni.PA165.api.dto.user.UserCreateDTO;
import cz.fi.muni.PA165.api.dto.user.UserUpdateDTO;

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

    /**
     * Logout current user
     */
    void logout();

    /**
     * login user
     * @param name of user
     * @param password of user
     * @return logged in user
     */
    UserDTO login(String name, String password);

    /**
     * Get currently logged in user
     * @return logged in user
     */
    UserDTO getCurrentUser();
}
