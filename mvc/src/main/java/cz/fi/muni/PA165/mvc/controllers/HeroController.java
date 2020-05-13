package cz.fi.muni.PA165.mvc.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "hero")
public class HeroController {
    private final static Logger log = LoggerFactory.getLogger(HeroController.class);

}
