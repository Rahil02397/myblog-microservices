package com.myblog.post.payload;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;


import javax.xml.stream.events.Comment;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    private String id;
    private String title;
    private String description;
    private String content;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Comment> comment;

}
