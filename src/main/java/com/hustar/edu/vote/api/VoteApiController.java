package com.hustar.edu.vote.api;

import com.hustar.edu.vote.auth.PrincipalDetail;
import com.hustar.edu.vote.dto.ResponseDto;
import com.hustar.edu.vote.dto.tb_user;
import com.hustar.edu.vote.service.VoteServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class VoteApiController {

    @Autowired
    VoteServiceImpl voteServiceimpl;

    public ResponseDto<Integer> save(@RequestBody tb_user user,

                                     @AuthenticationPrincipal PrincipalDetail principal) {
        user.setIdx(principal.getIdx());
        user.setVote(principal.getIdx());

        System.out.println("Vote : " + user.getVote());

        voteServiceimpl.getupdateVoting(user);

        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }

    @RequestMapping("/vote/votingListView")
    public List<Integer> GetselectvotingList(tb_user user, Model model){

        log.info("get GetselectvotingList");

        List<Integer> voteList = voteServiceimpl.getselectVotingList();
        model.addAttribute("voteList", voteList);
        return voteList;
    }
}
