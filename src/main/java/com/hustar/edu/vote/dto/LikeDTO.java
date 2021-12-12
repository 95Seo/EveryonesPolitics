package com.hustar.edu.vote.dto;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;


@Data
public class LikeDTO {
    private int idx;
    private int writerIdx;
    private String boardNm;
    private int boardIdx;
    private Date sysregdate;
    private Timestamp sysmoddate;
    private String likeYn;

    private int likeCnt;
}
