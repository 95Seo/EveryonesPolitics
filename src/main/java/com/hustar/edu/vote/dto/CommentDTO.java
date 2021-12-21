package com.hustar.edu.vote.dto;

import lombok.Data;

import java.sql.Timestamp;
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
    private Timestamp sysregdate;
    private Timestamp sysmoddate;
    private String useYn;
    private String realDelete;
    private int likeCnt;
    private int grpsCnt;

    private int commentCnt;
    private String likeYn;
    private String profileImg;
    private String nickname;
}
