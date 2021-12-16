package com.hustar.edu.vote.service;

import com.hustar.edu.vote.dto.tb_user;

import java.util.List;

public interface VoteService {
    public int getVotingCount();
    public void getupdateVoting(tb_user user);
    public int getselectChartView(int vote, String age_range);
}
