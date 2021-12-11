package com.hustar.edu.vote.api;


import com.hustar.edu.vote.auth.PrincipalDetail;
import com.hustar.edu.vote.dto.BoardDTO;
import com.hustar.edu.vote.dto.LikeDTO;
import com.hustar.edu.vote.service.AjaxService;
import com.hustar.edu.vote.service.BoardService;
import com.hustar.edu.vote.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

@RestController
public class BoardApiController {   //이름을 restcontroller로하면 에러 발생 -> 이름을 rest로 지으면 안됨
    @Autowired
    AjaxService service;

    @Autowired
    BoardService boardService;

    @Autowired
    CommonService commonService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /*	restful은 기본적으로 개발자간에 데이터 전송 형식을 API로 지정한다.
     list/[페이지당 보여줄 수]/[페이지 번호]
     일반적으로 restful은 url 형태로 데이터를 요청하기 때문에 조회에 국한(범위를 일정한 부분에 한정하는 것.)한다.*/
    @RequestMapping(value="/vote/board/boardList/{pagePerCnt}/{page}/{fill}")
    public HashMap<String, Object> listSub(@PathVariable int page, @PathVariable int pagePerCnt, @PathVariable int fill) {
        logger.info("page :" + page);
        logger.info("pagePerCnt :"+ pagePerCnt);
        logger.info("fill :"+ fill);

        return service.pagingList(page, pagePerCnt, fill);
    }

    @RequestMapping(value="/vote/board/boardDetail/{page}/{idx}")
    public HashMap<String, Object> boardDetail(@PathVariable String page, @PathVariable int idx) {
        logger.info("게시판 :" + page);
        logger.info("글번호 :"+ idx);
        
        return service.getLike(page, idx);
    }

    @RequestMapping(value="/vote/updateLike/{page}/{idx}/{like}")
    public HashMap<String, Object> updateLike(@PathVariable String page, @PathVariable int idx, @PathVariable String like) {
        logger.info("게시판 :" + page);
        logger.info("글번호 :"+ idx);
        logger.info("좋아요 :"+ like);

        return service.updateLike(page, idx, like);
    }
}
