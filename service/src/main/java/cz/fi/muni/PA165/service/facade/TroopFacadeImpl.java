package cz.fi.muni.PA165.service.facade;

import cz.fi.muni.PA165.api.dto.troop.TroopCreateDTO;
import cz.fi.muni.PA165.api.dto.troop.TroopDTO;
import cz.fi.muni.PA165.api.dto.troop.TroopUpdateDTO;
import cz.fi.muni.PA165.api.facade.TroopFacade;
import cz.fi.muni.PA165.persistence.model.Troop;
import cz.fi.muni.PA165.service.TroopService;
import cz.fi.muni.PA165.service.mapping.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Implementation of TroopFacade Interface
 *
 * @author Alena Bednarikova
 */

@Transactional
@ComponentScan(basePackages = "cz.fi.muni.PA165.service.mapping")
public class TroopFacadeImpl implements TroopFacade {
    private TroopService troopService;
    private BeanMapper beanMapper;

    @Autowired
    public TroopFacadeImpl(TroopService troopService, BeanMapper beanMapper) {
        this.troopService = troopService;
        this.beanMapper = beanMapper;
    }

    @Override
    public TroopDTO findById(Long id) {
        Troop troop = troopService.findById(id);
        return beanMapper.mapTo(troop, TroopDTO.class);
    }

    @Override
    public void createTroop(TroopCreateDTO troop) {
        Troop troopToCreate = beanMapper.mapTo(troop, Troop.class);
        troopService.createTroop(troopToCreate);
    }

    @Override
    public void deleteTroop(Long id) {
        troopService.deleteTroop(id);
    }

    @Override
    public void disbandTroop(Long id) { troopService.disbandTroop(id); }

    @Override
    public void updateTroop(TroopUpdateDTO troop) {
        Troop troopToUpdate = beanMapper.mapTo(troop, Troop.class);
        troopService.updateTroop(troopToUpdate);
    }

    @Override
    public List<TroopDTO> findAllTroops() {
        List<Troop> troops = troopService.findAllTroops();
        return beanMapper.mapTo(troops, TroopDTO.class);
    }
}
