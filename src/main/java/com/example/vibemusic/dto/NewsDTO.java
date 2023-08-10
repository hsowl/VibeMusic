package com.example.vibemusic.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class NewsDTO {

    private Long n_no;
    private String n_title;
    private String n_article;
    private String n_image;
    private int n_viewCount;
    private Date n_regDate;

}
