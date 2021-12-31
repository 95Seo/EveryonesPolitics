package com.hustar.edu.vote.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SearchController {

    @GetMapping("/robots.txt")
    public String robots() {return "User-agent: Yeti\nAllow:/\n";}


}
