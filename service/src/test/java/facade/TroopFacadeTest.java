package facade;

import cz.fi.muni.PA165.api.dto.troop.TroopCreateDTO;
import cz.fi.muni.PA165.api.dto.troop.TroopDTO;
import cz.fi.muni.PA165.api.dto.troop.TroopUpdateDTO;
import cz.fi.muni.PA165.api.facade.TroopFacade;
import cz.fi.muni.PA165.persistence.model.Troop;
import cz.fi.muni.PA165.service.TroopService;
import cz.fi.muni.PA165.service.facade.TroopFacadeImpl;
import cz.fi.muni.PA165.service.mapping.BeanMapper;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.testng.Assert.assertEquals;

/**
 * Tests for TroopFacade
 *
 * @author Michal Caniga
 **/

public class TroopFacadeTest {

    private TroopFacade troopFacade;

    @Mock
    private TroopService troopService;
    @Mock
    private BeanMapper beanMapper;

    private Troop troop;
    private TroopDTO troopDTO;

    @BeforeMethod
    public void init() {
        MockitoAnnotations.initMocks(this);
        troopFacade = new TroopFacadeImpl(troopService, beanMapper);
        troop = new Troop();
        troop.setId(1L);
        troop.setName("troop");
        troop.setMission("troop mission");

        troopDTO = new TroopDTO();
        troopDTO.setId(troop.getId());
        troopDTO.setName(troop.getName());
        troopDTO.setMission(troop.getMission());
        troopDTO.setGold(troop.getGold());
    }

    @Test
    public void findById() {
        given(troopService.findById(troop.getId())).willReturn(troop);
        given(beanMapper.mapTo(troop, TroopDTO.class)).willReturn(troopDTO);
        TroopDTO troopById = troopFacade.findById(troop.getId());
        assertEquals(troopDTO, troopById);
        then(troopService).should().findById(troop.getId());
    }

    @Test
    public void createTroop() {
        given(beanMapper.mapTo(any(TroopCreateDTO.class), eq(Troop.class))).willReturn(troop);
        troopFacade.createTroop(new TroopCreateDTO());
        then(troopService).should().createTroop(troop);
    }

    @Test
    public void deleteTroop() {
        troopFacade.deleteTroop(20L);
        then(troopService).should().deleteTroop(20L);
    }

    @Test
    public void updateTroop() {
        given(beanMapper.mapTo(any(TroopUpdateDTO.class), eq(Troop.class))).willReturn(troop);
        troopFacade.updateTroop(new TroopUpdateDTO());
        then(troopService).should().updateTroop(troop);
    }

    @Test
    public void findAllTroops() {
        Troop troop2 = new Troop();
        troop2.setId(2L);
        troop2.setName("troop2");
        troop2.setMission("troop2 mission");

        given(troopService.findAllTroops()).willReturn(List.of(troop, troop2));
        troopFacade.findAllTroops();
        then(troopService).should().findAllTroops();
        then(beanMapper).should().mapTo(List.of(troop, troop2), TroopDTO.class);
    }
}
