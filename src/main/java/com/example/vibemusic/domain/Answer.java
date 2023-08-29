package com.example.vibemusic.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "question")
//@ToString
public class Answer extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ano;

    @ManyToOne(fetch = FetchType.LAZY)
    private Question question;

    private String answerText;

    private String answerer;

    public void changeText(String text){
        this.answerText = text;
    }
}