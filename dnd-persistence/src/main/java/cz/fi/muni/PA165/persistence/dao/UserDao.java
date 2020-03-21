package cz.fi.muni.PA165.persistence.dao;

import cz.fi.muni.PA165.persistence.model.User;

import java.util.List;

/**
 * DAO for DnD User
 *
 * @author Michal Caniga
 */

public interface UserDao {
    User findById(Long id);

    void create(User user);

    void delete(User user);

    List<User> findAll();

    void update(User user);
}
