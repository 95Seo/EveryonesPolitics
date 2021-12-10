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

        boardDTO.setWriterIdx(((PrincipalDetail) principal).getIdx());
        boardDTO.setTitle(title);
        boardDTO.setContent(content);

        boardService.insertBoard(boardDTO);
        return "redirect:/vote/boardList";
    }

    @GetMapping("/vote/boardList")
    public String GetBoardListController (Model model) {
        log.info("GetBoardListPage");
//        int total = boardService.getTotal(cri);
//        cri.setRowStart((cri.getPageNum()-1) * cri.getAmount());
//        List<BoardDTO> boardList = boardService.getBoardList(cri);
//        log.info("nick : " + boardList.get(0).getNickName());
//        model.addAttribute("boardList", boardList);
//        model.addAttribute("pageMaker", new PageDTO(cri, total));

        return "/vote/board/boardList";
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
}
