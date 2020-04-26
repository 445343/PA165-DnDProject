package cz.fi.muni.PA165.service;

import cz.fi.muni.PA165.api.exceptions.DnDServiceException;
import cz.fi.muni.PA165.persistence.dao.TroopDao;
import cz.fi.muni.PA165.persistence.model.Troop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of TroopService Interface
 *
 * @author Alena Bednarikova
 */

@Service
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
            throw new DnDServiceException("Troop with id: " + id + "not found");
        return troop;
    }

    @Override
    public void createTroop(Troop troop) {
        troopDao.create(troop);
    }

    @Override
    public void deleteTroop(Long id) {
        Troop troop = findById(id);
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
