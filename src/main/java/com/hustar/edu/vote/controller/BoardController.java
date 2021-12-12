package com.hustar.edu.vote.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hustar.edu.vote.auth.PrincipalDetail;
import com.hustar.edu.vote.dto.BoardDTO;
import com.hustar.edu.vote.dto.tb_user;
import com.hustar.edu.vote.service.BoardService;
import com.hustar.edu.vote.service.CommonService;
import com.hustar.edu.vote.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String GetBoardListController (@RequestParam(value = "page", required = false, defaultValue = "1") int showPage,
                                          @RequestParam(value = "fill", required = false, defaultValue = "5") int showFill,
                                          Model model) {
        log.info("GetBoardListPage");

        try {
            ObjectMapper mapper = new ObjectMapper();
            String page = mapper.writeValueAsString(showPage);
            String fill = mapper.writeValueAsString(showFill);
            model.addAttribute( "page", page );
            model.addAttribute( "fill", fill );
        } catch (Exception e) {
            e.printStackTrace();
        }
//        int total = boardService.getTotal(cri);
//        cri.setRowStart((cri.getPageNum()-1) * cri.getAmount());
//        List<BoardDTO> boardList = boardService.getBoardList(cri);
//        log.info("nick : " + boardList.get(0).getNickName());
//        model.addAttribute("boardList", boardList);
//        model.addAttribute("pageMaker", new PageDTO(cri, total));

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

    @GetMapping("/vote/boardUpdate")
    public String voteGetBoardUpdateController(int idx, int page, int fill, Model model) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("page", page);
        map.put("fill", fill);
        model.addAttribute("cri", map);

        log.info("VoteBoardUpdatePage");
        model.addAttribute("board", boardService.selectBoardDetail(idx));
        return "/vote/board/boardUpdate";
    }

    @PostMapping("/vote/boardUpdate")
    public String votePostBoardUpdateController(BoardDTO boardDTO, int page, int fill) {
        log.info("VoteBoardUpdatePage");
        boardService.updateBoardDetail(boardDTO);
        return "redirect:/vote/boardDetail?idx="+boardDTO.getIdx()+"&page="+page+"&fill="+fill;
    }

    @GetMapping("/vote/boardDetail")
    public String voteBoardDetailController(int idx, int page, int fill, Model model, HttpServletRequest request, HttpServletResponse response) {
        commonService.viewCountUp("tb_board", idx, request, response);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("page", page);
        map.put("fill", fill);
        model.addAttribute("cri", map);
        log.info("VoteBoardDetailPage");
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonText = mapper.writeValueAsString(idx);
            model.addAttribute( "json", jsonText );
        } catch (Exception e) {
            e.printStackTrace();
        }
        BoardDTO boardDTO = boardService.selectBoardDetail(idx);
        tb_user user = userService.getUser(boardDTO.getWriterIdx());

        model.addAttribute("board", boardDTO);
        model.addAttribute("user", user);

        return "/vote/board/boardDetail";
    }
}
