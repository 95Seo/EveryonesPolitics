package com.hustar.edu.vote.FileUpload;

import lombok.Data;

import java.sql.Date;

@Data
public class UploadFile {
    private int idx;

    private String file_nm;

    private String file_size;

    private String file_dir;

    private int sort;

    private int sysregidx;

    private Date sysregdate;
}
