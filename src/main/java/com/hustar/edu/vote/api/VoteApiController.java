package com.hustar.edu.vote.api;

import com.hustar.edu.vote.auth.PrincipalDetail;
import com.hustar.edu.vote.dto.ResponseDto;
import com.hustar.edu.vote.dto.tb_user;
import com.hustar.edu.vote.service.VoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;

public class VoteApiController {

    @Autowired
    VoteServiceImpl voteServiceimpl;

    public ResponseDto<Integer> save(@RequestBody tb_user user,

                                     @AuthenticationPrincipal PrincipalDetail principal) {
        user.setIdx(principal.getIdx());
        user.setVote(principal.getIdx());

        System.out.println("Vote : " + user.getVote());

        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }
}
