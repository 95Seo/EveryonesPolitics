package com.hustar.edu.vote.api;

import com.hustar.edu.vote.auth.PrincipalDetail;
import com.hustar.edu.vote.paging.Criteria;
import com.hustar.edu.vote.service.BoardService;
import com.hustar.edu.vote.service.PromiseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class MainApiController {
    @Autowired
    PromiseService promiseService;

    @Autowired
    BoardService boardService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value="/vote/getMainPromiseList/{amount}/{page}/{fill}")
    public HashMap<String, Object> listSub(@PathVariable int amount, @PathVariable int page, @PathVariable String fill) {
        Criteria cri = new Criteria();
        cri.setAmount(amount);
        cri.setPage(page);
        cri.setFilter(fill);
        logger.info("amount :" + cri.getAmount());
        logger.info("page :"+ cri.getPage());
        logger.info("fill :"+ cri.getFilter());

        return promiseService.selectPromiseList(cri);
    }

    @RequestMapping(value="/vote/getMainPromiseList/", method= RequestMethod.POST)
    public HashMap<String, Object> postMainPromiseList(@RequestBody Criteria cri) {
        System.out.println("getMainPromiseList");
        logger.info("amount :" + cri.getAmount());
        logger.info("page :"+ cri.getPage());
        logger.info("fill :"+ cri.getFilter());
        logger.info("keyword :"+ cri.getKeyword());

        return promiseService.selectPromiseList(cri);
    }

    @RequestMapping(value="/vote/selectMyBoardList/", method= RequestMethod.POST)
    public HashMap<String, Object> selectMyBoardList(@RequestBody Criteria cri, @AuthenticationPrincipal PrincipalDetail principal) {

        logger.info("amount :" + cri.getAmount());
        logger.info("page :"+ cri.getPage());
        logger.info("fill :"+ cri.getFilter());
        logger.info("keyword :"+ cri.getKeyword());

        return boardService.selectMyBoardList(cri, principal.getIdx());
    }
}
