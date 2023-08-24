package com.example.vibemusic.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List; // List를 사용하기 위해 추가

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PlayList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plNo")
    private Long plNo;

    private String plName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mid")
    private Member member; // User 엔터티와의 관계

    // fk키
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "playlist_music", // 중간 테이블 이름
            joinColumns = @JoinColumn(name = "plNo"), // PlayList와 연결된 컬럼
            inverseJoinColumns = @JoinColumn(name = "no") // Music과 연결된 컬럼
    )
    private List<Music> musics = new ArrayList<>(); // Music 엔터티와의 관계

    public List<Music> getMusics(){
        return musics;
    }

    public void setPlName(String plName) {
    }

    public void removeMusic(Music music) {
        musics.remove(music);
    }

}