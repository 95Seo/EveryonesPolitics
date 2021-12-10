package com.hustar.edu.vote.service;

import com.hustar.edu.vote.dto.BoardDTO;
import com.hustar.edu.vote.mapper.BoardMapper;
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
    public void insertPromiseBoard(BoardDTO board) {
        promiseMapper.insertPromiseBoard(board);
    }

    @Override
    public List<BoardDTO> getPromiseBoardList(Criteria criteria) {
        List<BoardDTO> boardPromiseList = Collections.emptyList();

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
    public BoardDTO getPromiseBoardDetail(BoardDTO board) {
        log.info("get promiseDetail");

        return promiseMapper.selectPromiseBoardDetail(board);
    }
}