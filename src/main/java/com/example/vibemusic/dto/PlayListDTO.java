package com.example.vibemusic.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayListDTO {
    private Long pl_no;
    private String pl_name;
    private String pl_Recommend;

}
