package de.community.forum.controller;

import de.community.forum.service.dto.HelloWorld;
import de.community.forum.service.dto.SecretHelloWorld;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// TODO Only for testing purpose, delete this class later
@RestController
public class HelloWorldController {

    @GetMapping("/hello-world")
    public HelloWorld getHelloWorld() {
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.setText("Hello");
        helloWorld.setName("World");
        return helloWorld;
    }

    @GetMapping("/secret-hello-world")
    public HelloWorld getSecretHelloWorld() {
        SecretHelloWorld greeting = new SecretHelloWorld();
        greeting.setText("Hello");
        greeting.setName("World");
        greeting.setSecretText("This is a secret message. Only for logged in users!");
        return greeting;
    }
}