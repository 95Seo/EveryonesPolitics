package com.hustar.edu.vote.service;

import com.hustar.edu.vote.dto.CommentVO;

import java.util.List;

public interface CommentService {
    public List<CommentVO> commentListService() throws Exception;
    public int commentInsertService(CommentVO comment) throws Exception;
    public int commentUpdateService(CommentVO comment) throws Exception;
    public int commentDeleteService(int cno) throws Exception;
}
