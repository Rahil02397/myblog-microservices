package com.myblog.comment.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private String commentId;
    private String name;
    private String email;
    private String body;
    private String postId;

}
