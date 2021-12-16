package com.hustar.edu.vote.mapper;

import com.hustar.edu.vote.dto.tb_user;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VoteMapper {
    public int selectVotingCount();
    public void updateVoting(tb_user user);
    public List<Integer> selectVotingList();
    public int selectChartView(@Param("vote")int vote, @Param("age_range")String age_range);
}
