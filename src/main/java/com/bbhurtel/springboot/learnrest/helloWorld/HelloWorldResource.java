package com.bbhurtel.springboot.learnrest.helloWorld;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldResource {

    @RequestMapping("/helloWorld")
    public String helloWorld() {
        return "Hello World!";
    }

    @RequestMapping("/helloWorldBean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World Bidur");
    }
    @RequestMapping("/helloWorldBeanPathParam/{name}")
    public HelloWorldBean helloWorldBeanPathParam(@PathVariable String name) {
        return new HelloWorldBean("Hello World, " + name);
    }
}
