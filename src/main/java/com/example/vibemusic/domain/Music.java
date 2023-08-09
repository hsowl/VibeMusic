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
    @Column(name = "m_no")
    private Long m_no;

    @Column(name = "m_sound")
    private String m_sound;

    @Column(name = "m_title")
    private String m_title;

    @Column(name = "m_artist")
    private String m_artist;

    @Column(name = "m_genre")
    private String m_genre;

    @Column(name = "m_rDate")
    private int m_rDate;

    @Column(name = "m_playtime")
    private String m_playtime;

    @Column(name = "m_image")
    private String m_image;

    @Column(name = "m_playCount")
    private int m_playCount;

    @Column(name = "m_Recommend")
    private String m_Recommend;

    @OneToMany(mappedBy = "music", cascade = CascadeType.ALL)
    private List<Reply> replies;

    @ManyToMany(mappedBy = "music")
    private List<PlayList> playLists;

    public void updateImgUrl(String m_image){
        this.m_image = m_image;
    }
}
