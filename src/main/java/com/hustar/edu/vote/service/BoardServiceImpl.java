package com.hustar.edu.vote.service;

import com.hustar.edu.vote.dto.BoardDTO;
import com.hustar.edu.vote.mapper.BoardMapper;
import com.hustar.edu.vote.paging.Criteria;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    BoardMapper boardMapper;

    @Autowired
    CommonService commonService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void insertBoard(BoardDTO boardDTO) {
        boardMapper.insertBoard(boardDTO);
    }

    @Override
    public HashMap<String, Object> selectBoardList(Criteria cri) {
        HashMap<String, Object> json = new HashMap<>();
        List<BoardDTO> boardList = Collections.emptyList();

        // page 페이지 번호 = pageNum
        // cnt 페이지당 글 갯수 = amount
        int page = cri.getPage();

        //총 게시물 수
        int allCnt = boardMapper.selectBoardTotalCount(cri);

        // = 생성 가능 페이지 수 (나머지가 있으면 페이지 하나 더 생성)
        int range = allCnt % cri.getAmount()>0 ?
                Math.round(allCnt/cri.getAmount())+1 : allCnt/cri.getAmount();
        logger.info("총 게시물 수 : {}", allCnt);
        logger.info("생성 가능 페이지 수 : {}", range);

        try {
            //요청 페이지가 생성 가능 페이지 보다 클 경우 생성 가능 페이지로 적용
            if(page >range) {
                page = range;
            }
//        int end = page * cnt; // 5페이지 일때 200
            int start = (page-1) * cri.getAmount(); //5페이지 일때 첫 페이지 번호 81
            cri.setStart(start);

            boardList = boardMapper.selectBoardList(cri);

            json.put("message", "success");
            json.put("range",range);       //생성 가능한 총 페이지 수
            json.put("fill",cri.getFilter());
            json.put("keyword",cri.getKeyword());
            json.put("list", boardList); //요청한 게시물

            json.put("currPage", page);//현재 페이지
            return json;
        } catch (Exception e) {
            json.put("message", "fail");
            return json;
        }
    }

    public BoardDTO selectBoardDetail(int idx) {
        return boardMapper.selectBoardDetail(idx);
    }

    public void updateBoardDetail(BoardDTO boardDTO) {
        Date date = new Date();

        java.sql.Timestamp sqlDate = new java.sql.Timestamp(date.getTime());

        boardDTO.setSysmoddate(sqlDate);

        boardMapper.updateBoardDetail(boardDTO);
    }

    @Override
    public int getTotal(Criteria cri) {
        log.info("get total count");
        return boardMapper.selectBoardTotalCount(cri);
    }

    @Override
    public HashMap<String, Object> selectMyBoardList(Criteria cri, int idx) {
        HashMap<String, Object> json = new HashMap<>();
        List<BoardDTO> boardList = Collections.emptyList();

        // page 페이지 번호 = pageNum
        // cnt 페이지당 글 갯수 = amount
        int page = cri.getPage();

        //총 게시물 수
        int allCnt = boardMapper.selectMyBoardTotalCount(idx);

        // = 생성 가능 페이지 수 (나머지가 있으면 페이지 하나 더 생성)
        int range = allCnt % cri.getAmount()>0 ?
                Math.round(allCnt/cri.getAmount())+1 : allCnt/cri.getAmount();
        logger.info("총 게시물 수 : {}", allCnt);
        logger.info("생성 가능 페이지 수 : {}", range);

        try {
            //요청 페이지가 생성 가능 페이지 보다 클 경우 생성 가능 페이지로 적용
            if(page >range) {
                page = range;
            }
//        int end = page * cnt; // 5페이지 일때 200
            int start = (page-1) * cri.getAmount(); //5페이지 일때 첫 페이지 번호 81
            cri.setStart(start);

            System.out.println("로그인 유저 : " + idx);
            boardList = boardMapper.selectMyBoardList(start, cri.getAmount(), idx);
            System.out.println("정보를 가져 오나요?" + boardList.get(0).getContent());

            json.put("message", "success");
            json.put("range",range);       //생성 가능한 총 페이지 수
            json.put("fill",cri.getFilter());
            json.put("keyword",cri.getKeyword());
            json.put("list", boardList); //요청한 게시물
            System.out.println("list : " + boardList);

            json.put("currPage", page);//현재 페이지
            return json;
        } catch (Exception e) {
            json.put("message", "fail");
            return json;
        }
    }

    public List<BoardDTO> selectLikeBoardList() {
        return boardMapper.selectLikeBoardList();
    }

    @Override
    public int selectMyBoardTotalCount(int idx) {
        log.info("get total My Board Count");
        return boardMapper.selectMyBoardTotalCount(idx);
    }

    public void deleteBoardDetail(BoardDTO boardDTO) {
        log.info("Board Delete Board_No : "+ boardDTO.getIdx());
        boardMapper.deleteBoardDetail(boardDTO);
    }
}
