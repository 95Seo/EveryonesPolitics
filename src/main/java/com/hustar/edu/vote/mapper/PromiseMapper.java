package com.hustar.edu.vote.mapper;

import com.hustar.edu.vote.dto.BoardDTO;
import com.hustar.edu.vote.dto.PromiseDTO;
import com.hustar.edu.vote.paging.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PromiseMapper {
    public void insertPromiseBoard(PromiseDTO promiseDTO);

    public List<PromiseDTO> selectPromiseBoardList(Criteria criteria);

    public int selectPromiseBoardTotalCount(Criteria criteria);

    public PromiseDTO selectPromiseBoardDetail(PromiseDTO promiseDTO);
}
