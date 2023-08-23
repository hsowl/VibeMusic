package com.example.vibemusic.dto;

import lombok.Data;

@Data
public class MemberJoinDTO {

    private String mid, mpw, email;
    private boolean del, social;
}
