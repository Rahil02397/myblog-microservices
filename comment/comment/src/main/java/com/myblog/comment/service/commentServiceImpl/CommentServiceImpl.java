package com.myblog.comment.service.commentServiceImpl;

import com.myblog.comment.Config.RestTemplateConfig;
import com.myblog.comment.entity.Comment;
import com.myblog.comment.payload.CommentDto;
import com.myblog.comment.payload.Post;
import com.myblog.comment.repository.CommentRepository;
import com.myblog.comment.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private RestTemplateConfig restTemplate;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public CommentDto saveComment(CommentDto commentDto) {
        Post post = restTemplate.getRestTemplate().getForObject("http://POST-SERVICE/api/posts/" + commentDto.getPostId(), Post.class);
        if (post != null) {
            Comment comment = modelMapper.map(commentDto, Comment.class);
            String commentId = UUID.randomUUID().toString();
            comment.setCommentId(commentId);
            Comment savedComment = commentRepository.save(comment);
            CommentDto dto = modelMapper.map(savedComment, CommentDto.class);
            return dto;
        } else {
            return null;
        }
    }

    @Override
    public List<CommentDto> getCommentByPostId(String postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        List<CommentDto> commentDtos = comments.stream()
                .map(comment -> modelMapper.map(comment, CommentDto.class))
                .collect(Collectors.toList());
        return commentDtos;
    }
}
