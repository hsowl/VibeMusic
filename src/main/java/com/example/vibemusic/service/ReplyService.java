package com.example.vibemusic.service;

import com.example.vibemusic.domain.Reply;
import com.example.vibemusic.dto.PageRequestDTO;
import com.example.vibemusic.dto.PageResponseDTO;
import com.example.vibemusic.dto.ReplyDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ReplyService {

    PageResponseDTO<ReplyDTO> replyListWithPaging(PageRequestDTO pageRequestDTO);

    Long register(ReplyDTO replyDTO);

    void modify(ReplyDTO replyDTO);

    void remove(Long rno);

    Page<Reply> replyListOfMusic(Long no, Pageable pageable);

}
