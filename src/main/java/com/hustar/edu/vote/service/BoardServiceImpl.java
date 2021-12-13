package com.hustar.edu.vote.service;

import com.hustar.edu.vote.dto.BoardDTO;
import com.hustar.edu.vote.mapper.BoardMapper;
import com.hustar.edu.vote.paging.Criteria;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    BoardMapper boardMapper;

    @Override
    public void insertBoard(BoardDTO boardDTO) {
        boardMapper.insertBoard(boardDTO);
    }

    @Override
    public List<BoardDTO> selectBoardList(Criteria cri) {
        List<BoardDTO> boardList = Collections.emptyList();

        int boardTotalCount = boardMapper.selectBoardTotalCount(cri);

        if (boardTotalCount > 0) {
            boardList = boardMapper.selectBoardList(cri);
        }

        return boardList;
    }

    public BoardDTO selectBoardDetail(int idx) {
        return boardMapper.selectBoardDetail(idx);
    }

    public void updateBoardDetail(BoardDTO boardDTO) {
        Date date = new Date();

        java.sql.Timestamp sqlDate = new java.sql.Timestamp(date.getTime());

        boardDTO.setSysmoddate(sqlDate);

        boardMapper.updateBoardDetail(boardDTO);
    }

    @Override
    public int getTotal(Criteria cri) {
        log.info("get total count");
        return boardMapper.selectBoardTotalCount(cri);
    }
}
