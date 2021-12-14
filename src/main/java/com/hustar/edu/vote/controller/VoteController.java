package com.hustar.edu.vote.controller;

import com.hustar.edu.vote.auth.PrincipalDetail;
import com.hustar.edu.vote.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class VoteController {

	@Autowired
	UserService userService;

	@GetMapping("/")
	public String voteMainController() {
		log.info("VoteMainPage");
		return "/vote/main";
	}

	@GetMapping("/vote/myProfileWrite")
	public String voteMyProfileWriteController() {
		log.info("VoteMyProfileWritePage");
		return "/vote/profile/myProfileWrite";
	}
	@GetMapping("/vote/myProfileUpdate")
	public String voteMyProfileUpdateController(@AuthenticationPrincipal PrincipalDetail principal, Model model) {
		log.info("VoteMyProfileUpdatePage");

		model.addAttribute("info",userService.searchUser(principal.getUsername()));
		return "/vote/profile/myProfileUpdate";
	}
	@GetMapping("/vote/myProfileInfo")
	public String voteMyProfileInfoController(@AuthenticationPrincipal PrincipalDetail principal, Model model) {
		log.info("VoteMyProfileInfoPage");
		model.addAttribute("info",userService.searchUser(principal.getUsername()));
		return "/vote/profile/myProfileInfo";
	}
}
