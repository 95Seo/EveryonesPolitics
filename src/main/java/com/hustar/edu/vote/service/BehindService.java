package com.hustar.edu.vote.service;

import com.hustar.edu.vote.dto.BehindDTO;
import com.hustar.edu.vote.paging.Criteria;

import java.util.HashMap;
import java.util.List;

public interface BehindService {
    public void insertBehind(BehindDTO boardDTO);
    public HashMap<String, Object> selectBehindList(Criteria cri);
    public BehindDTO selectBehindDetail(int idx);
    //추가(전체 글 개수)
    public int getTotal(Criteria cri);
    public void updateBehindDetail(BehindDTO boardDTO);
}
