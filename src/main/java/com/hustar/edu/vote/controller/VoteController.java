package com.hustar.edu.vote.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hustar.edu.vote.FileUpload.ThumbnailUploadHandler;
import com.hustar.edu.vote.FileUpload.UploadFile;
import com.hustar.edu.vote.auth.PrincipalDetail;
import com.hustar.edu.vote.dto.BoardDTO;
import com.hustar.edu.vote.dto.tb_user;
import com.hustar.edu.vote.paging.Criteria;
import com.hustar.edu.vote.service.BoardService;
import com.hustar.edu.vote.service.UserService;
import com.sun.istack.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Controller
public class VoteController {

	@Autowired
	UserService userService;

	@Autowired
	BoardService boardService;

	@Value("${cos.key}")
	private String cosKey;

	@Autowired
	ThumbnailUploadHandler thumbnailUploadHandler;

	private final AuthenticationManager authenticationManager; // @Autowired

	public VoteController(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@GetMapping("/vote/")
	public String voteMainController(Criteria criteria, Model model) {
		log.info("VoteMainPage");
		try {
			ObjectMapper mapper = new ObjectMapper();
			HashMap<String, Object> map = new HashMap<>();
			map.put("amount", criteria.getAmount());
			map.put("page", criteria.getPage());
			map.put("filter", criteria.getFilter());
			map.put("keyword", criteria.getKeyword());

			String json = mapper.writeValueAsString(map);
			model.addAttribute( "json", json );
			log.info("json:"+ json);

			log.info("성공");
		} catch(Exception e) {
			e.printStackTrace();
			log.info("실패");
		}
		List<BoardDTO> list = boardService.selectLikeBoardList();

		model.addAttribute("list", list);

		return "/vote/main";
	}

	@GetMapping("/vote/myProfileInfo")
	public String voteMyProfileInfoController(@AuthenticationPrincipal PrincipalDetail principal, Model model) {
		log.info("VoteMyProfileInfoPage");
		model.addAttribute("info",userService.searchUser(principal.getUsername()));
		return "/vote/profile/myProfileInfo";
	}

	@GetMapping("/vote/myProfileUpdate")
	public String GetMyProfileUpdateController(@AuthenticationPrincipal PrincipalDetail principal, Model model) {
		log.info("VoteMyProfileUpdatePage");

		model.addAttribute("info",userService.searchUser(principal.getUsername()));
		return "/vote/profile/myProfileUpdate";
	}

	@PostMapping("/vote/myProfileUpdate")
	public String PostMyProfileUpdateController(tb_user user, @RequestParam @Nullable MultipartFile profileImg) {
		log.info("VoteMyProfileUpdatePage");

		if(!profileImg.isEmpty()) {
			UploadFile file_url = thumbnailUploadHandler.profileFileUpload(profileImg, "USER", user.getIdx());
			System.out.println("dir : " + file_url.getFile_dir());
			user.setProfile_img(file_url.getFile_dir());
		}
		
		userService.updateUser(user);

		user = userService.getUser(user.getIdx());

		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), cosKey));

		// 실제 SecurityContext에 authentication 정보를 등록한다.
		SecurityContextHolder.getContext().setAuthentication(authentication);
		System.out.println("재 로그인 성공");

		return "redirect:/vote/myProfileInfo";
	}

	@GetMapping("/vote/myProfileWrite")
	public String voteMyProfileWriteController(Criteria criteria, @AuthenticationPrincipal PrincipalDetail principal, Model model) {
		log.info("VoteMyProfileWritePage");
		try {
			ObjectMapper mapper = new ObjectMapper();
			HashMap<String, Object> map = new HashMap<>();
			map.put("amount", 12);
			map.put("page", criteria.getPage());
			map.put("filter", criteria.getFilter());
			map.put("keyword", criteria.getKeyword());

			String json = mapper.writeValueAsString(map);
			model.addAttribute( "json", json );
			log.info("json:"+ json);

			log.info("성공");
		} catch(Exception e) {
			e.printStackTrace();
			log.info("실패");
		}

		model.addAttribute("info",userService.searchUser(principal.getUsername()));

		return "/vote/profile/myProfileWrite";
	}

	@GetMapping(value = "/logout")
	public String loout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "/vote/main";
	}
}
