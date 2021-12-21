package com.hustar.edu.vote.service;

import com.hustar.edu.vote.auth.PrincipalDetail;
import com.hustar.edu.vote.dto.BoardDTO;
import com.hustar.edu.vote.dto.CommentDTO;
import com.hustar.edu.vote.dto.CommentLikeDTO;
import com.hustar.edu.vote.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    CommentMapper mCommentMapper;

    // 댓글 작성
    public BoardDTO pictureWriteReply(CommentDTO commentDTO) {
        // board 테이블에 해당 게시물의 comment수를 +1 하기위한 commentDTO 세팅
        BoardDTO pto = new BoardDTO();
        pto.setIdx(commentDTO.getBno());

        // 댓글 입력
        int result = mCommentMapper.picture_reply_write(commentDTO);

        commentDTO.setGrp(commentDTO.getCno());

        // cno와 grp가 다르다면 grp를 cno로 업데이트
        // 먼저 grp가 0인상태로 입력후 cno와 같은 grp로 수정
        mCommentMapper.picture_reply_check(commentDTO);

        commentDTO.setCommentCnt(mCommentMapper.getTotalComment(commentDTO));

        mCommentMapper.updateTotalComment(commentDTO);

        if(result == 1) {       // tb_comment 테이블에 새로운 댓글 추가가 성공한다면..
           // 갱신된 댓글 갯수를 가져옴
            pto.setCommentCnt(mCommentMapper.picture_reply_count(commentDTO));
        }
        return pto;
    }

    // 답글 작성
    public CommentDTO pictureWriteReReply(CommentDTO commentDTO) {
        // tb_board 테이블에 해당 게시물의 comment수를 +1 하기위한 commentDTO세팅

        CommentDTO pto = new CommentDTO();

        // grp가 reply의 cno와 일치하는 댓글이 몇갠지 카운트한다. 모댓글에 딸린 답글이 몇갠지 카운트하기 위함
        int count_rereply = mCommentMapper.picture_count_rereply(commentDTO);

        commentDTO.setGrps(count_rereply + 1);

        // tb_comment 테이블에 추가 (댓글 작성과 동일)
        int result = mCommentMapper.picture_rereply_write(commentDTO);

        commentDTO.setGrpsCnt(commentDTO.getGrps());

        mCommentMapper.updateMaxGrps(commentDTO);

        commentDTO.setCommentCnt(mCommentMapper.getTotalComment(commentDTO));

        mCommentMapper.updateTotalComment(commentDTO);

        if(result == 1) {       // tb_comment 테이블에 새로운 댓글 추가가 성공한다면..
            // 갱신된 댓글 갯수를 가져옴
            pto = mCommentMapper.getComment(commentDTO);
        }
        return pto;
    }

    // 댓글 리스트
    public ArrayList<CommentDTO> allreplyList(CommentDTO commentDTO) {
        ArrayList<CommentDTO> allreplyList = new ArrayList<CommentDTO>();
        allreplyList = mCommentMapper.all_picutre_replyList(commentDTO);
        return allreplyList;
    }

    // 댓글 리스트
    public ArrayList<CommentDTO> replyList(CommentDTO commentDTO) {
        ArrayList<CommentDTO> replyList = new ArrayList<CommentDTO>();
        replyList = mCommentMapper.picutre_replyList(commentDTO);
        return replyList;
    }

    // 댓글 리스트
    public ArrayList<CommentDTO> rereplyList(CommentDTO commentDTO) {
        ArrayList<CommentDTO> rereplyList = new ArrayList<CommentDTO>();
        rereplyList = mCommentMapper.picutre_rereplyList(commentDTO);
        return rereplyList;
    }

    // 모댓글 삭제
    public BoardDTO pictureDeleteReply(CommentDTO commentDTO) {
        // tb_board 테이블에 해당 게시물의 comment수를 -1 하기위한 comment세팅
        BoardDTO pto = new BoardDTO();
        pto.setIdx(commentDTO.getBno());

        int result = 0;

        result = mCommentMapper.picture_reply_delete(commentDTO);

        // grp가 reply의 cno와 일치하는 댓글이 몇갠지 카운트한다. 모댓글에 딸린 답글이 몇갠지 카운트하기 위함
        int count_rereply = mCommentMapper.picture_count_rereply_fromrereply(commentDTO);

        System.out.println("count_rereply = " + count_rereply);

        if(count_rereply == 0) {    // 답글이 없을 때 - 실제 삭제
            // real_delete = Y

            System.out.println("real_delete_grp = " + commentDTO.getGrp());

            result = mCommentMapper.picture_reply_delete_after_rereply_delete(commentDTO);
        }

        if(result == 1) {   // tb_comment 테이블에서 댓글 삭제가 성공한다면...
            // 갱신된 댓글 갯수를 가져옴
            pto.setCommentCnt(mCommentMapper.picture_reply_count(commentDTO));
        }

        commentDTO.setCommentCnt(mCommentMapper.getTotalComment(commentDTO));

        mCommentMapper.updateTotalComment(commentDTO);

        return pto;
    }

    // 답글 삭제
    public CommentDTO pictureDeleteReReply(CommentDTO commentDTO) {
        // tb_board 테이블에 해당 게시뭏의 reply수를 -1 하기위한 comment 세팅
        CommentDTO pto = new CommentDTO();

        System.out.println("commentDTO : "+commentDTO.getCno());

        // tb_comment 테이블에서 삭제
        int result = mCommentMapper.picture_reply_delete(commentDTO);

        // grp가 일치하는 답글이 몇갠지 카운트 한다. 없으면 모댓글을 삭제하기 위함.
        int count_rereply = mCommentMapper.picture_count_rereply_fromrereply(commentDTO);

        System.out.println("count_rereply = " + count_rereply);
        if(count_rereply == 0) {
            mCommentMapper.picture_reply_delete_after_rereply_delete(commentDTO);
        }

        if(result == 1) {   // tb_comment 테이블에서 댓글 삭제가 성공한다면...
            // 갱신된 댓글 갯수를 가져옴
            pto = mCommentMapper.getComment(commentDTO);
        }

        commentDTO.setCommentCnt(mCommentMapper.getTotalComment(commentDTO));

        mCommentMapper.updateTotalComment(commentDTO);


        return pto;
    }

    // 댓글 작성
    public BoardDTO profile_pictureWriteReply(CommentDTO commentDTO) {
        // tb_board 테이블에 해당 게시물의 comment수를 +1 하기 위한 comment세팅
        BoardDTO pto = new BoardDTO();
        pto.setIdx(commentDTO.getBno());

        int result = mCommentMapper.picture_reply_write(commentDTO);

        commentDTO.setGrp(commentDTO.getCno());

        commentDTO.setCommentCnt(mCommentMapper.getTotalComment(commentDTO));

        mCommentMapper.updateTotalComment(commentDTO);

        // cno와 grp가 다르면 grp를 cno로 업데이트
        if(result == 1) {       // tb_comment 테이블에 새로운 댓글 추가가 성공한다면...
            // 갱신된 댓글 갯수를 가져옴
            pto.setCommentCnt(mCommentMapper.picture_reply_count(commentDTO));
        }
        return pto;
    }

    //    @Override
