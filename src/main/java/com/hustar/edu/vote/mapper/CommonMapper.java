package com.hustar.edu.vote.mapper;

import com.hustar.edu.vote.dto.LikeDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommonMapper {
    public void viewCountUp(String table_nm, Long idx);
    public LikeDTO getLike(LikeDTO likeDTO);
    public void updateLike(LikeDTO likeDTO);
    public void insertLike(LikeDTO likeDTO);
    public int getLikeCount(LikeDTO likeDTO);
}
