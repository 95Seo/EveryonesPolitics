package com.hustar.edu.vote.controller;

import com.hustar.edu.vote.auth.PrincipalDetail;
import com.hustar.edu.vote.dto.CommentDTO;
import com.hustar.edu.vote.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService mCommentService;

//    @RequestMapping("/list") //댓글 리스트
//    @ResponseBody
//    private List<CommentDTO> mCommentServiceList(Model model, CommentDTO commentDTO) throws Exception{
//
//        System.out.println("댓글 리스트");
//
//        System.out.println("bno: "+commentDTO.getBno());
//
//        System.out.println("boardNm: "+commentDTO.getBoardNm());
//
//        return mCommentService.replyList(commentDTO);
//    }
//
//    // 로그인 예외 처리 해주세요. 신책임님
//    @RequestMapping("/insert") //댓글 작성
//    @ResponseBody
//    private int mCommentServiceInsert(@AuthenticationPrincipal PrincipalDetail principal, CommentDTO commentDTO) throws Exception{
//
//        //로그인 기능을 구현했거나 따로 댓글 작성자를 입력받는 폼이 있다면 입력 받아온 값으로 사용하면 됩니다. 저는 따로 폼을 구현하지 않았기때문에 임시로 "test"라는 값을 입력해놨습니다.
//        commentDTO.setWriter(principal.getIdx());
//
//        return mCommentService.pictureWriteReply(commentDTO);
//    }
//
//    @RequestMapping("/update") //댓글 수정
//    @ResponseBody
//    private int mCommentServiceUpdateProc(@RequestParam int cno, @RequestParam String content) throws Exception{
//
//        CommentDTO comment = new CommentDTO();
//        comment.setCno(cno);
//        comment.setContent(content);
//
//        return mCommentService.(comment);
//    }
//
//    @RequestMapping("/delete/{cno}") //댓글 삭제
//    @ResponseBody
//    private int mCommentServiceDelete(@PathVariable int cno) throws Exception{
//
//        return mCommentService.pictureDeleteReply(cno);
//    }

}
