package com.example.vibemusic.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Music {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long no;

    private String m_sound;

    @Column
    private String m_title;

    @Column
    private String m_artist;

    @Column
    private String m_genre;

    @Column
    private int m_rDate;

    @Column
    private String m_playtime;

    @Column
    private String m_image;

    @Column
    private int m_playCount;

    @Column
    private String m_Recommend;

    @OneToMany(mappedBy = "music", cascade = CascadeType.ALL)
    private List<Reply> replies;

    @ManyToMany(mappedBy = "music")
    private List<PlayList> playLists;

    public void updateImgUrl(String m_image){
        this.m_image = m_image;
    }
}
