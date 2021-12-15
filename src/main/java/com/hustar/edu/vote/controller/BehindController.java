package com.hustar.edu.vote.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hustar.edu.vote.FileUpload.FileUploadHandler;
import com.hustar.edu.vote.FileUpload.ThumbnailUploadHandler;
import com.hustar.edu.vote.FileUpload.UploadFile;
import com.hustar.edu.vote.auth.PrincipalDetail;
import com.hustar.edu.vote.dto.BehindDTO;
import com.hustar.edu.vote.dto.tb_user;
import com.hustar.edu.vote.paging.Criteria;
import com.hustar.edu.vote.service.BehindService;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Slf4j
@Controller
public class BehindController {
    @Autowired
    BehindService behindService;

    @Autowired
    UserService userService;

    @Autowired
    CommonService commonService;

    @Autowired
    ThumbnailUploadHandler thumbnailUploadHandler;

    @GetMapping("/vote/behindList")
    public String GetBehindListController (Criteria criteria,
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
        return "/vote/behind/behindList";
    }

    @GetMapping("/vote/behindDetail")
    public String voteBehindDetailController(int idx, Criteria criteria, Model model, HttpServletRequest request, HttpServletResponse response) {
        commonService.viewCountUp("tb_behind", idx, request, response);

        model.addAttribute( "page", criteria.getPage() );
        model.addAttribute( "filter", criteria.getFilter() );
        model.addAttribute( "keyword", criteria.getKeyword() );
        model.addAttribute( "idx", idx );

        BehindDTO behindDTO = behindService.selectBehindDetail(idx);
        tb_user user = userService.getUser(behindDTO.getWriterIdx());

        model.addAttribute("behind", behindDTO);
        model.addAttribute("user", user);

        return "/vote/behind/behindDetail";
    }

    @GetMapping("/vote/behindCreate")
    public String voteBehindCreateController() {

        log.info("VoteBehindPage");
        return "/vote/behind/behindCreate";
    }

    @PostMapping("/vote/behindCreate")
    public String PostBehindCreateController (@RequestParam("title") String title, @RequestParam("content") String content,
                                              @RequestParam MultipartFile multipartFile, String filter) {

        log.info("PostBehindCreatePage");

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PrincipalDetail userDetails = (PrincipalDetail)principal;

        BehindDTO behindDTO = new BehindDTO();

        UploadFile file_url = thumbnailUploadHandler.thumbnailFileUpload(multipartFile, "BEHIND/THUMBNAIL");
        System.out.println("file_url : " + file_url.getFile_dir());

        behindDTO.setWriterIdx(((PrincipalDetail) principal).getIdx());
        behindDTO.setTitle(title);
        behindDTO.setContent(content);
        behindDTO.setFileUrl(file_url.getFile_dir());
        behindDTO.setFilter(filter);

        behindService.insertBehind(behindDTO);
        return "redirect:/vote/behindList";
    }

    @GetMapping("/vote/behindUpdate")
    public String voteGetBehindUpdateController(int idx, Criteria criteria, Model model) {
        model.addAttribute( "page", criteria.getPage() );
        model.addAttribute( "filter", criteria.getFilter() );
        model.addAttribute( "keyword", criteria.getKeyword() );

        log.info("VoteBehindUpdatePage");
        model.addAttribute("behind", behindService.selectBehindDetail(idx));
        return "/vote/behind/behindUpdate";
    }

    @PostMapping("/vote/behindUpdate")
    public String votePostBehindUpdateController(BehindDTO behindDTO, Criteria criteria, RedirectAttributes rttr, @RequestParam MultipartFile multipartFile) {
        log.info("VoteBehindUpdatePage");
        UploadFile file_url = thumbnailUploadHandler.thumbnailFileUpload(multipartFile, "BEHIND/THUMBNAIL");
        behindDTO.setFileUrl(file_url.getFile_dir());
        behindService.updateBehindDetail(behindDTO);
        rttr.addAttribute("page", criteria.getPage());
        rttr.addAttribute("filter", criteria.getFilter());
        rttr.addAttribute("keyword", criteria.getKeyword());
        rttr.addAttribute("idx", behindDTO.getIdx());
        return "redirect:/vote/behindDetail";
    }
}