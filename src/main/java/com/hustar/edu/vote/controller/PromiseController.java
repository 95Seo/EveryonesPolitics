package com.hustar.edu.vote.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class PromiseController {
    @GetMapping("/vote/promiseCreate")
    public String promiseCreate() {
        log.info("promisePage");
        return "vote/promise/promiseCreate";
    }
}
