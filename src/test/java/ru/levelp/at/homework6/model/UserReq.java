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
public class UserReq {
    private String name;
    private String email;
    private String gender;
    private String status;

}
