package com.hustar.edu.vote.service;


import com.hustar.edu.vote.dto.PromiseDTO;
import com.hustar.edu.vote.dto.Time;
import com.hustar.edu.vote.mapper.PromiseMapper;
import com.hustar.edu.vote.paging.Criteria;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;


@Slf4j
@Service
public class PromiseServiceImpl implements PromiseService{

    @Autowired
    PromiseMapper promiseMapper;

   @Override
    public void insertPromiseBoard(PromiseDTO promise) {
        promiseMapper.insertPromiseBoard(promise);
    }

    @Override
    public List<PromiseDTO> getPromiseBoardList(Criteria criteria) {
        List<PromiseDTO> boardPromiseList = Collections.emptyList();

        int boardTotalCount = promiseMapper.selectPromiseBoardTotalCount(criteria);

        if (boardTotalCount > 0) {
            boardPromiseList = promiseMapper.selectPromiseBoardList(criteria);
        }

        return boardPromiseList;
    }

    @Override
    public int getPromiseTotal(Criteria cri) {
        log.info("get promise total count");
        return promiseMapper.selectPromiseBoardTotalCount(cri);
    }

    @Override
    public PromiseDTO getPromiseBoardDetail(PromiseDTO promiseDTO) {
        log.info("get promiseDetail");

        return promiseMapper.selectPromiseBoardDetail(promiseDTO);
    }

}