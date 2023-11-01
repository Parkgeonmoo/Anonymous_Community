package com.example.anonymous_community.domain.comment.controller;

import com.example.anonymous_community.domain.comment.dto.request.CommentEntryRequest;
import com.example.anonymous_community.domain.comment.dto.request.CommentUpdateRequest;
import com.example.anonymous_community.domain.comment.dto.response.CommentEntryResponse;
import com.example.anonymous_community.domain.comment.dto.response.CommentUpdateResponse;
import com.example.anonymous_community.global.common.ApiResponse;
import com.example.anonymous_community.domain.comment.service.command.CommentDeleteService;
import com.example.anonymous_community.domain.comment.service.command.CommentUpdateService;
import com.example.anonymous_community.domain.comment.service.query.CommentListService;
import com.example.anonymous_community.global.exception.enums.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import com.example.anonymous_community.domain.comment.service.command.CommentEntryService;

import javax.validation.Valid;
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
     * @param param {@link CommentEntryRequest} 댓글 등록 요청 파라미터
     * @return {@link ResponseEntity}
     */
    @PostMapping("/comment")
    public ResponseEntity<ApiResponse> doPostAsComment(@Valid @RequestBody CommentEntryRequest param) {

        CommentEntryResponse commentEntryResponse = commentEntryService.entry(param);

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .statusCode(StatusCode.SUCCESS)
                        .httpCode(HttpStatus.OK.value())
                        .data(commentEntryResponse)
                        .build()
        );

    }

    /**
     * 댓글 조회
     *
     * @param articleIndex 게시글 고유번호
     * @return {@link ResponseEntity}
     */
    @GetMapping("/comment")
    public ResponseEntity doGetAsComment(String articleIndex) {

        final List<CommentEntryRequest> result = commentListService.getList(articleIndex);


        return ResponseEntity.ok(
                ApiResponse.builder()
                        .statusCode(StatusCode.SUCCESS)
                        .httpCode(HttpStatus.OK.value())
                        .data("")
                        .build()
        );
    }

    /**
     * 댓글 수정 요청
     *
     * @param commentUpdateRequest {@link CommentEntryRequest} 댓글 수정 요청 파라미터
     * @return {@link ResponseEntity}
     */


    @PutMapping("/comment")
    public ResponseEntity<ApiResponse> doPutAsComment(@Valid @RequestBody CommentUpdateRequest commentUpdateRequest) {

        final CommentUpdateResponse commentUpdateResponse = commentUpdateService.putCommentService(commentUpdateRequest);

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .statusCode(StatusCode.SUCCESS)
                        .httpCode(HttpStatus.OK.value())
                        .data(commentUpdateResponse)
                        .build()
        );


    }


    /**
     * 댓글 삭제 요청
     *
     * @param param {@link CommentEntryRequest} 댓글 삭제 요청 파라미터
     * @return {@link ResponseEntity}
     *
     */

    /**
    @DeleteMapping("/comment")
    public ResponseEntity doDeleteAsComment(@RequestBody CommentEntryRequest param) {

        commentDeleteService.delete(param);

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .statusCode(StatusCode.SUCCESS)
                        .httpCode(HttpStatus.OK.value())
                        .data("")
                        .build()
        );
    }
    **/
}
