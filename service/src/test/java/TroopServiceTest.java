import cz.fi.muni.PA165.api.exceptions.DnDServiceException;
import cz.fi.muni.PA165.persistence.dao.TroopDao;
import cz.fi.muni.PA165.persistence.model.Troop;
import cz.fi.muni.PA165.service.TroopService;
import cz.fi.muni.PA165.service.TroopServiceImpl;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Tests for TroopService
 *
 * @author Michal Caniga
 **/

public class TroopServiceTest {

    private TroopService troopService;

    @Mock
    private TroopDao troopDao;

    private Troop troop1;

    @BeforeMethod
    public void init() {
        MockitoAnnotations.initMocks(this);
        troopService = new TroopServiceImpl(troopDao);

        troop1 = new Troop();
        troop1.setId(1L);
        troop1.setName("troop 1");
        troop1.setMission("troop1 mission");
        troop1.setGold(10);
    }

    @Test
    public void findById() {
        given(troopDao.findById(troop1.getId())).willReturn(troop1);
        Troop troop = troopService.findById(troop1.getId());
        assertEquals(troop, troop1);
        then(troopDao).should().findById(troop1.getId());
    }

    @Test(expectedExceptions = DnDServiceException.class)
    public void findByIdNonExisting() {
        given(troopDao.findById(anyLong())).willReturn(null);
        troopService.findById(100L);
    }

    @Test
    public void createTroop() {
        troopService.createTroop(troop1);
        then(troopDao).should().create(troop1);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void createTroopFromNull() {
        willThrow(new NullPointerException()).given(troopDao).create(null);
        troopService.createTroop(null);
    }

    @Test
    public void deleteTroop() {
        given(troopDao.findById(troop1.getId())).willReturn(troop1);
        troopService.deleteTroop(troop1.getId());
        then(troopDao).should().delete(troop1);
    }

    @Test
    public void updateTroop() {
        troopService.updateTroop(troop1);
        then(troopDao).should().update(troop1);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void updateTroopWithNull() {
        willThrow(new NullPointerException()).given(troopDao).update(null);
        troopService.updateTroop(null);
    }

    @Test
    public void findAllTroops() {
        Troop troop2 = new Troop();
        troop2.setId(2L);
        troop2.setName("troop 2");
        troop2.setMission("troop2 mission");

        given(troopDao.findAll()).willReturn(List.of(troop1, troop2));
        List<Troop> troops = troopService.findAllTroops();
        assertEquals(troops.size(), 2);
        assertTrue(troops.contains(troop1));
        assertTrue(troops.contains(troop2));
        then(troopDao).should().findAll();
    }
}
