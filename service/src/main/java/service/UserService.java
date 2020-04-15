package service;

import cz.fi.muni.PA165.persistence.model.User;

import java.util.List;

/**
 * Service for User
 * @author Boris Jadus
 */
public interface UserService {
    /**
     * Find user by id
     * @param id of user
     * @return user with corresponding id
     */
    User findById(Long id);

    /**
     * Create new user
     * @param user to be created
     */
    void createUser(User user);

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
}
