package com.hustar.edu.vote.api;


import com.hustar.edu.vote.service.AjaxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

@RestController
public class BoardApiController {   //이름을 restcontroller로하면 에러 발생 -> 이름을 rest로 지으면 안됨
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AjaxService service;

    /*	restful은 기본적으로 개발자간에 데이터 전송 형식을 API로 지정한다.
     list/[페이지당 보여줄 수]/[페이지 번호]
     일반적으로 restful은 url 형태로 데이터를 요청하기 때문에 조회에 국한(범위를 일정한 부분에 한정하는 것.)한다.*/
    @RequestMapping(value="/vote/board/boardList/{pagePerCnt}/{page}/{fill}")
    public HashMap<String, Object> listSub(@PathVariable int page, @PathVariable int pagePerCnt, @PathVariable int fill) {
        HashMap<String, Object> map = new HashMap<String,Object>();
        logger.info("page :" + page);
        logger.info("pagePerCnt :"+ pagePerCnt);
        logger.info("fill :"+ fill);

        return service.pagingList(page, pagePerCnt, fill);
    }
}
