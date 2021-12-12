package com.hustar.edu.vote.controller;

import com.hustar.edu.vote.dto.PromiseDTO;
import com.hustar.edu.vote.dto.Time;
import com.hustar.edu.vote.paging.Criteria;
import com.hustar.edu.vote.service.PromiseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.sql.Timestamp;

@Slf4j
@Controller
public class PromiseController {
    @Autowired
    PromiseServiceImpl promiseServiceimpl;

    @GetMapping("/vote/promiseCreate")
    public String promiseCreate() {
        log.info("promisePage");
        return "vote/promise/promiseCreate";
    }

    @GetMapping({"/vote/promiseList"})
    public String GetPromiseBoardList(Criteria cri, Model model) {
        log.info("GetPromiseBoardListPage");
        int total = promiseServiceimpl.getPromiseTotal(cri);
        List<PromiseDTO> promiseboardList = promiseServiceimpl.getPromiseBoardList(cri);
        model.addAttribute("promiseboardList", promiseboardList);
        return "/vote/promise/promiseList";
    }

    @GetMapping({"/vote/promiseDetail"})
    public String findById(PromiseDTO promiseDTO, Model model){
        log.info("GetPromiseBoardDetailPage");

        PromiseDTO promiseDetail = promiseServiceimpl.getPromiseBoardDetail(promiseDTO);
        System.out.println("promiseDetail : " + promiseDetail.getContent());
        System.out.println("Time : " + promiseDetail.getSysregdate());

        System.out.println("date : "+ Time.calculateTime(promiseDetail.getSysregdate()));
        model.addAttribute("promiseDetail", promiseDetail);
        model.addAttribute("calcTime", Time.calculateTime(promiseDetail.getSysregdate()));
        return "/vote/promise/promiseDetail";
    }
}
