package com.hustar.edu.vote.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CommentDTO {

    private int cno;
    private int grp;
    private int grps;
    private int grpl;
    private String boardNm;
    private int bno;
    private int writer;
    private String content;
    private Date sysregdate;

    private String profileImg;
    private String nickname;
}
