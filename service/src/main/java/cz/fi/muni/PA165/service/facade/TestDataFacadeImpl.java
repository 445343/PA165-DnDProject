package cz.fi.muni.PA165.service.facade;

import cz.fi.muni.PA165.api.facade.TestDataFacade;
import cz.fi.muni.PA165.service.TestDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TestDataFacadeImpl implements TestDataFacade {

    private TestDataService testDataService;

    @Autowired
    public TestDataFacadeImpl(TestDataService testDataService) {
        this.testDataService = testDataService;
    }

    @Override
    public void createTestData() {
        testDataService.createTestData();
    }
}
