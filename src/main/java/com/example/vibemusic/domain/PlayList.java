package com.example.vibemusic.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List; // List를 사용하기 위해 추가

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PlayList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pl_no")
    private Long pl_no;

    private String pl_name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "u_no")
    private User user; // User 엔터티와의 관계

    // fk키
    @ManyToMany
    @JoinTable(
            name = "playlist_music", // 중간 테이블 이름
            joinColumns = @JoinColumn(name = "pl_no"), // PlayList와 연결된 컬럼
            inverseJoinColumns = @JoinColumn(name = "m_no") // Music과 연결된 컬럼
    )
    private List<Music> music; // Music 엔터티와의 관계
}
