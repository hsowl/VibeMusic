package com.example.vibemusic.service;

import com.example.vibemusic.domain.News;
import com.example.vibemusic.dto.NewsDTO;
import com.example.vibemusic.dto.PageRequestDTO;
import com.example.vibemusic.dto.PageResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NewsService {

    /**
     Read 보기
     */
    NewsDTO read1news(Long nNo);

    Page<News> list(Pageable pageable);

//    PageResponseDTO<NewsDTO> listWithPaging(PageRequestDTO pageRequestDTO);


}
