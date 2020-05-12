package cz.fi.muni.PA165.service;

import cz.fi.muni.PA165.persistence.dao.HeroDao;
import cz.fi.muni.PA165.persistence.dao.UserDao;
import cz.fi.muni.PA165.persistence.model.Hero;
import cz.fi.muni.PA165.persistence.model.User;
import cz.fi.muni.PA165.api.exceptions.DnDServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
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
            throw new DnDServiceException("User with id: " + id + "not found");
        return user;
    }

    @Override
    public Long createUser(User user, String password){
        user.setPasswordHash(hashPassword(password));
        return userDao.create(user);
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
        if (user.isAdmin())
            throw new DnDServiceException("Admin can not be deleted.");
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
            throw new DnDServiceException("Hero with id: " + id + " not found");
        return hero;
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null)
            return null;
        return findByName(authentication.getName());
    }

    @Override
    public void logout() {
        SecurityContextHolder.clearContext();
    }

    @Override
    public User login(String name, String password) {
        User user = findByName(name);
        passwordCheck(user.getPasswordHash(), password);
        setSecurityContext(user.getUserName(), user.getPasswordHash(), user.isAdmin());
        return user;
    }

    private void setSecurityContext(String name, String password, boolean isAdmin){
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        if (isAdmin)
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        org.springframework.security.core.userdetails.User springUser
                = new org.springframework.security.core.userdetails.User(name, password, authorities);

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                springUser.getUsername(),
                springUser.getPassword(),
                springUser.getAuthorities()));
    }

    private void passwordCheck(String passwordHash, String password){
        String hash = hashPassword(password);
        if (!hash.equals(passwordHash))
            throw new DnDServiceException("Wrong password/login combination");
    }

    private User findByName(String name){
        User u = userDao.findByName(name);
        if (u == null)
            throw new DnDServiceException("User with name: "+ name +", not found");
        return u;
    }
}
