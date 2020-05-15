package cz.fi.muni.PA165.rest.controllers;

import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/hello")
public class TestController {

    @GetMapping
    public String helloWorld(){
        return "works";
    }

}
