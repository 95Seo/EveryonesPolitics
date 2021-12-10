package com.hustar.edu.vote.service;

import com.hustar.edu.vote.dto.BoardDTO;
import com.hustar.edu.vote.paging.Criteria;

import java.util.List;

public interface PromiseService {
    public void insertPromiseBoard(BoardDTO boardDTO);
    public List<BoardDTO> getPromiseBoardList(Criteria criteria);
    //추가(전체 글 개수)
    public int getPromiseTotal(Criteria cri);

    public BoardDTO getPromiseBoardDetail(BoardDTO boardDTO);
}
