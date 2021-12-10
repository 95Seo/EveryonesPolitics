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
	@GetMapping("/vote/boardCreate")
	public String voteBoardController() {
		log.info("VoteBoardPage");
		return "/vote/board/boardCreate";
	}
	@GetMapping("/vote/boardDetail")
	public String voteBoardDetailController() {
		log.info("VoteBoardDetailPage");
		return "/vote/board/boardDetail";
	}

	@GetMapping("/vote/promiseDetail")
	public String votePromiseDetailController() {
		log.info("VotePromiseDetailPage");
		return "/vote/promise/promiseDetail";
	}
	@GetMapping("/vote/promiseList")
	public String votePromiseListController() {
		log.info("VotePromiseListPage");
		return "/vote/promise/promiseList";
	}

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

	@GetMapping("/vote/behindList")
	public String voteBehindListController() {
		log.info("VoteBehindListPage");
		return "/vote/behind/behindList";
	}
}
