package com.hustar.edu.vote.service;

import com.hustar.edu.vote.dto.PromiseDTO;
import com.hustar.edu.vote.mapper.PromiseMapper;
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
public class PromiseServiceImpl implements PromiseService {
    @Autowired
    PromiseMapper pomiseMapper;

    @Autowired
    CommonService commonService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void insertPromise(PromiseDTO pomiseDTO) {
        pomiseMapper.insertPromise(pomiseDTO);
    }

    @Override
    public HashMap<String, Object> selectPromiseList(Criteria cri) {
        HashMap<String, Object> json = new HashMap<>();
        List<PromiseDTO> pomiseList = Collections.emptyList();

        // page 페이지 번호 = pageNum
        // cnt 페이지당 글 갯수 = amount
        int page = cri.getPage();

        //총 게시물 수
        int allCnt = pomiseMapper.selectPromiseTotalCount(cri);

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

            pomiseList = pomiseMapper.selectPromiseList(cri);

            json.put("message", "success");
            json.put("range",range);       //생성 가능한 총 페이지 수
            json.put("fill",cri.getFilter());
            json.put("keyword",cri.getKeyword());
            json.put("list", pomiseList); //요청한 게시물

            json.put("currPage", page);//현재 페이지
            return json;
        } catch (Exception e) {
            json.put("message", "fail");
            return json;
        }
    }

    public PromiseDTO selectPromiseDetail(int idx) {
        return pomiseMapper.selectPromiseDetail(idx);
    }

    public void updatePromiseDetail(PromiseDTO pomiseDTO) {
        Date date = new Date();

        java.sql.Timestamp sqlDate = new java.sql.Timestamp(date.getTime());

        pomiseDTO.setSysmoddate(sqlDate);

        pomiseMapper.updatePromiseDetail(pomiseDTO);
    }

    @Override
    public int getTotal(Criteria cri) {
        log.info("get total count");
        return pomiseMapper.selectPromiseTotalCount(cri);
    }
}
