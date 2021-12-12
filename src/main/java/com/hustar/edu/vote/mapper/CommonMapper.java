package com.hustar.edu.vote.mapper;

import com.hustar.edu.vote.dto.LikeDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CommonMapper {
    public void viewCountUp(@Param("boardNm")String boardNm, @Param("idx")Long idx);
    public LikeDTO getLike(LikeDTO likeDTO);
    public void updateLike(LikeDTO likeDTO);
    public void insertLike(LikeDTO likeDTO);
    public int getLikeCount(LikeDTO likeDTO);
    public void updateLikeCount(LikeDTO likeDTO);
}
