package com.example.vibemusic.service;

import com.example.vibemusic.domain.EventBoard;
import com.example.vibemusic.dto.EventBoardDTO;
import com.example.vibemusic.dto.PageRequestDTO;
import com.example.vibemusic.dto.PageResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventBoardService {

    Long register(EventBoardDTO eventBoardDTO);
    EventBoardDTO select(Long ebno);
    void modify(EventBoardDTO eventBoardDTO);
    void remove(Long ebno);
    PageResponseDTO<EventBoardDTO> list(PageRequestDTO pageRequestDTO);
    Page<EventBoard> Elist(Pageable pageable);



}
