package com.hustar.edu.vote.controller;


import com.hustar.edu.vote.service.SiteMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

@RestController
public class SiteMapController {
    @Autowired
    private SiteMapService siteMapService;
    @RequestMapping(value="/sitemap.xml", produces= {"application/xml"})
    @ResponseBody
    public ResponseEntity<String> sitemap (HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        return ResponseEntity.ok(siteMapService.getSystemicSiteMap());
    }
}