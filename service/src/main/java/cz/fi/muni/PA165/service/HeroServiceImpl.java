package cz.fi.muni.PA165.service;

import cz.fi.muni.PA165.persistence.dao.HeroDao;
import cz.fi.muni.PA165.persistence.model.Hero;
import cz.fi.muni.PA165.api.exceptions.DnDServiceException;
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

    @Autowired
    public HeroServiceImpl(HeroDao heroDao) {
        this.heroDao = heroDao;
    }

    @Override
    public Hero findById(Long id) {
        Hero hero = heroDao.findById(id);
        if (hero == null)
            throw new DnDServiceException("Hero with " + id + "not found");
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
}