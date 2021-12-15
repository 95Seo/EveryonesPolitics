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

    public List<BoardDTO> selectMyBoardList(@Param("start")int start, @Param("amount")int amount, @Param("idx")int idx);

    public int selectMyBoardTotalCount(int idx);
}
