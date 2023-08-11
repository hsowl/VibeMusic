package com.example.vibemusic.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyDTO {
    private Long rno;

    @NotEmpty
    private String rreplyer;

    @NotEmpty
    private String rreplyText;

    private LocalDateTime regDate;

    private LocalDateTime modDate;

    @NotNull
    private Long mno;
    // 필요한 경우 다른 관계 필드도 추가
}
