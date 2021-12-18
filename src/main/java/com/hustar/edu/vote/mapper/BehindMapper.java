package com.hustar.edu.vote.mapper;

import com.hustar.edu.vote.dto.BehindDTO;
import com.hustar.edu.vote.dto.BoardDTO;
import com.hustar.edu.vote.paging.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BehindMapper {
    void insertBehind(BehindDTO behindDTO) ;

    public List<BehindDTO> selectBehindList(Criteria cri);

    public BehindDTO selectBehindDetail(int idx);

    public int selectBehindTotalCount(Criteria cri);

    public void updateBehindDetail(BehindDTO behindDTO);

    public void deleteBehindDetail(BehindDTO behindDTO);
}
