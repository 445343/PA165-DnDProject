package cz.fi.muni.PA165.rest.controllers;

import cz.fi.muni.PA165.api.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserFacade userFacade;

    @Autowired
    public UserController(UserFacade userFacade){
        this.userFacade = userFacade;
    }

    

}
