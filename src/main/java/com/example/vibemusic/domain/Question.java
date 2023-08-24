package com.example.vibemusic.domain;

import lombok.*;


import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qNo;

    @Column(length = 500)
    private String qTitle;

    @Column (length = 5000)
    private String qContent;

    @Column (length = 5000)
    private String qImage;

    private String qWriter;

    @Column
    private int qViewCount;

    @Column
    private String qRegDate;

    public void change(String qTitle, String qContent){//QuestionServiceImple에 필요
        this.qTitle=qTitle;
        this.qContent=qContent;
    /*
    메소드 내부에서 this.***은 클래스의 인스턴스 변수인 "***"을 의미.
    따라서, 메소드가 받은 "***" 매개변수의 값을 클래스의 "***"에 할당하여 저장.
     */


    }


}

