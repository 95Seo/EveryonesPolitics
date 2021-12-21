package com.hustar.edu.vote.api;

import com.hustar.edu.vote.auth.PrincipalDetail;
import com.hustar.edu.vote.dto.BoardDTO;
import com.hustar.edu.vote.dto.CommentDTO;
import com.hustar.edu.vote.dto.CommentLikeDTO;
import com.hustar.edu.vote.service.CommentService;
import com.sun.istack.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@RestController
public class CommentApiController {

    @Autowired
    CommentService commentService;
    
    // 모댓글 작성
    @RequestMapping(value="/picture_write_reply.do")
    public BoardDTO write_reply(@RequestParam int bno, @RequestParam String content, @RequestParam String boardNm, @AuthenticationPrincipal PrincipalDetail principal) {
        CommentDTO commentDTO = new CommentDTO();

        // 게시물 번호 세팅
        commentDTO.setBno(bno);

        // 댓글 내용 세팅
        commentDTO.setContent(content);

        commentDTO.setBoardNm(boardNm);

        // 댓글작성자 idx을 writer로 세팅
        commentDTO.setWriter(principal.getIdx());

        // +1된 댓글 갯수를 담아오기 위함
        BoardDTO pto = commentService.pictureWriteReply(commentDTO);

        return pto;
    }

    // 답글 작성
    @RequestMapping(value = "/picture_write_rereply.do")
    public CommentDTO write_rereply(@RequestParam int cno, @RequestParam int bno, @RequestParam String boardNm, @RequestParam String content,
                                  @AuthenticationPrincipal PrincipalDetail principal) {
        CommentDTO commentDTO = new CommentDTO();

        // 게시물 번호 세팅
        commentDTO.setBno(bno);

        // grp, grps, grpl은 ReplyDTO에 int로 정의되어 있기 때문에 String인 no를 int로 변환해서 넣어준다.
        // 모댓글 번호 no를 grp으로 세팅한다.
        commentDTO.setGrp(cno);

        commentDTO.setBoardNm(boardNm);

        System.out.println("cno: " + cno);

        // 답글은 깊이가 1이 되어야 하므로 grpl을 1로 세팅한다.
        commentDTO.setGrpl(1);

        // 답글 내용 세팅
        commentDTO.setContent(content);

        // 댓글작성자 idx을 writer로 세팅
        commentDTO.setWriter(principal.getIdx());

        // +1된 댓글 갯수를 담아오기 위함
        CommentDTO pto = commentService.pictureWriteReReply(commentDTO);

        return pto;
    }
    
    // 댓글 리스트
    @RequestMapping(value="/picture_allreplyList.do")
    public ArrayList<CommentDTO> all_reply_list(@RequestParam int bno, @RequestParam String boardNm, @AuthenticationPrincipal PrincipalDetail principal) {
        CommentDTO commentDTO = new CommentDTO();

        // 가져올 댓글 리스트의 게시물번호를 세팅
        commentDTO.setBno(bno);

        commentDTO.setBoardNm(boardNm);

        try{
            commentDTO.setWriter(principal.getIdx());
        } catch (Exception e) {
            e.printStackTrace();
            commentDTO.setWriter(0);
        }

        ArrayList<CommentDTO> replyList = new ArrayList<CommentDTO>();

        replyList = commentService.allreplyList(commentDTO);

        return replyList;
    }

    // 댓글 리스트
    @RequestMapping(value="/picture_replyList.do")
    public ArrayList<CommentDTO> reply_list(@RequestParam int bno, @RequestParam String boardNm, @AuthenticationPrincipal @Nullable PrincipalDetail principal) throws Exception {
        CommentDTO commentDTO = new CommentDTO();

        // 가져올 댓글 리스트의 게시물번호를 세팅
        commentDTO.setBno(bno);

        commentDTO.setBoardNm(boardNm);

        try{
            commentDTO.setWriter(principal.getIdx());
        } catch (Exception e) {
            e.printStackTrace();
            commentDTO.setWriter(0);
        }
        ArrayList<CommentDTO> replyList = new ArrayList<CommentDTO>();

        replyList = commentService.replyList(commentDTO);

        return replyList;
    }

