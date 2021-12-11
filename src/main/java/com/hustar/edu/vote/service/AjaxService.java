package com.hustar.edu.vote.service;

import com.hustar.edu.vote.auth.PrincipalDetail;
import com.hustar.edu.vote.dto.BoardDTO;
import com.hustar.edu.vote.dto.LikeDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class AjaxService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    BoardService boardService;

    @Autowired
    CommonService commonService;

    public HashMap<String, Object> pagingList(int page, int cnt, int fill) {
        // page 페이지 번호 = pageNum
        // cnt 페이지당 글 갯수 = amount

        //총 게시물 수
        int allCnt = boardService.getTotal(fill);

        // = 생성 가능 페이지 수 (나머지가 있으면 페이지 하나 더 생성)
        int range = allCnt % cnt>0 ?
                Math.round(allCnt/cnt)+1 : allCnt/cnt;
        logger.info("총 게시물 수 : {}", allCnt);
        logger.info("생성 가능 페이지 수 : {}", range);

        //요청 페이지가 생성 가능 페이지 보다 클 경우 생성 가능 페이지로 적용
        if(page >range) {
            page = range;
        }
//        int end = page * cnt; // 5페이지 일때 200
        int start = (page-1) * cnt; //5페이지 일때 첫 페이지 번호 81


        HashMap<String, Object> json = new HashMap<>();
        json.put("range",range);       //생성 가능한 총 페이지 수
        json.put("fill",fill);
        json.put("list", boardService.getBoardList(start,cnt,fill)); //요청한 게시물

        List<BoardDTO> list = boardService.getBoardList(start,cnt,fill);

        json.put("currPage", page);//현재 페이지
        return json;
    }

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
            commonService.updateLike(likeDTO);
            json.put("message", "UpdateLike : " + likeDTO.getLikeYn());
        }

        return json;
    }
}
