package cz.fi.muni.PA165.service;

import cz.fi.muni.PA165.api.exceptions.ErrorStatus;
import cz.fi.muni.PA165.persistence.dao.HeroDao;
import cz.fi.muni.PA165.persistence.dao.RoleDao;
import cz.fi.muni.PA165.persistence.dao.TroopDao;
import cz.fi.muni.PA165.persistence.dao.UserDao;
import cz.fi.muni.PA165.persistence.model.Hero;
import cz.fi.muni.PA165.api.exceptions.DnDServiceException;
import cz.fi.muni.PA165.persistence.model.Role;
import cz.fi.muni.PA165.persistence.model.Troop;
import cz.fi.muni.PA165.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of HeroService interface
 *
 * @author Michal Caniga
 */
@Service
public class HeroServiceImpl implements HeroService {

    private HeroDao heroDao;
    private RoleDao roleDao;
    private TroopDao troopDao;
    private UserDao userDao;

    @Autowired
    public HeroServiceImpl(HeroDao heroDao, RoleDao roleDao, TroopDao troopDao, UserDao userDao) {
        this.heroDao = heroDao;
        this.roleDao = roleDao;
        this.troopDao = troopDao;
        this.userDao = userDao;
    }

    @Override
    public Hero findById(Long id) {
        Hero hero = heroDao.findById(id);
        if (hero == null)
            throw new DnDServiceException("Hero with id: " + id + "not found", ErrorStatus.RESOURCE_NOT_FOUND);
        return hero;
    }

    @Override
    public Long createHero(Hero hero) {
        return heroDao.create(hero);
    }

    @Override
    public void deleteHero(Long id) {
        Hero hero = this.findById(id);
        heroDao.delete(hero);
    }

    @Override
    public void updateHero(Hero hero) {
        heroDao.update(hero);
    }

    @Override
    public List<Hero> findAllHeroes() {
        User user = getCurrentUser();
        if (user.isAdmin())
            return heroDao.findAll();
        return new ArrayList<>(user.getHeroes());
    }

    @Override
    public void addRole(Long heroId, Long roleId) {
        Hero hero = findById(heroId);
        Role role = findRoleById(roleId);
        hero.addRole(role);
    }

    @Override
    public void removeRole(Long heroId, Long roleId) {
        Hero hero = findById(heroId);
        Role role = findRoleById(roleId);
        hero.removeRole(role);
    }

    @Override
    public void joinTroop(Long heroId, Long troopId) {
        Hero hero = findById(heroId);
        Troop troop = findTroopById(troopId);
        if (hero.getTroop() != null)
            throw new DnDServiceException("Hero is already part of another troop.", ErrorStatus.CONFLICT);
        hero.joinTroop(troop);
    }

    @Override
    public void leaveTroop(Long heroId) {
        Hero hero = findById(heroId);
        hero.leaveTroop();
    }

    private Role findRoleById(Long id) {
        Role role = roleDao.findById(id);
        if (role == null)
            throw new DnDServiceException("Role with id: " + id + "not found", ErrorStatus.RESOURCE_NOT_FOUND);
        return role;
    }

    private Troop findTroopById(Long id) {
        Troop troop = troopDao.findById(id);
        if (troop == null)
            throw new DnDServiceException("Troop with id: " + id + "not found", ErrorStatus.RESOURCE_NOT_FOUND);
        return troop;
    }

    private User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null)
            throw new DnDServiceException("You need to be logged in", ErrorStatus.NOT_LOGGED_IN);
        User user = userDao.findByName(authentication.getName());
        if (user == null)
            throw new DnDServiceException("User not found", ErrorStatus.RESOURCE_NOT_FOUND);
        return user;
    }

}