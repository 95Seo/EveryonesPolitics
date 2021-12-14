package com.hustar.edu.vote.dto;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
public class BehindDTO {
    private int idx;
    private String nickName;
    private int writerIdx;
    private String title;
    private String content;
    private String fileUrl;
    private String topFix;
    private String useYn;
    private Date sysregdate;
    private Timestamp sysmoddate;

    private String filter;
    private int viewCnt;
    private int likeCnt;
    private int dislikeCnt;
    private int commentCnt;

    // 리스트, 디테일
    private int rownum;
}