package ru.levelp.at.homework6.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class UserResp {

    private Integer id;

    private String name;

    private String email;

    private String gender;

    private String status;

    private String userId;

}



