package com.myblog.post.controller;

import com.myblog.post.entity.Post;
import com.myblog.post.payload.PostDto;
import com.myblog.post.service.PostService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    // http://localhost:8081/api/posts
    @PostMapping
    public ResponseEntity<PostDto> savePost(
            @RequestBody PostDto postDto
    ){
        PostDto dto = postService.savePost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    // http://localhost:8081/api/posts/1
    @GetMapping("/{postId}")
    public ResponseEntity<?> getPostByPostId(
            @PathVariable String postId
    ){
        PostDto dto = postService.findPostByPostId(postId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // http://localhost:8081/api/posts/1/comments
    @GetMapping("/{postId}/comments")
    @CircuitBreaker(name= "commentBreaker", fallbackMethod = "commentFallBack")
    public ResponseEntity<PostDto> getCommentsWithPost(@PathVariable String postId){
        PostDto commentsWithPost = postService.getCommentsWithPost(postId);
        return new ResponseEntity<>(commentsWithPost, HttpStatus.OK);
    }

    public ResponseEntity<PostDto> commentFallBack(String postId, Exception ex){
        System.out.println("Fallback is executed because service is down :"+ ex.getMessage());

        ex.printStackTrace();

        PostDto dto= new PostDto();
        dto.setId("1234");
        dto.setTitle("Service Down");
        dto.setContent("Service Down");
        dto.setDescription("Service Down");

        return new ResponseEntity<>(dto,HttpStatus.BAD_REQUEST);
    }
}
