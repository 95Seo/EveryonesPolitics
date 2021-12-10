package com.hustar.edu.vote.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.hustar.edu.vote.dto.Calendar;
import com.hustar.edu.vote.service.CalenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@Controller
@RequestMapping("calendar.do")
public class CalendarController {

    @Autowired(required = false)
    private CalenService service;

    @GetMapping(params = "method=list")
    public String list() {

        return "/vote/main";
    }

    @GetMapping(params = "method=data")
    @ResponseBody
    public String data(HttpServletResponse response) {
        ArrayList<Calendar> result = service.calenList();
        JsonArray arrObj = new JsonArray();
        result.forEach(it -> {
            JsonObject object = new JsonObject();
            object.addProperty("id", it.getId());
            object.addProperty("groupId", it.getGroupId());
            object.addProperty("title", it.getTitle());
            object.addProperty("writer", it.getWriter());
            object.addProperty("content", it.getContent());
            object.addProperty("start", it.getStart());
            object.addProperty("end" , it.getEnd());
            object.addProperty("allday" , it.isAllday());
            object.addProperty("backgroundColor" , it.getBackgroundColor());
            object.addProperty("borderColor" , it.getBorderColor());
            arrObj.add(object);
        });
        System.out.println(arrObj);

        return arrObj.toString();
    }
}
