package cz.fi.muni.PA165.rest.controllers;

import cz.fi.muni.PA165.api.facade.TestDataFacade;
import org.springframework.web.bind.annotation.*;

/**
 * Testing rest controller
 * @author Boris Jadus
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rest/test")
public class TestController {

    private TestDataFacade testDataFacade;

    public TestController(TestDataFacade testDataFacade) {
        this.testDataFacade = testDataFacade;
    }

    @GetMapping
    public String helloWorld(){
        return "works";
    }

    /**
     * Creates test data and logs as admin
     */
    @PostMapping
    public void createTestData() {
        testDataFacade.createTestData();
    }
}
