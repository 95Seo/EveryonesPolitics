package com.hustar.edu.vote.mapper;

import com.hustar.edu.vote.dto.BoardDTO;
import com.hustar.edu.vote.dto.PromiseDTO;
import com.hustar.edu.vote.paging.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PromiseMapper {
    void insertPromise(PromiseDTO promiseDTO) ;

    public List<PromiseDTO> selectPromiseList(Criteria cri);

    public PromiseDTO selectPromiseDetail(int idx);

    public int selectPromiseTotalCount(Criteria cri);

    public void updatePromiseDetail(PromiseDTO promiseDTO);

    public void deletePromiseDetail(PromiseDTO promiseDTO);
}
