package com.example.vibemusic.service;

import com.example.vibemusic.domain.Member;
import com.example.vibemusic.domain.MemberRole;
import com.example.vibemusic.dto.MemberJoinDTO;
import com.example.vibemusic.repository.MemberRepository;
import com.example.vibemusic.security.dto.MemberSecurityDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final ModelMapper modelMapper;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void join(MemberJoinDTO memberJoinDTO) throws MidExistException {
        String mid = memberJoinDTO.getMid();
        boolean exist = memberRepository.existsById(mid);

        if(exist){
            throw new MidExistException();
        }

        Member member = modelMapper.map(memberJoinDTO, Member.class);
        member.changePassword(passwordEncoder.encode(memberJoinDTO.getMpw()));
        member.addRole(MemberRole.USER);

        log.info("==================================================");
        log.info("member : {}",member);
        log.info("member.getRole set : {}", member.getRoleSet());

        memberRepository.save(member);
    }

    @Override
    public void login(MemberSecurityDTO memberSecurityDTO) throws MidExistException {
        String mid = memberSecurityDTO.getMid();
        boolean exist = memberRepository.existsById(mid);

        if(exist){
            throw new MidExistException();
        }

        Member member = modelMapper.map(memberSecurityDTO, Member.class);
        member.changePassword(passwordEncoder.encode(memberSecurityDTO.getMpw()));

        memberRepository.getWithRoles(mid);

    }
}
