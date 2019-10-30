package com.ga.musicappboot.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RESTController {
    @GetMapping("/hello")
    public String testController(){
        return "Hello world!";
    }

}
