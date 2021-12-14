package com.hustar.edu.vote.api;


import com.hustar.edu.vote.paging.Criteria;
import com.hustar.edu.vote.service.AjaxService;
import com.hustar.edu.vote.service.BehindService;
import com.hustar.edu.vote.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class BehindApiController {   //이름을 restcontroller로하면 에러 발생 -> 이름을 rest로 지으면 안됨
    @Autowired
    AjaxService service;

    @Autowired
    BehindService behindService;

    @Autowired
    CommonService commonService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /*	restful은 기본적으로 개발자간에 데이터 전송 형식을 API로 지정한다.
     list/[페이지당 보여줄 수]/[페이지 번호]
     일반적으로 restful은 url 형태로 데이터를 요청하기 때문에 조회에 국한(범위를 일정한 부분에 한정하는 것.)한다.*/
    @RequestMapping(value="/vote/behind/behindList/{amount}/{page}/{fill}")
    public HashMap<String, Object> listSub(@PathVariable int amount, @PathVariable int page, @PathVariable String fill) {
        Criteria cri = new Criteria();
        cri.setAmount(amount);
        cri.setPage(page);
        cri.setFilter(fill);
        logger.info("amount :" + cri.getAmount());
        logger.info("page :"+ cri.getPage());
        logger.info("fill :"+ cri.getFilter());

        return behindService.selectBehindList(cri);
    }

    @RequestMapping(value="/vote/getBehindPagingList/", method= RequestMethod.POST)
    public HashMap<String, Object> listSub(@RequestBody Criteria cri) {
        
        logger.info("amount :" + cri.getAmount());
        logger.info("page :"+ cri.getPage());
        logger.info("fill :"+ cri.getFilter());
        logger.info("keyword :"+ cri.getKeyword());

        return behindService.selectBehindList(cri);
    }

    @RequestMapping(value="/vote/behind/behindDetail/{page}/{idx}")
    public HashMap<String, Object> behindDetail(@PathVariable String page, @PathVariable int idx) {
        logger.info("게시판 :" + page);
        logger.info("글번호 :"+ idx);
        
        return service.getLike(page, idx);
    }
}
