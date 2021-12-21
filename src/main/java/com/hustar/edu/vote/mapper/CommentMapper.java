package com.hustar.edu.vote.mapper;

import com.hustar.edu.vote.dto.BoardDTO;
import com.hustar.edu.vote.dto.CommentDTO;
import com.hustar.edu.vote.dto.CommentLikeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface CommentMapper {

    // 댓글 쓰기
    public int picture_reply_write(CommentDTO commentDTO);

    // 모댓글일경우 no, grp 일치
    public void picture_reply_check(CommentDTO commentDTO);

    // 모댓글 삭제
    public int picture_reply_delete(CommentDTO commentDTO);

    // 답글 삭제
    public int picture_count_rereply_fromrereply(CommentDTO commentDTO);
    
    // 모댓글이 삭제된 댓글일때 그에 딸린 답글들이 모두 삭제되면 테이블에서 완전히 삭제
    public int picture_reply_delete_after_rereply_delete(CommentDTO commentDTO);

    // 답글 쓰기
    public int picture_rereply_write(CommentDTO commentDTO);

    // 댓글 갯수 증가
    public void picture_reply_up(BoardDTO boardDTO);

    // (좋아요, 유저 프로필, 댓글내용 까지 전부다)댓글 리스트 가져오기
    public ArrayList<CommentDTO> all_picutre_replyList(CommentDTO commentDTO);

    // 댓글 리스트 가져오기
    public ArrayList<CommentDTO> picutre_replyList(CommentDTO commentDTO);

    // 대댓글 리스트 가져오기
    public ArrayList<CommentDTO> picutre_rereplyList(CommentDTO commentDTO);

    // 모댓글 삭제 - 답글 없음
    public void picutre_reply_delete(CommentDTO commentDTO);

    // 모댓글 삭제 - 답글 있음
    public int picture_reply_not_delete(CommentDTO commentDTO);

    // 댓글 갯수 감소
    public void picture_reply_down(BoardDTO boardDTO);

    // 댓글 갯수 카운트
    public int picture_reply_count(CommentDTO commentDTO);

    // 모댓글에 달린 대댓글 갯수 카운트
    public int picture_count_rereply(CommentDTO commentDTO);

    public void updateTotalComment(CommentDTO commentDTO);

    public int getTotalComment(CommentDTO commentDTO);

    public void updateMaxGrps(CommentDTO commentDTO);

    public CommentDTO getMaxGrps(CommentDTO commentDTO);

//    // 댓글 개수
    public CommentDTO getComment(CommentDTO commentDTO);

    // 0. 좋아요 로그 카운트
    public int getLogLikeCnt(CommentLikeDTO commentLikeDTO);

    // 1. 좋아요 업데이트
    public void updateLike(CommentLikeDTO commentLikeDTO);

    // 2. 없으면 좋아요 인서트
    public void insertLike(CommentLikeDTO commentLikeDTO);

    // 3. 좋아요 갯수 카운트
    public int getLikeCnt(CommentLikeDTO commentLikeDTO);

    // 4. 좋아요 갯수 수정
    public void updateLikeCnt(CommentDTO commentDTO);
//
//    // 댓글 목록
//    public List<CommentDTO> commentList(CommentDTO comment) throws Exception;
//
//    // 댓글 작성
//    public int commentInsert(CommentDTO comment) throws Exception;

    // 댓글 수정
    public int commentUpdate(CommentDTO comment) throws Exception;
//
//    // 댓글 삭제
//    public int commentDelete(int cno) throws Exception;

}
