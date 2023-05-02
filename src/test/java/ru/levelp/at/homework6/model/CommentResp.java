package ru.levelp.at.homework6.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CommentResp {

    private Integer id;
    private Integer post_id;
    private String name;
    private String email;
    private String body;

}
