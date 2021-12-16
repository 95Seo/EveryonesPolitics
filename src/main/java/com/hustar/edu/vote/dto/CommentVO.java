package com.hustar.edu.vote.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CommentVO {

    private int cno;
    private int bno;
    private int writer;
    private String content;
    private Date reg_date;

    private String profileImg;
    private String nickname;
}
