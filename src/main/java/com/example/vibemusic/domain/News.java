package com.example.vibemusic.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

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
    private Long n_no;

    @Column
    private String n_title;

    @Column
    private String n_content;

    @Column
    private String n_image;

    @Column
    private int n_viewCount;

    @Temporal(TemporalType.TIMESTAMP)
    private Date n_regDate;

}
