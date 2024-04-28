package com.myblog.comment.Controller;

import com.myblog.comment.payload.CommentDto;
import com.myblog.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // http://localhost:8082/api/comments
    @PostMapping
    public ResponseEntity<CommentDto> saveComment(@RequestBody CommentDto commentDto){
        CommentDto dto = commentService.saveComment(commentDto);
        return  new ResponseEntity<>(dto, HttpStatus.OK);
    }

    //http://localhost:8082/api/comments/15895477-be1a-4a97-88b3-cca67b21938c
    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentDto>> getCommentByPostId(@PathVariable String postId){
        List<CommentDto> comment = commentService.getCommentByPostId(postId);
        return new ResponseEntity<>(comment,HttpStatus.OK);

    }




}
