package cz.fi.muni.PA165.mvc.controllers;

import cz.fi.muni.PA165.api.dto.user.UserCreateDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "auth")
public class AuthController {
    private final static Logger log = LoggerFactory.getLogger(AuthController.class);

    /**
     * Show Login form
     */

    @GetMapping(value = "/login")
    public String loginGet() {
        log.debug("loginGet()");
        return "auth/login";
    }

    /**
     * Show Register form
     */

    @GetMapping(value = "/register")
    public String registerGet(Model model) {
        log.debug("registerGet()");
        UserCreateDTO user = new UserCreateDTO();
        model.addAttribute("userCreate", user);
        return "auth/register";
    }

    /**
     * Handle Registration
     */

    @PostMapping(value = "/register")
    public String registerPost(Model model) {
        log.debug("registerPost()");
        // TODO: Register user
        // TODO: Return his id
        // TODO: Delegate request to UserController/userGet
        return "redirect: user/{id}"; // replace {id} with gained user id
    }

}
