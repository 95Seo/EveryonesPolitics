package com.hustar.edu.vote.paging;

import lombok.Data;

@Data
public class Criteria {

    private int page;    // 페이지 번호
    private int amount;     // 한페이지 글 갯수

    private String filter;  // 필터
    private String keyword; // 키워드

    private int start;
    private int end;

    public Criteria() {
        this.page = 1;
        this.amount = 10;
        this.filter = "all";
    }
}
