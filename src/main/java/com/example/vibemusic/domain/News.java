package com.example.vibemusic.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nNo;

    @Column (length = 500)
    private String nTitle;

    @Column (length = 5000)
    private String nContent;

    @Column (length = 5000)
    private String nImage;

    @Column
    private int nViewCount;

    @Column
    private String nRegDate;

}