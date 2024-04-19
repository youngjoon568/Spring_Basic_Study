package com.example.spring.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Log4j2
@RequestMapping("/todo")

public class TodoController {

    @RequestMapping("/list")
    public void list() {
        log.info("list");
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public void signupGet() {
        log.info("signup get");

    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public void signupPost() {
        log.info("signup post");
    }
}
