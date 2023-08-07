package com.example.vibemusic.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyDTO {
    private Long r_no;
    private String r_replyer;
    private String r_replyText;
    private Date r_regDate;
    private Date r_modDate;
    // 필요한 경우 다른 관계 필드도 추가
}
