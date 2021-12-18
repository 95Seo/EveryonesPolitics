package com.hustar.edu.vote.mapper;

import com.hustar.edu.vote.dto.CommentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    // 댓글 개수
    public int commentCount() throws Exception;

    // 댓글 목록
    public List<CommentDTO> commentList(CommentDTO comment) throws Exception;

    // 댓글 작성
    public int commentInsert(CommentDTO comment) throws Exception;

    // 댓글 수정
    public int commentUpdate(CommentDTO comment) throws Exception;

    // 댓글 삭제
    public int commentDelete(int cno) throws Exception;


}
