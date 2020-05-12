package cz.fi.muni.PA165.service;

import cz.fi.muni.PA165.persistence.model.User;
import cz.fi.muni.PA165.api.exceptions.DnDServiceException;

import java.util.List;

/**
 * Service for User
 * @author Boris Jadus
 */
public interface UserService {
    /**
     * Find user by id
     * @param id of user
     * @throws DnDServiceException if user is not found.
     * @return user with corresponding id
     */
    User findById(Long id);

    /**
     * Create new user
     * @param user to be created
     * @param password for hashing
     * @throws DnDServiceException if hashing of password fails.
     */
    Long createUser(User user, String password);

    /**
     * Delete user with given id
     * @param id of user to be deleted
     */
    void deleteUser(Long id);

    /**
     * Update user
     * @param user to be updated
     */
    void updateUser(User user);

    /**
     * Find all users
     * @return all users
     */
    List<User> findAllUsers();

    /**
     * Adds hero to user
     * @param userId - id of the user
     * @param heroId - id of the hero
     * @throws DnDServiceException if user or hero is not found.
     */
    void addHeroToUser(Long userId, Long heroId);

    /**
     * Removes hero to user
     * @param userId - id of the user
     * @param heroId - id of the hero
     * @throws DnDServiceException if user or hero is not found.
     */
    void removeHeroFromUser(Long userId, Long heroId);

    /**
     * Returns logged in user
     * @return current user
     */
    User getCurrentUser();

    /**
     * Logout current user
     */
    void logout();

    /**
     *  Authenticates and logs in user
     * @param name - name of the user
     * @param password - password of the user
     * @return logged in user
     */
    User login(String name, String password);

}
