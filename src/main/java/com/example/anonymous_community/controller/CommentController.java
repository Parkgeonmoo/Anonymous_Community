package com.example.anonymous_community.controller;


import com.example.anonymous_community.dto.CommentRequest;
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


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentEntryService commentEntryService;
    private final CommentListService commentListService;
    private final CommentUpdateService commentUpdateService;
    private final CommentDeleteService commentDeleteService;


    @PostMapping("/comment")
    public ResponseEntity doPostAsComment(@RequestBody CommentRequest inputCommentRequest) {
        try {
            commentEntryService.postCommentService(inputCommentRequest);
            return ResponseEntity.ok().build();
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }




    @GetMapping("/comment")
    public ResponseEntity doGetAsComment(String articleIndex) {
        List<CommentRequest> result = commentListService.getCommentService(articleIndex);
        if (result != null) {
            return ResponseEntity.ok(result);
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }



    @PutMapping("/comment")
    public ResponseEntity doPutAsComment(@RequestBody CommentRequest inputCommentRequest) {
        try {
            commentUpdateService.putCommentService(inputCommentRequest);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @DeleteMapping("/comment")
    public ResponseEntity doDeleteAsComment(@RequestBody CommentRequest inputCommentRequest) {
        try {
            commentDeleteService.deleteCommentService(inputCommentRequest);
            return ResponseEntity.ok().build();
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

}