    // 댓글 리스트
    @RequestMapping(value="/picture_rereplyList.do")
    public ArrayList<CommentDTO> rereply_list(@RequestParam int bno, @RequestParam String boardNm, @AuthenticationPrincipal @Nullable PrincipalDetail principal) throws Exception {
        CommentDTO commentDTO = new CommentDTO();

        // 가져올 댓글 리스트의 게시물번호를 세팅
        commentDTO.setBno(bno);

        commentDTO.setBoardNm(boardNm);

        try{
            commentDTO.setWriter(principal.getIdx());
        } catch (Exception e) {
            e.printStackTrace();
            commentDTO.setWriter(0);
        }
        ArrayList<CommentDTO> replyList = new ArrayList<CommentDTO>();

        replyList = commentService.rereplyList(commentDTO);

        return replyList;
    }

    // 댓글, 답글 수정
    @RequestMapping("/picture_replyUpdate.do")
    private int mCommentServiceUpdateProc(@RequestParam int cno, @RequestParam String boardNm, @RequestParam String content) throws Exception{

        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setCno(cno);
        commentDTO.setContent(content);

        commentDTO.setBoardNm(boardNm);

        return commentService.commentUpdateService(commentDTO);
    }

    // 모댓글 삭제
    @RequestMapping(value="/picture_delete_reply.do")
    public BoardDTO picture_delete_reply(@RequestParam int cno, @RequestParam String boardNm, @RequestParam int bno, @RequestParam int grp) {
        CommentDTO commentDTO = new CommentDTO();

        // 모댓글 번호 세팅
        commentDTO.setCno(cno);

        // 게시물 번호 세팅
        commentDTO.setBno(bno);

        commentDTO.setGrp(grp);

        commentDTO.setBoardNm(boardNm);

        // 갱신된 댓글 갯수를 담아오기 위함
        BoardDTO pto = commentService.pictureDeleteReply(commentDTO);

        return pto;
    }

    // 답글 삭제
    @RequestMapping(value = "/picture_delete_rereply.do")
    public CommentDTO delete_rereply(@RequestParam int cno, @RequestParam String boardNm, @RequestParam int bno, @RequestParam int grp) {
        CommentDTO commentDTO = new CommentDTO();

        // 답글 번호 세팅 - 답글 삭제하기 위해서 필요함
        commentDTO.setCno(cno);

        // 게시물 번호 세팅 - board의 reply+1하기 위해 필요함
        commentDTO.setBno(bno);

        commentDTO.setBoardNm(boardNm);

        // grp세팅(모댓글이 뭔지) - 모댓글은 삭제를 해도 답글이 있으면 남아있게 되는데 답글이 모두 삭제되었을 때 모댓글도 삭제하기 위해 필요함
        commentDTO.setGrp(grp);

        CommentDTO pto = commentService.pictureDeleteReReply(commentDTO);

        return pto;
    }

    // 답글 삭제
    @RequestMapping(value = "/reply_like_change.do")
    public CommentDTO changeLikeReply(@RequestParam String boardNm, @RequestParam int bno, @RequestParam int cno, @RequestParam String likeYn, @AuthenticationPrincipal @Nullable PrincipalDetail principal) {
        CommentLikeDTO commentLikeDTO = new CommentLikeDTO();

        // 답글 번호 세팅 - 답글 삭제하기 위해서 필요함
        commentLikeDTO.setBno(bno);

        // 게시물 번호 세팅 - board의 reply+1하기 위해 필요함
        commentLikeDTO.setBoardNm(boardNm);

        // 답글 번호 세팅 - 답글 삭제하기 위해서 필요함
        commentLikeDTO.setCno(cno);

        // 게시물 번호 세팅 - board의 reply+1하기 위해 필요함
        commentLikeDTO.setLikeYn(likeYn);

        try {
            commentLikeDTO.setWriter(principal.getIdx());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // 좋아요 있는지 검사
        // 없으면 인서트
        // 있으면 업데이트 후
        // 댓글 좋아요 갯수 구해서 출력
        CommentDTO pto = commentService.replyLikeChange(commentLikeDTO);

        return pto;
    }
}
