package com.hustar.edu.vote.service;

import com.hustar.edu.vote.auth.PrincipalDetail;
import com.hustar.edu.vote.dto.BoardDTO;
import com.hustar.edu.vote.dto.LikeDTO;
import com.hustar.edu.vote.paging.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class AjaxService {
    @Autowired
    BoardService boardService;

    @Autowired
    CommonService commonService;

    public HashMap<String, Object> getLike(String page, int idx) {
        // page - 게시판 종류
        // idx - 게시글 번호
        HashMap<String, Object> json = new HashMap<>();
        LikeDTO likeDTO = new LikeDTO();
        LikeDTO new_likeDTO = null;
        likeDTO.setBoardNm(page);
        likeDTO.setBoardIdx(idx);

        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            PrincipalDetail userDetails = (PrincipalDetail) principal;
            likeDTO.setWriterIdx(((PrincipalDetail) principal).getIdx());
            if(commonService.getLike(likeDTO) == null) {
                likeDTO.setLikeYn("N");
                json.put("like", likeDTO);
            } else {
                json.put("like", commonService.getLike(likeDTO));
            }

            json.put("likeCnt", commonService.getLikeCount(likeDTO));
        } catch (Exception e) {
            e.getStackTrace();
            likeDTO.setLikeYn("NotLogin");
            json.put("like", likeDTO);
            json.put("likeCnt", commonService.getLikeCount(likeDTO));
        }

        return json;
    }

    public HashMap<String, Object> updateLike(String page, int idx, String like) {
        // page - 게시판 종류
        // idx - 게시글 번호
        // like - 좋아요
        // 선언
        HashMap<String, Object> json = new HashMap<>();

        LikeDTO likeDTO = new LikeDTO();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PrincipalDetail userDetails = (PrincipalDetail) principal;
        likeDTO.setWriterIdx(((PrincipalDetail) principal).getIdx());
        likeDTO.setBoardNm(page);
        likeDTO.setBoardIdx(idx);
        likeDTO.setLikeYn(like);

        if(commonService.getLike(likeDTO) == null) {
            commonService.insertLike(likeDTO);
            json.put("message", "InsertLike : " + likeDTO.getLikeYn());
        } else {
            Date date = new Date();
            java.sql.Timestamp sqlDate = new java.sql.Timestamp(date.getTime());
            likeDTO.setSysmoddate(sqlDate);

            commonService.updateLike(likeDTO);
            json.put("message", "UpdateLike : " + likeDTO.getLikeYn());
        }

        likeDTO.setLikeCnt(commonService.getLikeCount(likeDTO));
        System.out.println("getBoardNm:" + likeDTO.getBoardNm());
        commonService.updateLikeCount(likeDTO);

        return json;
    }
}