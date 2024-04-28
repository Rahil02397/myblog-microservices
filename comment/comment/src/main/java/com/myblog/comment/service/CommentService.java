package com.myblog.comment.service;

import com.myblog.comment.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto saveComment(CommentDto commentDto);

    List<CommentDto> getCommentByPostId(String postId);

}
