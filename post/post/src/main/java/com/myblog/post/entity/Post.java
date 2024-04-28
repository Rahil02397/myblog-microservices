package com.myblog.post.entity;
import javax.persistence.*;
import lombok.*;

import javax.xml.stream.events.Comment;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="posts")
@Entity
public class Post {

    @Id
    private String id;
    private String title;
    private String description;
    private String content;
}
