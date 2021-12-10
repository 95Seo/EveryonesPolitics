package com.hustar.edu.vote.mapper;

import com.hustar.edu.vote.dto.BoardDTO;
import com.hustar.edu.vote.paging.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    void insertBoard(BoardDTO boardDTO) ;

    public List<BoardDTO> selectBoardList(int start, int end, int fill);

    public int selectBoardTotalCount(int fill);
}
