package cz.fi.muni.PA165.persistence.dao;

import cz.fi.muni.PA165.persistence.model.User;

import java.util.List;

/**
 * DAO for DnD User
 *
 * @author Michal Caniga
 */

public interface UserDao {
    /**
     * Finds user by given ID
     *
     * @param id id of user
     * @return user with given id
     */
    User findById(Long id);

    /**
     * Create user in DB
     *
     * @param user user to create
     */
    void create(User user);

    /**
     * Deletes user from DB
     *
     * @param user user to delete
     */
    void delete(User user);

    /**
     * Finds all stored users
     *
     * @return List of all users
     */
    List<User> findAll();

    /**
     * Updates user in DB
     *
     * @param user user to update
     */
    void update(User user);

    /**
     * Finds user with given name
     * @param name of wanted user
     * @return user with given name
     */
    User findByName(String name);
}
