package cz.fi.muni.PA165.service;

import cz.fi.muni.PA165.persistence.dao.HeroDao;
import cz.fi.muni.PA165.persistence.dao.RoleDao;
import cz.fi.muni.PA165.persistence.dao.TroopDao;
import cz.fi.muni.PA165.persistence.model.Hero;
import cz.fi.muni.PA165.api.exceptions.DnDServiceException;
import cz.fi.muni.PA165.persistence.model.Role;
import cz.fi.muni.PA165.persistence.model.Troop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    public HeroServiceImpl(HeroDao heroDao, RoleDao roleDao, TroopDao troopDao) {
        this.heroDao = heroDao;
        this.roleDao = roleDao;
        this.troopDao = troopDao;
    }

    @Override
    public Hero findById(Long id) {
        Hero hero = heroDao.findById(id);
        if (hero == null)
            throw new DnDServiceException("Hero with id: " + id + "not found");
        return hero;
    }

    @Override
    public void createHero(Hero hero) {
        heroDao.create(hero);
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
        return heroDao.findAll();
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
            throw new DnDServiceException("Hero is already part of another troop.");
        hero.joinTroop(troop);
    }

    @Override
    public void leaveTroop(Long heroId, Long troopId) {
        Hero hero = findById(heroId);
        Troop troop = findTroopById(troopId);
        hero.leaveTroop(troop);
    }

    private Role findRoleById(Long id) {
        Role role = roleDao.findById(id);
        if (role == null)
            throw new DnDServiceException("Role with id: " + id + "not found");
        return role;
    }

    private Troop findTroopById(Long id) {
        Troop troop = troopDao.findById(id);
        if (troop == null)
            throw new DnDServiceException("Troop with id: " + id + "not found");
        return troop;
    }
}