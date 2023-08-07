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
public class ReReply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rr_no")
    private Long rr_no;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "r_no")
    private Reply reply; // Reply 엔터티와의 관계

    @Column(name = "rr_replyer", nullable = false)
    private String rr_replyer; // 대댓글 작성자

    @Column(name = "rr_replyText", nullable = false)
    private String rr_replyText; // 대댓글 내용

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "rr_regDate")
    private Date rr_regDate; // 대댓글 등록 날짜

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "rr_modDate")
    private Date rr_modDate; // 대댓글 수정 날짜
}
