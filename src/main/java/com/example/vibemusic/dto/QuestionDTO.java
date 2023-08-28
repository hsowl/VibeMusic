package com.example.vibemusic.dto;


import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class QuestionDTO {

    private Long qNo;
    @NotEmpty
    @Size(min = 3, max = 100)
    private String qTitle;
    @NotEmpty
    private String qContent;

    private String qImage;
    @NotEmpty
    private String qWriter;

    private int qViewCount;

    private String qRegDate;
}
