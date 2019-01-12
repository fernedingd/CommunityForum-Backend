package de.community.forum.controller;

import de.community.forum.service.dto.Greeting;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// TODO Only for testing purpose, delete this class later#
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class GreetingController {

    @GetMapping("/greeting")
    public Greeting getGreeting() {
        Greeting greeting = new Greeting();
        greeting.setText("Hello");
        greeting.setName("World");
        return greeting;
    }
}
