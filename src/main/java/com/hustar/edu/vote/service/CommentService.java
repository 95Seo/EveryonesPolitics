package com.hustar.edu.vote.service;

import com.hustar.edu.vote.dto.CommentDTO;

import java.util.List;

public interface CommentService {
    public List<CommentDTO> commentListService(CommentDTO comment) throws Exception;
    public int commentInsertService(CommentDTO comment) throws Exception;
    public int commentUpdateService(CommentDTO comment) throws Exception;
    public int commentDeleteService(int cno) throws Exception;
}
