package com.hustar.edu.vote.service;

import com.hustar.edu.vote.dto.CommentDTO;
import com.hustar.edu.vote.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    CommentMapper mCommentMapper;

    @Override
    public List<CommentDTO> commentListService(CommentDTO comment) throws Exception {
        return mCommentMapper.commentList(comment);
    }

    @Override
    public int commentInsertService(CommentDTO comment) throws Exception {
        return mCommentMapper.commentInsert(comment);
    }

    @Override
    public int commentUpdateService(CommentDTO comment) throws Exception {
        return mCommentMapper.commentUpdate(comment);
    }

    @Override
    public int commentDeleteService(int cno) throws Exception {
        return mCommentMapper.commentDelete(cno);
    }
}
