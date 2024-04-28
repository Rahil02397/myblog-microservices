package com.myblog.comment.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="comments")
@Entity
public class Comment {

    @Id
    private String commentId;
    private String name;
    private String email;
    private String body;
    private String postId;
}

