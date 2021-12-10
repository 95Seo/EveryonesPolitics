package com.hustar.edu.vote.service;

import com.hustar.edu.vote.dto.BoardDTO;
import com.hustar.edu.vote.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PromiseService {

    @Autowired
    private BoardMapper boardMapper;

    @Transactional
    public void insertBoard(BoardDTO board) {
        boardMapper.insertBoard(board);
    }
}