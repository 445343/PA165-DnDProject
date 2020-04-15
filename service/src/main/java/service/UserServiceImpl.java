package service;

import cz.fi.muni.PA165.persistence.dao.UserDao;
import cz.fi.muni.PA165.persistence.model.User;
import exceptions.DnDServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of UserService interface
 * @author Boris Jadus
 */
@Service
public class UserServiceImpl implements UserService{

    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public User findById(Long id) {
        User user = userDao.findById(id);
        if (user == null)
            throw new DnDServiceException("User with " + id + "not found");
        return user;
    }

    @Override
    public void createUser(User user) {
        userDao.create(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = findById(id);
        userDao.delete(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.update(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userDao.findAll();
    }
}
