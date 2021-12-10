package com.hustar.edu.vote.api;

import com.hustar.edu.vote.auth.PrincipalDetail;
import com.hustar.edu.vote.dto.BoardDTO;
import com.hustar.edu.vote.dto.ResponseDto;
import com.hustar.edu.vote.paging.Criteria;
import com.hustar.edu.vote.service.PromiseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PromiseApiController {

    @Autowired
    private PromiseServiceImpl promiseServiceimpl;

    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody BoardDTO board,
                                     @AuthenticationPrincipal PrincipalDetail principal, Criteria cri) {
        board.setWriter_idx(principal.getIdx());
        System.out.println("Content: " + board.getContent());
        promiseServiceimpl.insertPromiseBoard(board);
        promiseServiceimpl.getPromiseBoardList(cri);
        promiseServiceimpl.getPromiseBoardDetail(board);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }
}
