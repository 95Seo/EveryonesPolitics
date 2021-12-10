package com.hustar.edu.vote.dto;

import lombok.Data;
import java.sql.Date;

@Data
public class BoardDTO {

    private int idx;
    private int writer_idx;
    private String title;
    private String content;
    private int view_cnt;
    private int file_id;
    private String top_fix;
    private String use_yn;
    private Date sysregdate;
    private Date sysmoddate;
    private String total_cd;
    private String dashboard_cd;
}
