package com.hustar.edu.vote.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hustar.edu.vote.FileUpload.ThumbnailUploadHandler;
import com.hustar.edu.vote.FileUpload.UploadFile;
import com.hustar.edu.vote.auth.PrincipalDetail;
import com.hustar.edu.vote.dto.BehindDTO;
import com.hustar.edu.vote.dto.PromiseDTO;
import com.hustar.edu.vote.dto.Time;
import com.hustar.edu.vote.dto.tb_user;
import com.hustar.edu.vote.paging.Criteria;
import com.hustar.edu.vote.service.PromiseService;
import com.hustar.edu.vote.service.CommonService;
import com.hustar.edu.vote.service.UserService;
import com.sun.istack.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Slf4j
@Controller
public class PromiseController {
    @Autowired
    PromiseService promiseService;

    @Autowired
    UserService userService;

    @Autowired
    CommonService commonService;

    @Autowired
    ThumbnailUploadHandler thumbnailUploadHandler;

    @GetMapping("/vote/promiseList")
    public String GetPromiseListController (Criteria criteria,
                                           Model model) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            HashMap<String, Object> map = new HashMap<>();
            map.put("amount", 9);
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
        return "/vote/promise/promiseList";
    }

    @GetMapping("/vote/promiseCreate")
    public String votePromiseCreateController() {

        log.info("VotePromisePage");
        return "/vote/promise/promiseCreate";
    }

    @PostMapping("/vote/promiseCreate")
    public String PostPromiseCreateController (PromiseDTO promiseDTO,
                                              @RequestParam MultipartFile multipartFile) {

        log.info("PostPromiseCreatePage");

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PrincipalDetail userDetails = (PrincipalDetail)principal;

        UploadFile file_url = thumbnailUploadHandler.thumbnailFileUpload(multipartFile, "PROMISE/THUMBNAIL");
        System.out.println("file_url : " + file_url.getFile_dir());

        promiseDTO.setWriterIdx(((PrincipalDetail) principal).getIdx());
        promiseDTO.setFileUrl(file_url.getFile_dir());

        promiseService.insertPromise(promiseDTO);
        return "redirect:/vote/promiseList";
    }

    @GetMapping("/vote/promiseUpdate")
    public String voteGetPromiseUpdateController(int idx, Criteria criteria, Model model) {
        model.addAttribute( "page", criteria.getPage() );
        model.addAttribute( "filter", criteria.getFilter() );
        model.addAttribute( "keyword", criteria.getKeyword() );

        log.info("VotePromiseUpdatePage");
        model.addAttribute("promise", promiseService.selectPromiseDetail(idx));
        return "/vote/promise/promiseUpdate";
    }

    @PostMapping("/vote/promiseUpdate")
    public String votePostPromiseUpdateController(PromiseDTO promiseDTO, Criteria criteria, RedirectAttributes rttr, @RequestParam @Nullable MultipartFile multipartFile) {
        log.info("VotePromiseUpdatePage");

        try {
            UploadFile file_url = thumbnailUploadHandler.thumbnailFileUpload(multipartFile, "PROMISE/THUMBNAIL");
            promiseDTO.setFileUrl(file_url.getFile_dir());
        } catch (Exception e) {
            e.printStackTrace();
            promiseDTO.setFileUrl("null");
        } finally {
            promiseService.updatePromiseDetail(promiseDTO);
            rttr.addAttribute("page", criteria.getPage());
            rttr.addAttribute("filter", criteria.getFilter());
            rttr.addAttribute("keyword", criteria.getKeyword());
            rttr.addAttribute("idx", promiseDTO.getIdx());
        }

        return "redirect:/vote/promiseDetail";
    }

    @GetMapping("/vote/promiseDetail")
    public String votePromiseDetailController(int idx, Criteria criteria, Model model, HttpServletRequest request, HttpServletResponse response) {
        commonService.viewCountUp("tb_promise", idx, request, response);

        model.addAttribute( "page", criteria.getPage() );
        model.addAttribute( "filter", criteria.getFilter() );
        model.addAttribute( "keyword", criteria.getKeyword() );
        model.addAttribute( "idx", idx );

        PromiseDTO promiseDTO = promiseService.selectPromiseDetail(idx);
        tb_user user = userService.getUser(promiseDTO.getWriterIdx());

        model.addAttribute("promise", promiseDTO);
        model.addAttribute("user", user);
        model.addAttribute("calcTime", Time.calculateTime(promiseDTO.getSysregdate()));

        return "/vote/promise/promiseDetail";
    }

    @GetMapping("/vote/promiseDelete")
    public String voteBoardDeleteController(PromiseDTO promiseDTO) {
        log.info("VoteBoardUpdatePage");
        promiseService.deletePromiseDetail(promiseDTO);

        return "redirect:/vote/promiseList";
    }
}