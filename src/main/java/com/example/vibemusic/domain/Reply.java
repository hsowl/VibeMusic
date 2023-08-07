package com.example.vibemusic.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "r_no")
    private Long r_no;

    @Column(name = "r_replyer", nullable = false)
    private String r_replyer;

    @Column(name = "r_replyText", nullable = false)
    private String r_replyText;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "r_regDate")
    private Date r_regDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "r_modDate")
    private Date r_modDate;

    // music의 fk키
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "m_no")
    private Music music; // Music 엔터티와의 관계
}
