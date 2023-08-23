package com.example.vibemusic.service;

import com.example.vibemusic.dto.MemberJoinDTO;
import com.example.vibemusic.security.dto.MemberSecurityDTO;

public interface MemberService {

    static class MidExistException extends Exception {

    }

    void join(MemberJoinDTO memberJoinDTO) throws MidExistException;

    void login(MemberSecurityDTO memberSecurityDTO) throws MidExistException;

}
