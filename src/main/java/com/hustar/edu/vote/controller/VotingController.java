package com.hustar.edu.vote.controller;

import com.google.gson.Gson;
import com.hustar.edu.vote.auth.PrincipalDetail;
import com.hustar.edu.vote.dto.tb_user;
import com.hustar.edu.vote.service.VoteService;
import com.hustar.edu.vote.service.VoteServiceImpl;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@Controller
@Log
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

    @PostMapping("/form")
    public String updateVoting(@AuthenticationPrincipal PrincipalDetail principal, tb_user user, Model model) {
        user.setIdx(principal.getIdx());
        voteServiceimpl.getupdateVoting(user);

        return "redirect:/";
    }

//    private static final Logger logger = LoggerFactory.getLogger(VotingController.class);
//
//    @RequestMapping(value = "/qweqwe", method = RequestMethod.GET)
//    public String votemain(Locale locale, Model model) {
//        return "redirect:/";
//    }
//
//    @RequestMapping(value = "/asdsada", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
//    public @ResponseBody String main(Locale locale, Model model) {
//        Gson gson= new Gson();
//        List<tb_user> votingListView = voteServiceimpl.getselectVotingList();
//
//        return gson.toJson(votingListView);
//    }
}
