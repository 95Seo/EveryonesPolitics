package com.hustar.edu.vote.service;

import com.hustar.edu.vote.dto.tb_user;
import com.hustar.edu.vote.mapper.VoteMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class VoteServiceImpl implements VoteService {
    @Autowired
    VoteMapper voteMapper;

    @Override
    public int getVotingCount() {
        log.info("get selectVoingCount");
        return voteMapper.selectVotingCount();
    }

    @Override
    public void getupdateVoting(tb_user user) {
        log.info("get updateVoting");
        voteMapper.updateVoting(user);
    }

    @Override
    public List<Integer> getselectVotingList() {
        log.info("get selectVotingList");
        return voteMapper.selectVotingList();
    }


}
