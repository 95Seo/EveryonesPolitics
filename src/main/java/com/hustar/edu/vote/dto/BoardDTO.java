package com.hustar.edu.vote.dto;

import lombok.Data;
import java.sql.Date;

@Data
public class BoardDTO {
    private int idx;
    private String nickName;
    private int writerIdx;
    private String title;
    private String content;
    private int fileId;
    private String topFix;
    private String useYn;
    private Date sysregdate;
    private Date sysmoddate;

    private String totalCd;
    private int viewCnt;
    private int likeCnt;
    private int dislikeCnt;
    private int commentCnt;

    // 리스트, 디테일
    private int rownum;
}