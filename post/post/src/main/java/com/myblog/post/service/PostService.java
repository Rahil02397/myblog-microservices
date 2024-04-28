package com.myblog.post.service;

import com.myblog.post.entity.Post;
import com.myblog.post.payload.PostDto;

public interface PostService {
    PostDto savePost(PostDto postDto);

    PostDto findPostByPostId(String postId);

    PostDto getCommentsWithPost(String postId);
}
