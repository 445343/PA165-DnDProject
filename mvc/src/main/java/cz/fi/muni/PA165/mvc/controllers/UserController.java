package cz.fi.muni.PA165.mvc.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "user")
public class UserController {
    private final static Logger log = LoggerFactory.getLogger(UserController.class);

    /**
     * Show user
     */

    @GetMapping(value = "/{id}}")
    public String userGet(@PathVariable String id) {
        log.debug("loginGet()");

        /*
          TODO: get user with provided id
          TODO: and inject him to view
         */

        return "user/show";
    }

}
