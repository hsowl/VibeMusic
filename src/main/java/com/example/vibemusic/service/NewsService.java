package com.example.vibemusic.service;

import com.example.vibemusic.dto.NewsDTO;
import com.example.vibemusic.dto.PageRequestDTO;
import com.example.vibemusic.dto.PageResponseDTO;

import java.util.List;

public interface NewsService {

    /**
     Read 보기
     */
    NewsDTO read1news(Long nNo);

    List list();

//    PageResponseDTO<NewsDTO> listWithPaging(PageRequestDTO pageRequestDTO);
}
