package com.hustar.edu.vote.service;

import com.hustar.edu.vote.dto.PromiseDTO;
import com.hustar.edu.vote.paging.Criteria;

import java.util.HashMap;

public interface PromiseService {
    public void insertPromise(PromiseDTO promiseDTO);
    public HashMap<String, Object> selectPromiseList(Criteria cri);
    public PromiseDTO selectPromiseDetail(int idx);
    //추가(전체 글 개수)
    public int getTotal(Criteria cri);
    public void updatePromiseDetail(PromiseDTO promiseDTO);
    public void deletePromiseDetail(PromiseDTO promiseDTO);
}
