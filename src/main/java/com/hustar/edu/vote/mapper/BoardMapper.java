package com.hustar.edu.vote.mapper;

import com.hustar.edu.vote.dto.BoardDTO;
import com.hustar.edu.vote.paging.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {
    void insertBoard(BoardDTO boardDTO) ;

    public List<BoardDTO> selectBoardList(@Param("start")int start, @Param("end")int end, @Param("fill")int fill);

    public BoardDTO selectBoardDetail(int idx);

    public int selectBoardTotalCount(int fill);

    public void updateBoardDetail(BoardDTO boardDTO);
}
