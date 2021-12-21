package com.hustar.edu.vote.service;

import com.hustar.edu.vote.dto.BoardDTO;
import com.hustar.edu.vote.dto.CommentDTO;
import com.hustar.edu.vote.dto.CommentLikeDTO;

import java.util.ArrayList;
import java.util.List;

public interface CommentService {
//    public List<CommentDTO> commentListService(CommentDTO comment) throws Exception;
//    public int commentInsertService(CommentDTO comment) throws Exception;
    public int commentUpdateService(CommentDTO comment) throws Exception;
//    public int commentDeleteService(int cno) throws Exception;
    public BoardDTO pictureWriteReply(CommentDTO commentDTO);

    // 답글 작성
    public CommentDTO pictureWriteReReply(CommentDTO commentDTO);

    // (좋아요, 유저 프로필, 댓글내용 까지 전부다)댓글 리스트 가져오기
    public ArrayList<CommentDTO> allreplyList(CommentDTO commentDTO);

    // 댓글 리스트
    public ArrayList<CommentDTO> replyList(CommentDTO commentDTO);

    // 대댓글 리스트
    public ArrayList<CommentDTO> rereplyList(CommentDTO commentDTO);

    // 모댓글 삭제
    public BoardDTO pictureDeleteReply(CommentDTO commentDTO);

    // 답글 삭제
    public CommentDTO pictureDeleteReReply(CommentDTO commentDTO);

    public CommentDTO replyLikeChange(CommentLikeDTO commentLikeDTO);

    // 댓글 작성
    public BoardDTO profile_pictureWriteReply(CommentDTO commentDTO);
}
