package com.example.anonymous_community.controller;


import com.example.anonymous_community.dto.Comment;
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
import com.example.anonymous_community.service.CommentEntryService;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentEntryService commentEntryService;


    @PostMapping("/comment")
    public ResponseEntity doPostAsComment(@RequestBody Comment inputComment) {

        Comment result = commentEntryService.postCommentService(inputComment);

        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }




    @GetMapping("/comment")
    public ResponseEntity doGetAsComment(String articleIndex) {
        List<Comment> result = commentEntryService.getCommentService(articleIndex);

        if (result != null) {
            return ResponseEntity.ok(result);
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }



    @PutMapping("/comment")
    public ResponseEntity doPutAsComment(@RequestBody Comment inputComment) {
        Comment result = commentEntryService.putCommentService(inputComment);
        if (result != null) {
            return ResponseEntity.ok(result);
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



    @DeleteMapping("/comment")
    public ResponseEntity doDeleteAsComment(@RequestBody Comment inputComment) {
        Comment result = commentEntryService.deleteCommentService(inputComment);
        if (result != null) {
            return ResponseEntity.ok(result);
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

}
