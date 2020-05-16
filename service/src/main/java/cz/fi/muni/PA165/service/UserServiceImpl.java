package cz.fi.muni.PA165.service;

import cz.fi.muni.PA165.api.exceptions.ErrorStatus;
import cz.fi.muni.PA165.persistence.dao.HeroDao;
import cz.fi.muni.PA165.persistence.dao.RoleDao;
import cz.fi.muni.PA165.persistence.dao.TroopDao;
import cz.fi.muni.PA165.persistence.dao.UserDao;
import cz.fi.muni.PA165.persistence.model.Hero;
import cz.fi.muni.PA165.persistence.model.Role;
import cz.fi.muni.PA165.persistence.model.Troop;
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
    private TroopDao troopDao;
    private RoleDao roleDao;

    @Autowired
    public UserServiceImpl(UserDao userDao, HeroDao heroDao, TroopDao troopDao, RoleDao roleDao){
        this.userDao = userDao;
        this.heroDao = heroDao;
        this.troopDao = troopDao;
        this.roleDao = roleDao;
    }

    @Override
    public User findById(Long id) {
        User user = userDao.findById(id);
        if (user == null)
            throw new DnDServiceException("User with id: " + id + "not found", ErrorStatus.RESOURCE_NOT_FOUND);
        return user;
    }

    @Override
    public Long createUser(User user, String password){
        user.setPasswordHash(hashPassword(password));
        Long newId = userDao.create(user);
        setSecurityContext(user.getUserName(), user.getPasswordHash(), user.isAdmin());
        return newId;
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
            throw new DnDServiceException("Error while hashing password.", ErrorStatus.INTERNAL);
        }
    }

    @Override
    public void deleteUser(Long id) {
        User user = findById(id);
        if (user.isAdmin())
            throw new DnDServiceException("Admin can not be deleted.", ErrorStatus.CONFLICT);
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
            throw new DnDServiceException("Hero with id: " + id + " not found", ErrorStatus.RESOURCE_NOT_FOUND);
        return hero;
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null)
            throw new DnDServiceException("You are not logged in", ErrorStatus.NOT_LOGGED_IN);
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

    @Override
    public boolean isAdmin(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null)
            return false;
        return findByName(authentication.getName()).isAdmin();
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
            throw new DnDServiceException("Wrong password/login combination", ErrorStatus.BAD_LOGIN);
    }

    private User findByName(String name){
        User u = userDao.findByName(name);
        if (u == null)
            throw new DnDServiceException("User with name: "+ name +", not found", ErrorStatus.RESOURCE_NOT_FOUND);
        return u;
    }

    @Override
    public void createTestData() {
        User admin = new User();
        admin.setAdmin(true);
        admin.setUserName("admin");
        admin.setPasswordHash(hashPassword("admin"));
        userDao.create(admin);

        User user = new User();
        user.setAdmin(false);
        user.setUserName("user1");
        user.setPasswordHash(hashPassword("user1"));
        userDao.create(user);

        User user2 = new User();
        user2.setAdmin(false);
        user2.setUserName("user2");
        user2.setPasswordHash(hashPassword("user2"));
        userDao.create(user2);

        Troop troop1 = new Troop();
        troop1.setName("Dragon slayers");
        troop1.setMission("Kill dragon residing at nearby mountain");
        troop1.setGold(500);
        troopDao.create(troop1);

        Troop troop2 = new Troop();
        troop2.setName("Demon hunters");
        troop2.setMission("Perform exorcism on possessed priest");
        troop2.setGold(666);
        troopDao.create(troop2);

        Hero hero1 = new Hero();
        hero1.setName("Igor the Elf");
        hero1.setLevel(9);
        heroDao.create(hero1);

        Hero hero2 = new Hero();
        hero2.setName("Ugly Berta");
        hero2.setLevel(2);
        heroDao.create(hero2);

        Hero hero3 = new Hero();
        hero3.setName("Stealing Tom");
        hero3.setLevel(3);
        heroDao.create(hero3);

        Hero hero4 = new Hero();
        hero4.setName("Dominik");
        hero4.setLevel(6);
        heroDao.create(hero4);

        Role role1 = new Role();
        role1.setName("Archer");
        role1.setDescription("Master of bow and arrow");
        roleDao.create(role1);

        Role role2 = new Role();
        role2.setName("Rogue");
        role2.setDescription("Master of shadows");
        roleDao.create(role2);

        Role role3 = new Role();
        role3.setName("Brute");
        role3.setDescription("Master of fist fights");
        roleDao.create(role3);

        Role role4 = new Role();
        role4.setName("Dominik");
        role4.setDescription("Useless human being");
        roleDao.create(role4);

        hero1.addRole(role1);
        hero2.addRole(role3);
        hero3.addRole(role2);
        hero4.addRole(role4);

        troop1.addHero(hero1);
        troop1.addHero(hero3);
        troop2.addHero(hero2);

        user.addHero(hero1);
        user.addHero(hero2);
        user2.addHero(hero4);
        user2.addHero(hero3);

        login(admin.getUserName(), "admin");
    }
}
