package cz.fi.muni.PA165.rest.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class TestController {
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public String helloWorld(){
        return "works";
    }


}
