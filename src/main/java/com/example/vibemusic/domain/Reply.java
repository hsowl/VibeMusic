package com.example.vibemusic.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Reply extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rno")
    private Long rno;

    @Column(name = "rreplyer", nullable = false)
    private String rreplyer;

    @Column(name = "r_replyText", nullable = false)
    private String r_replyText;

    // music의 fk키
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "no")
    private Music music; // Music 엔터티와의 관계

    public void change(String r_replyText){
        this.r_replyText = r_replyText;
    }

}
