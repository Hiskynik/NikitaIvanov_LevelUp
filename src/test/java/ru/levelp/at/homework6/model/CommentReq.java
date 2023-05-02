package ru.levelp.at.homework6.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CommentReq {
    private Integer post_id;
    private String email;
    private String name;
    private String body;



}
