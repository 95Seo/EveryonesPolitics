package com.hustar.edu.vote.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class VoteController {
	@GetMapping("/")
	public String voteMainController() {
		log.info("VoteMainPage");
		return "/vote/main";
	}

//	@GetMapping("/vote/promiseDetail")
//	public String votePromiseDetailController() {
//		log.info("VotePromiseDetailPage");
//		return "/vote/promise/promiseDetail";
//	}
//	@GetMapping("")
//	public String votePromiseListController() {
//		log.info("VotePromiseListPage");
//
//	}
//	@GetMapping("/vote/voting")
//	public String voteVotingController(){
//		log.info("VoteVotingPage");
//		return "/vote/voting/voting";
//	}

	@GetMapping("/vote/myProfileWrite")
	public String voteMyProfileWriteController() {
		log.info("VoteMyProfileWritePage");
		return "/vote/profile/myProfileWrite";
	}
	@GetMapping("/vote/myProfileUpdate")
	public String voteMyProfileUpdateController() {
		log.info("VoteMyProfileUpdatePage");
		return "/vote/profile/myProfileUpdate";
	}
	@GetMapping("/vote/myProfileInfo")
	public String voteMyProfileInfoController() {
		log.info("VoteMyProfileInfoPage");
		return "/vote/profile/myProfileInfo";
	}
}
