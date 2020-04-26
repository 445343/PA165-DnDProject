package cz.fi.muni.PA165.service.facade;

import cz.fi.muni.PA165.api.dto.hero.HeroCreateDTO;
import cz.fi.muni.PA165.api.dto.hero.HeroDTO;
import cz.fi.muni.PA165.api.dto.hero.HeroUpdateDTO;
import cz.fi.muni.PA165.api.facade.HeroFacade;
import cz.fi.muni.PA165.persistence.model.Hero;
import cz.fi.muni.PA165.service.HeroService;
import cz.fi.muni.PA165.service.mapping.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Implementation of HeroFacade interface
 *
 * @author Michal Caniga
 */

@Transactional
@ComponentScan(basePackages = "cz.fi.muni.PA165.service.mapping")
public class HeroFacadeImpl implements HeroFacade {

    private HeroService heroService;
    private BeanMapper beanMapper;

    @Autowired
    public HeroFacadeImpl(HeroService heroService, BeanMapper beanMapper) {
        this.heroService = heroService;
        this.beanMapper = beanMapper;
    }

    @Override
    public HeroDTO findById(Long id) {
        Hero hero = heroService.findById(id);
        return beanMapper.mapTo(hero, HeroDTO.class);
    }

    @Override
    public void createHero(HeroCreateDTO hero) {
        Hero heroForCreation = beanMapper.mapTo(hero, Hero.class);
        heroService.createHero(heroForCreation);
    }

    @Override
    public void deleteHero(Long id) {
        heroService.deleteHero(id);
    }

    @Override
    public void updateHero(HeroUpdateDTO hero) {
        Hero heroForUpdate = beanMapper.mapTo(hero, Hero.class);
        heroService.updateHero(heroForUpdate);
    }

    @Override
    public List<HeroDTO> findAllHeroes() {
        List<Hero> heroes = heroService.findAllHeroes();
        return beanMapper.mapTo(heroes, HeroDTO.class);
    }

    @Override
    public void addRole(Long heroId, Long roleId) {
        heroService.addRole(heroId, roleId);
    }

    @Override
    public void removeRole(Long heroId, Long roleId) {
        heroService.removeRole(heroId, roleId);
    }

    @Override
    public void joinTroop(Long heroId, Long troopId) {
        heroService.joinTroop(heroId, troopId);
    }

    @Override
    public void leaveTroop(Long heroId) {
        heroService.leaveTroop(heroId);
    }
}
