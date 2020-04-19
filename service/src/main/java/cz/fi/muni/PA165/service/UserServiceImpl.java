package cz.fi.muni.PA165.service;

import cz.fi.muni.PA165.persistence.dao.HeroDao;
import cz.fi.muni.PA165.persistence.dao.UserDao;
import cz.fi.muni.PA165.persistence.model.Hero;
import cz.fi.muni.PA165.persistence.model.User;
import cz.fi.muni.PA165.api.exceptions.DnDServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Implementation of UserService interface
 * @author Boris Jadus
 */
@Service
public class UserServiceImpl implements UserService{

    private UserDao userDao;
    private HeroDao heroDao;

    @Autowired
    public UserServiceImpl(UserDao userDao, HeroDao heroDao){
        this.userDao = userDao;
        this.heroDao = heroDao;
    }

    @Override
    public User findById(Long id) {
        User user = userDao.findById(id);
        if (user == null)
            throw new DnDServiceException("User with " + id + "not found");
        return user;
    }

    @Override
    public void createUser(User user, String password){
        user.setPasswordHash(hashPassword(password));
        userDao.create(user);
    }

    private String hashPassword(String password){
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02X ", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex){
            throw new DnDServiceException("Error while hashing password.");
        }
    }

    @Override
    public void deleteUser(Long id) {
        User user = findById(id);
        userDao.delete(user);
    }

    @Override
    public void updateUser(User user) {
        User originalUser = findById(user.getId());
        user.setHeroes(originalUser.getHeroes());
        userDao.update(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userDao.findAll();
    }

    @Override
    public void addHeroToUser(Long userId, Long heroId) {
        User user = findById(userId);
        Hero hero = findHeroById(heroId);
        user.addHero(hero);
    }

    @Override
    public void removeHeroFromUser(Long userId, Long heroId) {
        User user = findById(userId);
        Hero hero = findHeroById(heroId);
        user.removeHero(hero);
    }

    private Hero findHeroById(Long id){
        Hero hero = heroDao.findById(id);
        if (hero == null)
            throw new DnDServiceException("Hero with " + id + "not found");
        return hero;
    }

}
