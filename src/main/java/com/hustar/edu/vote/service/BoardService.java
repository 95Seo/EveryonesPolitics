package com.hustar.edu.vote.service;

import com.hustar.edu.vote.dto.BoardDTO;
import com.hustar.edu.vote.paging.Criteria;

import java.util.HashMap;
import java.util.List;

public interface BoardService {
    public void insertBoard(BoardDTO boardDTO);
    public HashMap<String, Object> selectBoardList(Criteria cri);
    public BoardDTO selectBoardDetail(int idx);
    //추가(전체 글 개수)
    public int getTotal(Criteria cri);
    public void updateBoardDetail(BoardDTO boardDTO);
    public HashMap<String, Object> selectMyBoardList(Criteria cri, int idx);
    public int selectMyBoardTotalCount(int idx);
    public List<BoardDTO> selectLikeBoardList();
    public void deleteBoardDetail(BoardDTO boardDTO);
}
