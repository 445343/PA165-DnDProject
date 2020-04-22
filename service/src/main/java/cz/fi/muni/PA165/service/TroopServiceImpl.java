package cz.fi.muni.PA165.service;

import cz.fi.muni.PA165.api.exceptions.DnDServiceException;
import cz.fi.muni.PA165.persistence.dao.HeroDao;
import cz.fi.muni.PA165.persistence.dao.RoleDao;
import cz.fi.muni.PA165.persistence.dao.TroopDao;
import cz.fi.muni.PA165.persistence.model.Hero;
import cz.fi.muni.PA165.persistence.model.Troop;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TroopServiceImpl implements TroopService {

    private TroopDao troopDao;

    @Autowired
    public TroopServiceImpl(TroopDao troopDao) {
        this.troopDao = troopDao;
    }

    @Override
    public Troop findById(Long id) {
        Troop troop = troopDao.findById(id);
        if (troop == null)
            throw new DnDServiceException("Troop with " + id + "not found");
        return troop;
    }

    @Override
    public void createTroop(Troop troop) {
        troopDao.create(troop);
    }

    @Override
    public void deleteTroop(Long id) {
        Troop troop = troopDao.findById(id);
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
