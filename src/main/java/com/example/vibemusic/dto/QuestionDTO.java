package com.example.vibemusic.dto;


import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class QuestionDTO {

    private Long qNo;

    private String qTitle;

    private String qContent;

    private String qImage;

    private String qWriter;

    private int qViewCount;

    private String qRegDate;
}
