package com.example.vibemusic.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MusicDTO {
    private Long no;
    private String m_sound;
    private String m_title;
    private String m_artist;
    private String mgenre;
    private int rDate;
    private String m_playtime;
    private String m_image;
    private int m_playCount;
    private String m_Recommend;

}
