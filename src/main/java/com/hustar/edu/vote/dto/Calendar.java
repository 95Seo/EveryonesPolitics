package com.hustar.edu.vote.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class Calendar {
    private int id;
    private String groupname;
    private String title;
    private String location;
    private String description;
    private String url;
    private String start;
    private String end;
    private boolean allday;
    private String backgroundColor;
    private String borderColor;
}
