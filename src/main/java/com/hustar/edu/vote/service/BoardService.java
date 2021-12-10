package com.hustar.edu.vote.service;

import com.hustar.edu.vote.dto.BoardDTO;
import com.hustar.edu.vote.paging.Criteria;

import java.util.List;

public interface BoardService {
    public void insertBoard(BoardDTO boardDTO);
    public List<BoardDTO> getBoardList(Criteria criteria);
    //추가(전체 글 개수)
    public int getTotal(Criteria cri);
}
