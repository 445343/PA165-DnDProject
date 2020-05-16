package cz.fi.muni.PA165.service;

import cz.fi.muni.PA165.api.exceptions.DnDServiceException;
import cz.fi.muni.PA165.api.exceptions.ErrorStatus;
import cz.fi.muni.PA165.persistence.dao.HeroDao;
import cz.fi.muni.PA165.persistence.dao.RoleDao;
import cz.fi.muni.PA165.persistence.dao.TroopDao;
import cz.fi.muni.PA165.persistence.dao.UserDao;
import cz.fi.muni.PA165.persistence.model.Hero;
import cz.fi.muni.PA165.persistence.model.Role;
import cz.fi.muni.PA165.persistence.model.Troop;
import cz.fi.muni.PA165.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class TestDataService {

    private UserDao userDao;
    private TroopDao troopDao;
    private RoleDao roleDao;
    private HeroDao heroDao;

    @Autowired
    public TestDataService(UserDao userDao, TroopDao troopDao, RoleDao roleDao, HeroDao heroDao) {
        this.userDao = userDao;
        this.troopDao = troopDao;
        this.roleDao = roleDao;
        this.heroDao = heroDao;
    }

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
}
