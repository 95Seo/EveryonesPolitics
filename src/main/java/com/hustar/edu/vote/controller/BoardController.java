package com.hustar.edu.vote.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hustar.edu.vote.auth.PrincipalDetail;
import com.hustar.edu.vote.dto.BoardDTO;
import com.hustar.edu.vote.dto.Time;
import com.hustar.edu.vote.dto.tb_user;
import com.hustar.edu.vote.paging.Criteria;
import com.hustar.edu.vote.service.BoardService;
import com.hustar.edu.vote.service.CommonService;
import com.hustar.edu.vote.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Slf4j
@Controller
public class BoardController {
    @Autowired
    BoardService boardService;

    @Autowired
    UserService userService;

    @Autowired
    CommonService commonService;

    @GetMapping("/vote/boardList")
    public String GetBoardListController (Criteria criteria,
                                          Model model) {
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
        return "/vote/board/boardList";
    }

    @GetMapping("/vote/boardCreate")
    public String voteBoardCreateController() {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            PrincipalDetail userDetails = (PrincipalDetail) principal;
        } catch (Exception e) {
            return "redirect:/vote/boardList";
        }
        log.info("VoteBoardPage");
        return "/vote/board/boardCreate";
    }

    @PostMapping("/vote/boardCreate")
    public String PostBoardCreateController (@RequestParam("title") String title, @RequestParam("content") String content, String filter) {

        log.info("PostBoardCreatePage");

        System.out.println("filter:"+filter);

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PrincipalDetail userDetails = (PrincipalDetail)principal;

        BoardDTO boardDTO = new BoardDTO();

        boardDTO.setWriterIdx(((PrincipalDetail) principal).getIdx());
        boardDTO.setTitle(title);
        boardDTO.setContent(content);
        boardDTO.setFilter(filter);

        boardService.insertBoard(boardDTO);
        return "redirect:/vote/boardList";
    }

    @GetMapping("/vote/boardUpdate")
    public String voteGetBoardUpdateController(int idx, Criteria criteria, Model model) {
        model.addAttribute( "page", criteria.getPage() );
        model.addAttribute( "filter", criteria.getFilter() );
        model.addAttribute( "keyword", criteria.getKeyword() );

        log.info("VoteBoardUpdatePage");
        model.addAttribute("board", boardService.selectBoardDetail(idx));
        return "/vote/board/boardUpdate";
    }

    @PostMapping("/vote/boardUpdate")
    public String votePostBoardUpdateController(BoardDTO boardDTO, Criteria criteria, RedirectAttributes rttr) {
        log.info("VoteBoardUpdatePage");
        boardService.updateBoardDetail(boardDTO);
        rttr.addAttribute("page", criteria.getPage());
        rttr.addAttribute("filter", criteria.getFilter());
        rttr.addAttribute("keyword", criteria.getKeyword());
        rttr.addAttribute("idx", boardDTO.getIdx());
        return "redirect:/vote/boardDetail";
    }

    @GetMapping("/vote/boardDetail")
    public String voteBoardDetailController(@AuthenticationPrincipal @Nullable PrincipalDetail principal, int idx, Criteria criteria, Model model, HttpServletRequest request, HttpServletResponse response) {
        commonService.viewCountUp("tb_board", idx, request, response);

        model.addAttribute( "page", criteria.getPage() );
        model.addAttribute( "filter", criteria.getFilter() );
        model.addAttribute( "keyword", criteria.getKeyword() );
        model.addAttribute( "idx", idx );

        BoardDTO boardDTO = boardService.selectBoardDetail(idx);
        tb_user user = userService.getUser(boardDTO.getWriterIdx());

        System.out.println("getProfile_img:" + user.getProfile_img());

        if (principal != null) {
            model.addAttribute("current_idx", principal.getIdx());
        } else {
            model.addAttribute("current_idx", 0);
        }

        model.addAttribute("board", boardDTO);
        model.addAttribute("user", user);
        model.addAttribute("calcTime", Time.calculateTime(boardDTO.getSysregdate()));

        return "/vote/board/boardDetail";
    }

    @GetMapping("/vote/boardDelete")
    public String voteBoardDeleteController(BoardDTO boardDTO) {
        log.info("VoteBoardUpdatePage");
        boardService.deleteBoardDetail(boardDTO);
        return "redirect:/vote/boardList";
    }
}