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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "u_no")
    private Long u_no;

    @Column(length = 8, nullable = false, unique = true,updatable = false)
    private String u_id;

    @Column(length = 4, nullable = false, updatable = false)
    private String u_name;

    @Column(length = 16, nullable = false)
    private String u_password;

    @Column(length = 13)
    private String u_phone;

    @Column(length = 30, unique = true)
    private String u_email;


    @Temporal(TemporalType.DATE)
    private Date u_birthDate;

    @Column
    private boolean u_adult;

    // playList와 연결
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<PlayList> playLists;



}
