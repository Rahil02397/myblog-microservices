package com.myblog.post.service.postServiceImpl;

import com.myblog.post.config.RestTemplateConfig;
import com.myblog.post.entity.Post;
import com.myblog.post.payload.PostDto;
import com.myblog.post.repository.PostRepository;
import com.myblog.post.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RestTemplateConfig restTemplate;

    @Override
    public PostDto savePost(PostDto postDto) {
        Post post = mapToEntity(postDto);
        String postId = UUID.randomUUID().toString();
        post.setId(postId);
        Post savedPost = postRepository.save(post);
        PostDto dto = mapToDto(post);
        return dto;
    }

    @Override
    public PostDto findPostByPostId(String postId) {
        Post post = postRepository.findById(postId).get();
        PostDto dto = mapToDto(post);
        return dto;
    }

    @Override
    public PostDto getCommentsWithPost(String postId) {
        Post post = postRepository.findById(postId).get();
        ArrayList comment = restTemplate.getRestTemplate().getForObject("http://COMMENT-SERVICE/api/comments/" + postId, ArrayList.class);
        PostDto dto = mapToDto(post);
        dto.setComment(comment);
        return dto;
    }


    Post mapToEntity(PostDto postDto){
        Post post = modelMapper.map(postDto, Post.class);
        return post;
    }

    PostDto mapToDto(Post post){
        PostDto dto = modelMapper.map(post, PostDto.class);
         return dto;
    }
}

