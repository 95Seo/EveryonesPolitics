package com.hustar.edu.vote.service;

import com.hustar.edu.vote.dto.BehindDTO;
import com.hustar.edu.vote.mapper.BehindMapper;
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
public class BehindServiceImpl implements BehindService {
    @Autowired
    BehindMapper behindMapper;

    @Autowired
    CommonService commonService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void insertBehind(BehindDTO behindDTO) {
        behindMapper.insertBehind(behindDTO);
    }

    @Override
    public HashMap<String, Object> selectBehindList(Criteria cri) {
        HashMap<String, Object> json = new HashMap<>();
        List<BehindDTO> behindList = Collections.emptyList();

        // page 페이지 번호 = pageNum
        // cnt 페이지당 글 갯수 = amount
        int page = cri.getPage();

        //총 게시물 수
        int allCnt = behindMapper.selectBehindTotalCount(cri);

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

            behindList = behindMapper.selectBehindList(cri);

            json.put("message", "success");
            json.put("range",range);       //생성 가능한 총 페이지 수
            json.put("fill",cri.getFilter());
            json.put("keyword",cri.getKeyword());
            json.put("list", behindList); //요청한 게시물

            json.put("currPage", page);//현재 페이지
            return json;
        } catch (Exception e) {
            json.put("message", "fail");
            return json;
        }
    }

    public BehindDTO selectBehindDetail(int idx) {
        return behindMapper.selectBehindDetail(idx);
    }

    public void updateBehindDetail(BehindDTO behindDTO) {
        Date date = new Date();

        java.sql.Timestamp sqlDate = new java.sql.Timestamp(date.getTime());

        behindDTO.setSysmoddate(sqlDate);

        behindMapper.updateBehindDetail(behindDTO);
    }

    @Override
    public int getTotal(Criteria cri) {
        log.info("get total count");
        return behindMapper.selectBehindTotalCount(cri);
    }
}
