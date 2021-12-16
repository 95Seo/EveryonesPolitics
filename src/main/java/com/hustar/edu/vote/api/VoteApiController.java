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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @RequestMapping(value="/vote/votingListView", method = RequestMethod.GET)
    public List<Integer> GetselectVotingList(@RequestParam String age_range, Model model){

        log.info("get GetselectVotingList");

        List<Integer> list = new ArrayList<Integer>();

        for (int vote = 1; vote <= 4; vote++) {
            int voteChartView = voteServiceimpl.getselectChartView(vote, age_range);
            list.add(voteChartView);
        }

        return list;
    }
}
