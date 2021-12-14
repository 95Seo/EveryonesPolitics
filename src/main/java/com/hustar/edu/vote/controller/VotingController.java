package com.hustar.edu.vote.controller;

import com.hustar.edu.vote.dto.tb_user;
import com.hustar.edu.vote.service.VoteServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class VotingController {
    @Autowired
    VoteServiceImpl voteServiceimpl;

    @GetMapping("/vote/voting")
    public String GetVotingCount(tb_user user, Model model){
        log.info("get getVotingCount");

        int voteCount= voteServiceimpl.getVotingCount();
        System.out.println("voteCount : " + voteCount);

        model.addAttribute("voteCount", voteCount);
        return "/vote/voting/voting";
    }
}
