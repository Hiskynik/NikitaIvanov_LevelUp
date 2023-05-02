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
public class PostReq {
    private Integer user_id;
    private String title;
    private String body;



}
