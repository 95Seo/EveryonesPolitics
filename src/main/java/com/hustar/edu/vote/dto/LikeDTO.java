package com.hustar.edu.vote.dto;

import lombok.Data;

import java.sql.Date;


@Data
public class LikeDTO {
    private int idx;
    private int writerIdx;
    private String boardNm;
    private int boardIdx;
    private Date sysregdate;
    private Date sysmoddate;
    private String likeYn;
}
