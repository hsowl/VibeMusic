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
    @Column(name = "rno")
    private Long rno;

    @Column(name = "rreplyer", nullable = false)
    private String rreplyer;

    @Column(name = "r_replyText", nullable = false)
    private String r_replyText;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "rregDate")
    private Date rregDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "rmodDate")
    private Date rmodDate;

    // music의 fk키
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "no")
    private Music music; // Music 엔터티와의 관계
}
