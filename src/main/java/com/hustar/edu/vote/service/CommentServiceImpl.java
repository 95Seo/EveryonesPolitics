package com.hustar.edu.vote.service;

import com.hustar.edu.vote.dto.CommentVO;
import com.hustar.edu.vote.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    CommentMapper mCommentMapper;

    @Override
    public List<CommentVO> commentListService() throws Exception {
        return mCommentMapper.commentList();
    }

    @Override
    public int commentInsertService(CommentVO comment) throws Exception {
        return mCommentMapper.commentInsert(comment);
    }

    @Override
    public int commentUpdateService(CommentVO comment) throws Exception {
        return mCommentMapper.commentUpdate(comment);
    }

    @Override
    public int commentDeleteService(int cno) throws Exception {
        return mCommentMapper.commentDelete(cno);
    }
}
