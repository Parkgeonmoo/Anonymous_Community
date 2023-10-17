package com.example.anonymous_community.controller;

import com.example.anonymous_community.dto.request.CommentRequest;
import com.example.anonymous_community.dto.response.ApiResponse;
import com.example.anonymous_community.service.comment.command.CommentDeleteService;
import com.example.anonymous_community.service.comment.command.CommentUpdateService;
import com.example.anonymous_community.service.comment.query.CommentListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import com.example.anonymous_community.service.comment.command.CommentEntryService;

import java.util.List;

/**
 * 댓글 controller
 *
 * @author parkgeonwoo
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentEntryService commentEntryService;
    private final CommentListService commentListService;
    private final CommentUpdateService commentUpdateService;
    private final CommentDeleteService commentDeleteService;

    /**
     * 댓글 등록 요청
     *
     * @param param {@link CommentRequest} 댓글 등록 요청 파라미터
     * @return {@link ResponseEntity}
     */
    @PostMapping("/comment")
    public ResponseEntity doPostAsComment(@RequestBody CommentRequest param) {
        try {
            commentEntryService.entry(param);
            return ResponseEntity.ok().build();
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "댓글 등록에 실패했습니다.", null));
        }
    }

    /**
     * 댓글 조회
     *
     * @param articleIndex 게시글 고유번호
     * @return {@link ResponseEntity}
     */
    @GetMapping("/comment")
    public ResponseEntity doGetAsComment(String articleIndex) {
        try {
            final List<CommentRequest> result = commentListService.getList(articleIndex);
            if (result != null) {
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "fail", null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "댓글 조회에 실패했습니다.", null));
        }
    }

    /**
     * 댓글 수정 요청
     *
     * @param param {@link CommentRequest} 댓글 수정 요청 파라미터
     * @return {@link ResponseEntity}
     */
    @PutMapping("/comment")
    public ResponseEntity doPutAsComment(@RequestBody CommentRequest param) {
        try {
            commentUpdateService.putCommentService(param);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "댓글 수정에 실패했습니다.", null));
        }
    }

    /**
     * 댓글 삭제 요청
     *
     * @param param {@link CommentRequest} 댓글 삭제 요청 파라미터
     * @return {@link ResponseEntity}
     *
     */
    @DeleteMapping("/comment")
    public ResponseEntity doDeleteAsComment(@RequestBody CommentRequest param) {
        try {
            commentDeleteService.delete(param);
            return ResponseEntity.ok().build();
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "댓글 삭제에 실패했습니다.", null));
        }
    }
}
