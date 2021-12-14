package com.hustar.edu.vote.mapper;

import com.hustar.edu.vote.dto.tb_user;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VoteMapper {
    public int selectVoting();
}
