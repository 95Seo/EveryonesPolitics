package com.hustar.edu.vote.controller;

import com.hustar.edu.vote.auth.PrincipalDetail;
import com.hustar.edu.vote.dto.BoardDTO;
import com.hustar.edu.vote.paging.Criteria;
import com.hustar.edu.vote.paging.PageDTO;
import com.hustar.edu.vote.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
public class BoardController {
    @Autowired
    BoardService boardService;

    @PostMapping("/vote/boardCreate")
    public String PostBoardCreateController (@RequestParam("title") String title, @RequestParam("content") String content) {
        log.info("PostBoardCreatePage");

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PrincipalDetail userDetails = (PrincipalDetail)principal;

        BoardDTO boardDTO = new BoardDTO();

        boardDTO.setWriter_idx(((PrincipalDetail) principal).getIdx());
        boardDTO.setTitle(title);
        boardDTO.setContent(content);
        // 1 - 공약, 2 - 비하인드, 3 - 사용자 게시판
        boardDTO.setDashboard_cd("3");

        boardService.insertBoard(boardDTO);
        return "/vote/board/boardList";
    }

    @GetMapping("/vote/boardList")
    public String GetBoardListController (Criteria cri, Model model) {
        log.info("GetBoardListPage");
        int total = boardService.getTotal(cri);
        List<BoardDTO> boardList = boardService.getBoardList(cri);
        model.addAttribute("boardList", boardList);
        model.addAttribute("pageMaker", new PageDTO(cri, total));

        return "/vote/board/boardList";
    }
}
