package com.hustar.edu.vote.controller;

import com.hustar.edu.vote.auth.PrincipalDetail;
import com.hustar.edu.vote.dto.tb_user;
import com.hustar.edu.vote.service.UserService;
import com.hustar.edu.vote.service.VoteServiceImpl;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@Log
public class VotingController {
    @Autowired
    VoteServiceImpl voteServiceimpl;

    @Autowired
    UserService userService;

    @Value("${cos.key}")
    private String cosKey;

    private final AuthenticationManager authenticationManager; // @Autowired

    public VotingController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/vote/voting")
    public String GetVotingCount(@AuthenticationPrincipal @Nullable PrincipalDetail principal, tb_user user, Model model) throws Exception{
        log.info("get getVotingCount");

        int voteCount= voteServiceimpl.getVotingCount();

        System.out.println("voteCount : " + voteCount);

        model.addAttribute("voteCount", voteCount);

        if (principal != null) {
            model.addAttribute("vote", principal.getVote());
        }

        return "/vote/voting/voting";
    }

    @PostMapping("/form")
    public String updateVoting(@AuthenticationPrincipal PrincipalDetail principal, tb_user user, Model model) {
        System.out.println("voting : " + user.getVote());

        user.setIdx(principal.getIdx());

        voteServiceimpl.getupdateVoting(user);

        user = userService.getUser(user.getIdx());

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), cosKey));

        // 실제 SecurityContext에 authentication 정보를 등록한다.
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("재 로그인 성공");

        return "redirect:/";
    }
}
