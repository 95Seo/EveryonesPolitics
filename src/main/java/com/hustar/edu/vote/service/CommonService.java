package com.hustar.edu.vote.service;

import com.hustar.edu.vote.dto.LikeDTO;
import com.hustar.edu.vote.mapper.CommonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class CommonService {

    @Autowired
    CommonMapper mapper;

    /**
     조회수 중복 방지
     **/
    public void viewCountUp(String boardNm, int id, HttpServletRequest request, HttpServletResponse response) {
        Long idx = Long.valueOf(id);
        Cookie oldCookie = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("postView")) {
                    oldCookie = cookie;
                }
            }
        }

        if (oldCookie != null) {
            if (!oldCookie.getValue().contains(boardNm + "_[" + idx.toString() + "]")) {
                mapper.viewCountUp(boardNm, idx);
                oldCookie.setValue(oldCookie.getValue() + "_" + boardNm + "_[" + idx + "]");
                oldCookie.setPath("/");
                oldCookie.setMaxAge(60 * 60 * 24);
                response.addCookie(oldCookie);
            }
        } else {
            mapper.viewCountUp(boardNm, idx);
            Cookie newCookie = new Cookie("postView",boardNm + "_[" + idx + "]");
            newCookie.setPath("/");
            newCookie.setMaxAge(60 * 60 * 24);
            response.addCookie(newCookie);
        }
    }

    public LikeDTO getLike(LikeDTO likeDTO) {
        return mapper.getLike(likeDTO);
    }

    public void insertLike(LikeDTO likeDTO) {
        mapper.insertLike(likeDTO);
    }

    public void updateLike(LikeDTO likeDTO) {
        mapper.updateLike(likeDTO);
    }
    public int getLikeCount(LikeDTO likeDTO) {
        return mapper.getLikeCount(likeDTO);
    }

    public void updateLikeCount(LikeDTO likeDTO) {
        mapper.updateLikeCount(likeDTO);
    }
}
