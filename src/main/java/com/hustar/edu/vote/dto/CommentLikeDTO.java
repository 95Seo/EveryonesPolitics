package com.hustar.edu.vote.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CommentLikeDTO {
    private int idx;
    private int writer;
    private String boardNm;
    private int bno;
    private int cno;
    private Timestamp sysregdate;
    private Timestamp sysmoddate;
    private String likeYn;
}
