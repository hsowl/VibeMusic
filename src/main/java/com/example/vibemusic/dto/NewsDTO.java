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

    private Long nNo;
    private String nTitle;
    private String nContent;
    private String nImage;
    private String nFullNews;
    private int nViewCount;
    private String nRegDate;

}
