package cz.fi.muni.PA165.service;

import cz.fi.muni.PA165.api.exceptions.DnDServiceException;
import cz.fi.muni.PA165.api.exceptions.ErrorStatus;
import cz.fi.muni.PA165.persistence.dao.HeroDao;
import cz.fi.muni.PA165.persistence.dao.TroopDao;
import cz.fi.muni.PA165.persistence.model.Hero;
import cz.fi.muni.PA165.persistence.model.Troop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Implementation of TroopService Interface
 *
 * @author Alena Bednarikova
 */

@Service
public class TroopServiceImpl implements TroopService {

    private TroopDao troopDao;

    @Autowired
    public TroopServiceImpl(TroopDao troopDao) { this.troopDao = troopDao; }

    @Override
    public Troop findById(Long id) {
        Troop troop = troopDao.findById(id);
        if (troop == null)
            throw new DnDServiceException("Troop with id: " + id + "not found", ErrorStatus.RESOURCE_NOT_FOUND);
        return troop;
    }

    @Override
    public Long createTroop(Troop troop) {
        return troopDao.create(troop);
    }

    @Override
    public void disbandTroop(Long id) {
        Troop troop = findById(id);
        List<Hero> heroesToRemove = new ArrayList<>(troop.getHeroes());
        for (Hero h : heroesToRemove)
            h.leaveTroop();
        troopDao.delete(troop);
    }

    @Override
    public void updateTroop(Troop troop) {
        troopDao.update(troop);
    }

    @Override
    public List<Troop> findAllTroops() {
        return troopDao.findAll();
    }
}