//    public List<CommentDTO> commentListService(CommentDTO comment) throws Exception {
//        return mCommentMapper.commentList(comment);
//    }
//
//    @Override
//    public int commentInsertService(CommentDTO comment) throws Exception {
//        return mCommentMapper.commentInsert(comment);
//    }
//
    @Override
    public int commentUpdateService(CommentDTO comment) throws Exception {
        return mCommentMapper.commentUpdate(comment);
    }

    public CommentDTO replyLikeChange(CommentLikeDTO commentLikeDTO) {

        CommentDTO pto = new CommentDTO();

        // 0. 좋아요 기록 있는지 검사 (로그 검사)
        // 1. 없으면 인서트
        // 2. 있으면 업데이트 후
        // 3. 댓글 좋아요 갯수 구해서 출력
        if(mCommentMapper.getLogLikeCnt(commentLikeDTO) != 0){
            mCommentMapper.updateLike(commentLikeDTO);
        } else {
            mCommentMapper.insertLike(commentLikeDTO);
        }

        pto.setBoardNm(commentLikeDTO.getBoardNm());
        pto.setBno(commentLikeDTO.getBno());
        pto.setCno(commentLikeDTO.getCno());
        pto.setLikeCnt(mCommentMapper.getLikeCnt(commentLikeDTO));

        mCommentMapper.updateLikeCnt(pto);

        return pto;
    }
//
//    @Override
//    public int commentDeleteService(int cno) throws Exception {
//        return mCommentMapper.commentDelete(cno);
//    }
}
