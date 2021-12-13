package com.hustar.edu.vote.mapper;

import com.hustar.edu.vote.dto.BoardDTO;
import com.hustar.edu.vote.paging.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {
    void insertBoard(BoardDTO boardDTO) ;

    public List<BoardDTO> selectBoardList(Criteria cri);

    public BoardDTO selectBoardDetail(int idx);

    public int selectBoardTotalCount(Criteria cri);

    public void updateBoardDetail(BoardDTO boardDTO);
}
